package com.junseo.subwayalram

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.IBinder
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.junseo.subwayalram.common.CommonFunc
import com.junseo.subwayalram.common.CommonInfo
import com.junseo.subwayalram.databaseutils.DatabaseProvider
import com.junseo.subwayalram.databaseutils.SelectedSubway
import com.junseo.subwayalram.databaseutils.SubwayDatabase
import com.junseo.subwayalram.databaseutils.SubwayLineInfoEntity
import com.junseo.subwayalram.databaseutils.SubwayStatioinDetailInfo
import com.junseo.subwayalram.databaseutils.SubwayStation
import com.junseo.subwayalram.datas.StationInfo
import com.junseo.subwayalram.datas.SubwayLineInfo
import com.junseo.subwayalram.retrofit.RetrofitClient
import com.junseo.subwayalram.services.LogBroadcastReceiver
import com.junseo.subwayalram.services.MyForegroundService
import com.junseo.subwayalram.ui.theme.SubwayAlramTheme
import com.junseo.subwayalram.utils.ActivityResultUtil
import com.junseo.subwayalram.utils.GeofenceManager
import com.junseo.subwayalram.utils.SharedPrefsUtil
import com.junseo.subwayalram.utils.log.MLog
import com.junseo.subwayalram.viewmodels.LogModel
import com.junseo.subwayalram.viewmodels.SelectedSubwayRepository
import com.junseo.subwayalram.viewmodels.SelectedSubwayViewModel
import com.junseo.subwayalram.viewmodels.SelectedSubwayViewModelFactory

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {

    /// 로그를 갱신해주는 viewmodel - 테스트 이후 제거할 예정
    private val logViewModel: LogModel by viewModels()

    private lateinit var selectedViewModel: SelectedSubwayViewModel
    private lateinit var logBroadcastReceiver: BroadcastReceiver

    // 권한 요청 런처 선언
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var database: SubwayDatabase

    private fun showFileSelectionDialog(directory: File) {

        directory.listFiles()?.let {files ->
            val fileNames = files.map { it.name }.toTypedArray()

            AlertDialog.Builder(this)
                .setTitle("파일선택")
                .setItems(fileNames) {_, which ->
                    val selectedFile = files[which]
                    openFile(selectedFile)
                }.show()
        }
    }

    private fun openFileExplorer() {
        // 경로 지정
        val logDir = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            File(getExternalFilesDir("subway_alram_log")?.path ?: "")
        } else {
            File(Environment.getExternalStorageDirectory(), "subway_alram_log")
        }

        // 파일 선택 Dialog 열기
        if (logDir.exists() && logDir.isDirectory) {
            showFileSelectionDialog(logDir)
        } else {
            Toast.makeText(this, "폴더가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openFile(file: File) {
        try {
            // File을 Content URI로 변환
            val uri = FileProvider.getUriForFile(
                this,
                "${BuildConfig.APPLICATION_ID}.provider",
                file
            )

            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "text/plain") // MIME 타입 설정
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // URI 읽기 권한 부여
            }

            startActivity(Intent.createChooser(intent, "파일 열기"))
        } catch (e: Exception) {
            Toast.makeText(this, "파일을 열 수 없습니다.", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    private fun saveDataToSharedPrefs() {
        val sdf = SimpleDateFormat(CommonInfo.DATE_FORMAT, Locale.KOREA)
        SharedPrefsUtil.putDate(this, CommonInfo.KEY_SUBWAY_INFO_SAVED_DATE, sdf.format(Date()))
    }

    fun SubwayLineInfoEntity.toStationInfo(): StationInfo {
        return StationInfo(
            STATION_CD = this.STATION_CD,
            STATION_NM = this.STATION_NM,
            LINE_NUM = this.LINE_NUM,
            FR_CODE = this.FR_CODE
        )
    }

    private fun StationInfo.toEntity(): SubwayLineInfoEntity {
        return SubwayLineInfoEntity(
            STATION_CD = this.STATION_CD,
            STATION_NM = this.STATION_NM,
            LINE_NUM = this.LINE_NUM.replace("0", ""),
            FR_CODE = this.FR_CODE
        )
    }

    private fun fetchAndSaveSubwayStations() {
        try {
            val apiService = RetrofitClient.tdataInstance
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = apiService.getSubwayStations().execute()

                    if (response.isSuccessful) {
                        response.body()?.let { stationsApiResponse ->
                            val subwayStations = stationsApiResponse.map {
                                SubwayStation(
                                    outStnNum = it.outStnNum.toInt(),
                                    stationName = it.stnKrNm,
                                    lineName = it.lineNm,
                                    latitude = it.convY.toDouble(),
                                    longitude = it.convX.toDouble()
                                )
                            }

                            subwayStations.forEach { station ->
                                database.subwayStationDao().insertUniqueStation(station)
                                //Log.d("sewhan", "역이름 : ${station.stationName}")
                            }
                        }

                        saveDataToSharedPrefs()

                    } else {
                        // 에러 처리
                        Log.e("API_ERROR", "Error fetching subway stations: ${response.errorBody()}")
                    }
                } catch (e:Exception) {
                    Log.e("sehwan", e.toString())
                }

            }
        } catch (e:Exception) {
            Log.e("sehwan", e.toString())
        }

    }

    private fun checkDateDifference(storedDate: String): Boolean {
        // 현재 날짜 구하기
        val currentDate = Calendar.getInstance().time

        // 저장된 날짜 파싱
        val previousDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(storedDate)

        // 날짜 차이 계산
        if (previousDate != null) {
            // 두 날짜를 비교하기 위한 Calendar 객체 생성
            val previousCalendar = Calendar.getInstance()
            previousCalendar.time = previousDate

            // 현재 날짜와 이전 날짜 사이의 차이를 계산 (일 단위)
            val daysDifference = ((currentDate.time - previousCalendar.timeInMillis) / (1000 * 60 * 60 * 24)).toInt()

            // 90일 이상 차이가 나는지 체크
            return daysDifference >= 90
        }

        // 저장된 날짜가 유효하지 않을 경우 false 반환
        return false
    }

    private fun parseSubwayStations(): List<SubwayStatioinDetailInfo> {
        val subwayStations = mutableListOf<SubwayStatioinDetailInfo>()
        try {
            val jsonArray = JSONArray(CommonInfo.SUBWAY_STATION_INFO_LIST)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val subwayId = jsonObject.getInt("SUBWAY_ID")
                val statnId = jsonObject.getLong("STATN_ID")
                val statnNm = jsonObject.getString("STATN_NM")
                val lineName = jsonObject.getString("LINE_NAME")
                subwayStations.add(SubwayStatioinDetailInfo(subwayId = subwayId, statnId = statnId, statnNm = statnNm, lineName = lineName))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return subwayStations
    }

//    fun saveSubwayStations(jsonString: String) {
//        val subwayStations = parseSubwayStations(jsonString)
//
//        // Room에 데이터를 삽입하는 작업을 비동기로 처리
//        kotlinx.coroutines.GlobalScope.launch {
//            database.subwayStationDetailInfoDao().insertSubwayStationsIfNeeded(subwayStations)
//        }
//    }

    //var selectedSubways: List<SelectedSubway> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //deleteDatabase("subway_database")
        // 여기에서 전역적으로 필요한 초기화 코드 추가
        database = DatabaseProvider.getDatabase(this)

        // 선택했던 역을 로드한다.
        val selectedSubwayDao = database.selectedSubwayDao()
        val repository = SelectedSubwayRepository(selectedSubwayDao)
        selectedViewModel = ViewModelProvider(this, SelectedSubwayViewModelFactory(repository))[SelectedSubwayViewModel::class.java]

        // Room에 데이터를 삽입하는 작업을 비동기로 처리(station id가 있는 지하철 정보를 DB에 넣는다.
        // 데이터가 없을때만 insert
        lifecycleScope.launch {
            val subwayStations = parseSubwayStations()
            database.subwayStationDetailInfoDao().insertSubwayStationsIfNeeded(subwayStations)
        }


        // 지하철 역 로드
        selectedViewModel.loadSubways()


        setContent {
            SubwayAlramTheme {
                MyApp(logViewModel)
            }
        }

        val savedUpdateDate = SharedPrefsUtil.getDate(this, CommonInfo.KEY_SUBWAY_INFO_SAVED_DATE)

        //지하철 역 위/경도가 있는 정보를 가져와 DB에 저장한다.(날짜가 없거나 90일이 넘었으면 다시 로드하여 업데이트한다.)
        if (savedUpdateDate == null) {
            fetchAndSaveSubwayStations()
        } else {
            if (checkDateDifference(savedUpdateDate)) {
                fetchAndSaveSubwayStations()
            }
        }

        // DB 사용전 선택정보를 프리퍼런스에 저장함(현재는 사용안함)
        val savedSubwayStation = SharedPrefsUtil.getString(this, CommonInfo.KEY_SAVED_SELECT_SUBWAY_STATION)
        if(savedSubwayStation == null) {
            val station = SubwayStation(10000, 0,"회사", "없음", 37.5043176, 127.044641)
            val gson = Gson()
            val jsonString = gson.toJson(station)
            SharedPrefsUtil.putString(this, CommonInfo.KEY_SAVED_SELECT_SUBWAY_STATION, jsonString)
        }

        // 런처 등록
        activityResultLauncher = ActivityResultUtil.registerActivityResultCallback(this) { result ->
            // SettingsActivity에서 종료 시에도 들어오지만 처리할 필요가 없어 Action분기를 하지 않음
            if (result.resultCode == Activity.RESULT_OK) {
                // 결과 처리 로직
                val data = result.data
                val stationName = data?.getStringExtra("STATION_NAME") ?: ""

                CoroutineScope(Dispatchers.IO).launch {
                    val stationList = database.subwayStationDao().searchStations("%$stationName%")
                    val stationLineInfos = DatabaseProvider.getDatabase(this@MainActivity)
                        .subwayLineInfoDao().getStationByName(stationName)

                    val selectedSubways = stationList.mapNotNull { station ->
                        val matchingStation = stationLineInfos.find { stationLineInfo ->
                            val convertLineName = if(station.lineName == "경부선" || station.lineName == "경원선") "1호선" else station.lineName
                            //convertLineName == stationLineInfo.LINE_NUM.replace("0", "")
                            //convertLineName.contains(stationLineInfo.LINE_NUM.replace("0", ""))
                            stationLineInfo.LINE_NUM.contains(convertLineName)
                        }
                        if(matchingStation != null) {
                            SelectedSubway(
                                station.id,
                                CommonFunc.extractStationName(station.stationName),
                                if(station.lineName == "경부선" || station.lineName == "경원선") "1호선" else station.lineName,
                                station.latitude,
                                station.longitude,
                                matchingStation.FR_CODE
                            )
                        } else {
                            null
                        }
                    }

                    for (selectedSubway in selectedSubways) {
                        MLog.d("sehwan", "selectedSubway name : ${selectedSubway.stationName}, line : ${selectedSubway.lineName}")
                        selectedViewModel.addSubway(selectedSubway)
                        MyApplication.getInstance().geofenceManager?.addGeofence(selectedSubway, 200F)
                    }
                }
            }
        }

        // LogBroadcastReceiver 등록
        logBroadcastReceiver = LogBroadcastReceiver(logViewModel)
        val filter = IntentFilter("com.junseo.subwayalram.UPDATE_DATA")
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            PendingIntent.FLAG_MUTABLE or Context.RECEIVER_EXPORTED
        } else {
            PendingIntent.FLAG_MUTABLE
        }
        registerReceiver(logBroadcastReceiver, filter, flags)

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            registerReceiver(logBroadcastReceiver, filter, RECEIVER_EXPORTED)
//        } else {
//            registerReceiver(logBroadcastReceiver, filter, RECEIVER_EXPORTED)
//        }

        // 퍼미션 요청 결과 처리
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            var allGranted = true // 모든 권한이 승인되었는지 확인하는 플래그

            // 권한 요청 결과 처리
            permissions.entries.forEach {
                val permissionName = it.key
                val isGranted = it.value
                if (!isGranted) {
                    allGranted = false // 권한이 하나라도 거부되면 플래그를 false로 설정
                    Toast.makeText(this, "$permissionName 권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            // 모든 권한이 승인된 경우에만 서비스를 시작
            if (allGranted) {
                startService()

                // Android 10 (API 29) 이상에서 Background 위치정보를 가져오려면 반드시 필요
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    startService()
                } else {
                    requestLocationPermissions()
                }
            }
        }

        // 권한 요청 실행
        requestPermissions()
    }

    private var myForegroundService: MyForegroundService? = null
    private var isBound = false
    private val serviceState = mutableStateOf(false)

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            myForegroundService = (binder as MyForegroundService.LocalBinder).getService()
            isBound = true
            serviceState.value = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            myForegroundService = null
            isBound = false
            serviceState.value = false
        }
    }

    private fun startService() {
        val serviceIntent = Intent(this, MyForegroundService::class.java)
        serviceIntent.action = MyForegroundService.ACTION_START_SERVICE
        startForegroundService(serviceIntent) // API 26 이상

        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE) // 통신을 위해 서비스를 바인딩한다.
    }

    private fun requestPermissions() {
        val requiredPermissions = mutableListOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            //Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            requiredPermissions.add(Manifest.permission.BLUETOOTH_SCAN)
//            requiredPermissions.add(Manifest.permission.BLUETOOTH_CONNECT)
//        }

        // Android 13 이상인 경우 POST_NOTIFICATIONS 권한 추가
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requiredPermissions.add(Manifest.permission.VIBRATE)
            requiredPermissions.add(Manifest.permission.POST_NOTIFICATIONS)
        }

        // 런타임 권한 요청 실행
        permissionLauncher.launch(requiredPermissions.toTypedArray())
    }

    private fun requestLocationPermissions() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val requiredPermissions = mutableListOf(
                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
            permissionLauncher.launch(requiredPermissions.toTypedArray())
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // BroadcastReceiver 해제
        unregisterReceiver(logBroadcastReceiver)

        if(isBound) {
            unbindService(serviceConnection)
            isBound = false
        }
    }

    @Composable
    fun speedDataUI(service: MyForegroundService) {
        val interval = SharedPrefsUtil.getInt(this, "LOCATION_INFORMATION_REQUEST_INTERVAL", 0)
        val speedByService by service.observespeedAverage().collectAsState(initial = 0.0)
        Text(text = "평균속도 : ${String.format("%.2f", speedByService)}km/h (주기 : ${interval/1000}초)", modifier = Modifier.padding(start = 16.dp))
    }

    @Composable
    fun MyApp(viewModel: LogModel) {
        val context = LocalContext.current

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val text by viewModel.textState.collectAsState() // StateFlow 수집
            // 스크롤 상태 관리
            val scrollState = rememberScrollState()
            //val buttons = remember { (1..3).map { "Button $it" } }

            Column {
                Row {
                    Button(onClick = {
                        ActivityResultUtil.startForResult(
                            activityResultLauncher,
                            context,
                            SelectSubWayActivity::class.java
                        )
                    },
                        modifier = Modifier.padding(5.dp)) {
                        Text("지하철 역 선택")
                    }

                    Button(onClick = {
                        ActivityResultUtil.startForResult(
                            activityResultLauncher,
                            context,
                            SettingsActivity::class.java
                        )
                    },
                        modifier = Modifier.padding(5.dp)) {
                        Text("설정")
                    }

                    Button(onClick = {
                        openFileExplorer()
                    },
                        modifier = Modifier.padding(5.dp)) {
                        Text("로그확인")
                    }
                }
                if (serviceState.value) {
                    myForegroundService?.let {
                        speedDataUI(service = it)
                    }
                } else {
                    Text("아직 서비스가 실행되지 않았습니다.", modifier = Modifier.padding(start = 16.dp))
                }

                //DynamicGridButtonContainer()
                SelectedSubwayList()


                // Text에 둥근 외곽선 및 패딩 추가
                Box(
                    modifier = Modifier
                        .height(500.dp) // 고정 높이 설정
                        .padding(5.dp) // 바깥쪽 패딩 설정
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx() // 외곽선 두께
                            val outlineColor = Color.Blue // 외곽선 색상
                            val cornerRadius = 8.dp.toPx() // 둥근 모서리 반지름

                            // 둥근 외곽선 그리기
                            drawRoundRect(
                                color = outlineColor,
                                size = size.copy(
                                    width = size.width + strokeWidth * 2,
                                    height = size.height + strokeWidth * 2
                                ),
                                topLeft = Offset(-strokeWidth, -strokeWidth),
                                cornerRadius = CornerRadius(
                                    cornerRadius,
                                    cornerRadius
                                ), // 둥근 모서리 설정
                                style = Stroke(width = strokeWidth)
                            )
                        }
                ) {
                    Text(
                        text = text,
                        modifier = Modifier
                            .fillMaxWidth() // 부모의 너비에 맞추기
                            .padding(5.dp) // 안쪽 패딩 설정
                            .verticalScroll(scrollState), // 스크롤 가능하도록 설정
                        style = TextStyle(
                            color = Color.Black, // 텍스트 색상
                            fontSize = 14.sp
                        )
                    )
                }


                LaunchedEffect(text) {
                    scrollState.animateScrollTo(scrollState.maxValue) // 스크롤을 가장 아래로 이동
                }
            }
        }
    }

    @Composable
    fun SelectedSubwayList() {
        val selectedSubways by selectedViewModel.selectedSubways.observeAsState(initial = emptyList())
        //val dialogStates = remember { mutableStateOf(mutableMapOf<SelectedSubway, Boolean>()) }
        val dialogStates = remember { mutableStateMapOf<SelectedSubway, Boolean>() }

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp) // 그룹 전체의 바깥쪽 여백
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(8.dp) // 둥근 모서리 테두리
            )
            .padding(16.dp)) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(selectedSubways) { item ->
                    //ListItem(item)

                    val showDialog = dialogStates[item] ?: false

                    Text(
                        text = "${item.stationName}(${item.lineName})",
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable {
                                dialogStates[item] = true
                            }
                    )
                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { dialogStates[item] = false },
                            text = { Text("${item.stationName}(${item.lineName}) 삭제하시겠습니까?") },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        // 확인 버튼 클릭 시 동작
                                        dialogStates[item] = false
                                        selectedViewModel.deleteSubway(item)
                                        //geofenceManager?.removeGeofence(item.statnId)
                                        MyApplication.getInstance().geofenceManager?.removeGeofence(item)
                                    }
                                ) {
                                    Text("삭제")
                                }
                            },
                            dismissButton = {
                                TextButton(
                                    onClick = {
                                        // 취소 버튼 클릭 시 동작
                                        dialogStates[item] = false
                                    }
                                ) {
                                    Text("취소")
                                }
                            }
                        )
                    }
                }

            }
        }

    }

    @Composable
    fun ListItem(item: SelectedSubway) {
        // ListItem UI 구성
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp) // 각 아이템 간의 상하 간격
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp) // 텍스트 안쪽 패딩
            ) {
                Text(
                    text = "${item.stationName}(${item.lineName})",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                )
            }
        }
/*        Text(
            text = "${item.stationName}(${item.lineName})",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )*/
    }

    @Composable
    fun DynamicGridButtonContainer() {
        //val buttons = remember { (1..20).map { "Button $it" } }

        val selectedSubways by selectedViewModel.selectedSubways.observeAsState(initial = emptyList())

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 열 개수 설정
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp), // 세로 간격 설정
            horizontalArrangement = Arrangement.spacedBy(4.dp) // 가로 간격 설정
        ) {
            items(selectedSubways) { selectedSubway ->
                Button(onClick = {
                    selectedViewModel.deleteSubway(selectedSubway)
                }) {
                    Text("${selectedSubway.stationName}(${selectedSubway.lineName})")
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MyAppPreview() {
        // ViewModel의 모의 인스턴스를 생성 (여기서는 FakeViewModel이라고 가정)
        val fakeViewModel = LogModel( /*초기 데이터 또는 Mock 데이터*/ )

        SubwayAlramTheme {
            MyApp(viewModel = fakeViewModel)
        }
    }
//    @Composable
//    fun selectedStation() {
//        CoroutineScope(Dispatchers.IO).launch {
//            database.selectedSubwayDao().getAllStations()
//        }
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(3), // 열 개수 설정
//            modifier = Modifier.fillMaxWidth(),
//            contentPadding = PaddingValues(10.dp)
//        ) {
//            items(buttons) { item ->
//                Box(
//                    modifier = Modifier
//                        .padding(4.dp) // 아이템 간의 패딩
//                        .fillMaxWidth() // 아이템 너비를 채우기
//                ) {
//                    Button(onClick = { /* 클릭 시 동작 */ }, modifier = Modifier.fillMaxSize()) {
//                        Text(text = item)
//                    }
//                }
//            }
//        }
//    }
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SubwayAlramTheme {
        Greeting("Android")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}*/



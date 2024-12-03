package com.junseo.subwayalram.services

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.text.substring
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.ServiceCompat
import androidx.core.content.ContextCompat
import androidx.core.text.isDigitsOnly
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.junseo.subwayalram.MainActivity
import com.junseo.subwayalram.MyApplication
import com.junseo.subwayalram.R
import com.junseo.subwayalram.common.CommonFunc
import com.junseo.subwayalram.common.CommonInfo
import com.junseo.subwayalram.databaseutils.DatabaseProvider
import com.junseo.subwayalram.databaseutils.SelectedSubway
import com.junseo.subwayalram.databaseutils.SubwayDatabase
import com.junseo.subwayalram.databaseutils.SubwayStatioinDetailInfo
import com.junseo.subwayalram.databaseutils.SubwayStation
import com.junseo.subwayalram.datas.ArrivalInfo
import com.junseo.subwayalram.datas.RealtimeStationArrivalResponse
import com.junseo.subwayalram.retrofit.RetrofitClient
import com.junseo.subwayalram.utils.SharedPrefsUtil
import com.junseo.subwayalram.utils.log.MLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.random.Random

class MyForegroundService : Service() {

    private val CHANNEL_ID = "MyServiceChannel"
    private val NOTIFICATION_CHANNEL_ID = "NotificationChannel"
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var database: SubwayDatabase
    private var isLocationUpdates = false // LocationUpdates 모듈 활성화 여부

    var entryCheckRadius = CommonInfo.ENTRY_CHECK_RADIUS
    var locationInfomationRequestInterval = CommonInfo.LOCATION_INFORMATION_REQUEST_INTERVAL

    private val processor = DataProcessor(maxFalseDetection = 2)

    var allSubwayInfos:List<SubwayStation>? = null

    private fun sendLogToActivity(data: String) {
        MLog.WriteLog("sehwan", data)
        val intent = Intent("com.junseo.subwayalram.UPDATE_DATA")
        intent.putExtra("log_data", data)
        sendBroadcast(intent) // LocalBroadcastManager 대신 sendBroadcast 사용
    }

    // 위치정보를 받아오는 설정이 바뀌면 기존콜백을 제거하고 다시 등록한다.
    private fun resetLocationUpdates() {
        notificationCountMap.clear()

        MLog.WriteLog("sehwan", "requestLocationUpdates 플래그 [$isLocationUpdates]")

        entryCheckRadius = SharedPrefsUtil.getInt(this, "ENTRY_CHECK_RADIUS", entryCheckRadius)
        val interval = SharedPrefsUtil.getInt(this, "LOCATION_INFORMATION_REQUEST_INTERVAL", CommonInfo.LOCATION_INFORMATION_REQUEST_INTERVAL)

        if(locationInfomationRequestInterval != interval) {
            locationInfomationRequestInterval = interval
            sendLogToActivity("설정값을 변경합니다. 반경(m) : $entryCheckRadius, 주기시간(초) : ${interval / 1000}")

            fusedLocationClient.removeLocationUpdates(locationCallback).addOnSuccessListener {
                isLocationUpdates = false
                MLog.WriteLog("sehwan", "requestLocationUpdates 제거에 성공하였습니다.")
            }
                .addOnFailureListener{
                    MLog.WriteLog("sehwan", "requestLocationUpdates 제거에 실패하였습니다.")
                }

            Thread.sleep(200)

            requestLocationUpdates()
        }
    }

    private fun setAllStationInfo() {
        CoroutineScope(Dispatchers.IO).launch {
            allSubwayInfos = database.subwayStationDao().getAllStations()
        }
    }

    override fun onCreate() {

        super.onCreate()
        createNotificationChannel() // 채널 생성
        Log.d("MyService", "Service Created")

        entryCheckRadius = SharedPrefsUtil.getInt(this, "ENTRY_CHECK_RADIUS", entryCheckRadius)
        locationInfomationRequestInterval = SharedPrefsUtil.getInt(this, "LOCATION_INFORMATION_REQUEST_INTERVAL", locationInfomationRequestInterval)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        database = DatabaseProvider.getDatabase(this)

        checkSelectedSubwayCount()

        requestLocationUpdates()

        setAllStationInfo()

//        CoroutineScope(Dispatchers.Main).launch {
//            // 1초 동안 지연
//            delay(1000L)
//
//            test()
//        }
    }

    private fun test() {
        CoroutineScope(Dispatchers.IO).launch {
            for (subwayList in CommonInfo.subwayStations2DArray)    {
                movedDataInputProcess(subwayList.toMutableList())
                delay(500)
            }
        }
    }

    // geofenceManager에 등록된 좌표가 없다면, 선택된 지하철역을 체크하여 있다면 등록시켜준다.
    private fun checkSelectedSubwayCount() {
        CoroutineScope(Dispatchers.IO).launch {
            val countGeofence = MyApplication.getInstance().geofenceManager?.getSelectedSize()
            if (countGeofence == 0) {
                val selectedSubways = database.selectedSubwayDao().getAllStations()
                if (selectedSubways.isNotEmpty()) {
                    for (item in selectedSubways) {
                        MyApplication.getInstance().geofenceManager?.addGeofence(
                            item,
                            200F,
                        )
                    }
                }
            }
        }
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var bestLocation: Location? = null

            // locations를 순회하여 가장 정확한 위치 찾기
            locationResult.locations.forEach { location ->
                Log.d("LocationService", "루프 Location: ${location.latitude}, ${location.longitude}")
                if (bestLocation == null || location.accuracy < (bestLocation?.accuracy ?: Float.MAX_VALUE)) {
                    bestLocation = location
                }
            }

            // 가장 정확한 위치 정보가 있으면 사용
            bestLocation?.let {
                MLog.WriteLog("LocationService", "Best Location: ${it.latitude}, ${it.longitude}, Accuracy: ${it.accuracy}")
                checkLocationInStoredCoordinates(it)
            } ?: run {
                Log.d("LocationService", "No valid location available")
            }
        }
    }

    // 위치정보 업데이트를 설정값에 맞춰 적용한다.
    private fun requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // 네트워크 기반으로 추정된 위치 정보 활용
            val locationRequest = LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY, // 위치 우선순위 설정
                locationInfomationRequestInterval.toLong() // 요청 간격 (밀리초 단위)
            ).apply {
                setMinUpdateIntervalMillis((locationInfomationRequestInterval/2).toLong()) // 최소 업데이트 간격
                setWaitForAccurateLocation(false)  // 정확한 위치 기다림 설정
            }.build()

            if(!isLocationUpdates) {
                fusedLocationClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.getMainLooper()
                )
                    .addOnSuccessListener() {
                        MLog.WriteLog("sehwan", "requestLocationUpdates가 정상등록되었습니다.")
                        isLocationUpdates = true
                    }
                    .addOnFailureListener() {
                        MLog.WriteLog("sehwan", "requestLocationUpdates가 등록에 실패하였습니다.")
                        isLocationUpdates = false
                    }
            }


        } else {
            Toast.makeText(this, "위치 권한 수락이 필요합니다.", Toast.LENGTH_SHORT).show()
        }
    }

    // LocationAlarmReceiver 를 사용하지 않으므로 현재는 사용하지 않는다. AlarmManager를 사용할때 활성화 처리 해야함
    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    Log.d("LocationService", "Location: ${location.latitude}, ${location.longitude}")
                    checkLocationInStoredCoordinates(location)
                } else {
                    Log.d("LocationService", "Location not available")
                }
            }
        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action) {
            ACTION_START_SERVICE -> {
                // 포그라운드 서비스로 전환
                val notification = createNotification()
                notification.flags = notification.flags or Notification.FLAG_NO_CLEAR

                ServiceCompat.startForeground(
                    this,
                    123456,
                    notification,
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
                    } else {
                        0
                    },
                )
            }

            ACTION_STOP_SERVICE -> {
                stopSelf()
            }

            // LocationAlarmReceiver 를 사용하지 않으므로 현재는 사용하지 않는다. AlarmManager를 사용할때 활성화 처리 해야함
            ACTION_REQUEST_LOCATION_UPDATES -> {
                fetchLocation()
            }

            /// 위경도 체크 설정 시간을 다시 세팅하여 재설정
            ACTION_REQUEST_LOCATION_REUPDATES -> {
                resetLocationUpdates()
            }

            // 지오펜스에 트리거된 정보를 GeofenceBroadcastReceiver에서 보내준다. 이를 처리함
            ACTION_REQUEST_TRIGER_SUBWAY -> {
                CoroutineScope(Dispatchers.IO).launch {
                    val stationIds = intent.getStringArrayListExtra("STATION_ID_LIST")

                    stationIds?.let {
                        if(stationIds.isNotEmpty()) {
                            // 일단 broadcast로 지오펜스정보가 들어왔다는것은 도착했다는 의미이므로 모든 데이터를 초기화 해준다.
                            preMoveLineStationId = 0
                            plusSelectedSubway = null
                            minusSelectedSubway = null
                            processor.initDataProcessor()

                            val subwayStatioinDetailInfos:MutableList<SubwayStatioinDetailInfo> = mutableListOf()
                            for (stationId in stationIds) {
                                val subwayStatioinDetailInfo = database.subwayStationDetailInfoDao().getStationsByStationId(stationId)
                                subwayStatioinDetailInfos.addAll(subwayStatioinDetailInfo)
                                MLog.WriteLog("sehwan", "GeofenceBroadcastReceiver 에서 전달받은 데이터 [${subwayStatioinDetailInfo[0].statnNm}][${subwayStatioinDetailInfo[0].lineName}]")
                            }

                            processor.processData(subwayStatioinDetailInfos)

                            showLocalNotification(this@MyForegroundService, "안내", "${subwayStatioinDetailInfos[0].statnNm}역 근처에요.")
                        }
                    }
                }
            }
        }

        return START_STICKY // 서비스가 종료된 후에도 다시 시작됨
    }

    private fun createNotification(): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE // Android 12 이상에서 FLAG_IMMUTABLE 필요
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("지하철 알림")
            .setContentText("지하철 알림 서비스가 실행중입니다.")
            .setSmallIcon(R.drawable.subway_48px)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setAutoCancel(false)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // 알림의 우선순위를 높게 설정
            .build()
    }

    private fun arriveDestinationAlarm(context: Context, title: String, message: String, selectedSubway: SelectedSubway) {
        showLocalNotification(context, title, message)

        fetchRealtimeStationArrival(selectedSubway.stationName, 0, 10) { result ->
            getTransferStationInfo(result, selectedSubway)
        };

    }

    private fun showLocalNotification(context: Context, title: String, message: String) {

        // 랜덤한 notificationId 생성
        val notificationId = Random.nextInt(1, 10000) // 0에서 9999 사이의 랜덤 숫자

        // 알림을 클릭했을 때 실행할 액티비티 설정
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE // Android 12 이상에서는 FLAG_IMMUTABLE 필요
        )

        // 소리 URI 설정
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL)

        // 진동 패턴 설정 (밀리초 단위)
        val vibrationPattern = longArrayOf(100, 2000, 100, 2000) // 진동 대기, 진동 지속, 대기, 진동 지속

        // 알림 빌더 생성
        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.subway_48px) // 알림 아이콘
            .setContentTitle(title) // 알림 제목
            .setContentText(message) // 알림 내용
            .setPriority(NotificationCompat.PRIORITY_HIGH) // 우선순위
            .setContentIntent(pendingIntent) // 클릭 시 동작할 인텐트
            .setSound(soundUri) // 소리 설정
            .setVibrate(vibrationPattern) // 진동 설정
            .setAutoCancel(true) // 클릭 시 자동으로 제거

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13 이상에서 권한 체크
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                // 권한이 이미 부여됨, 알림을 표시할 수 있음
                // 알림 표시
                val notificationManager = NotificationManagerCompat.from(context)
                notificationManager.notify(notificationId, builder.build())
            }
        } else {
            // Android 13 미만에서는 권한 체크가 필요 없음
            // 알림 표시
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(notificationId, builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(NotificationManager::class.java)

            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "My Service Channel",
                NotificationManager.IMPORTANCE_HIGH
            )

            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationChannel.apply {
                enableLights(true)
                enableVibration(true)
                // 진동 패턴 설정
                // 채널아이디가 완전히 지워지거나 바뀌기 전에는 진동패턴은 바뀌지 않는다.
                vibrationPattern = longArrayOf(100, 2000, 100, 2000)
            }

            manager?.createNotificationChannel(serviceChannel)
            manager?.createNotificationChannel(notificationChannel)
        }
    }

    private fun getAddress(lat: Double, lng: Double): List<Address>? {
        lateinit var address: List<Address>

        return try {
            val geocoder = Geocoder(this, Locale.KOREA)
            address = geocoder.getFromLocation(lat, lng, 1) as List<Address>
            address
        } catch (e: IOException) {
            Log.e("sehwan", e.toString())
            null
        }
    }

    // 알림 카운트를 저장할 Map(지금 사용안함)
    private val notificationCountMap = mutableMapOf<String, Int>()

    // 이전 도착 날짜/시간정보를 프리퍼런스에 저장한다.
    private fun setCheckLocationInfoPref(myLocation: Location, subwayStation: SubwayStation?, now: Calendar) {
        val jsonSubwayLocation = JSONObject()

        jsonSubwayLocation.put("stationName", subwayStation?.stationName)
        jsonSubwayLocation.put("lineName", subwayStation?.lineName)
        jsonSubwayLocation.put("latitude", myLocation.latitude)
        jsonSubwayLocation.put("longitude", myLocation.longitude)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.KOREAN)
        val dateTimeString = dateFormat.format(now.time)
        jsonSubwayLocation.put("checkDataTime", dateTimeString)

        SharedPrefsUtil.putString(context = this@MyForegroundService, CommonInfo.KEY_SAVED_CHECK_DATE_TIME, jsonSubwayLocation.toString())
    }

    var moveLineInfo = ""
    private var preMoveLineStationId:Long = 0

    private var preLocation: Location? = null
    private var checkStartTime:Calendar? = null

    private var plusSelectedSubway:SelectedSubway? = null
    private var minusSelectedSubway:SelectedSubway? = null

    /**
     * 특정시간(10분)동안 움직임이 있는지 체크하여 위치정보 트리거 시간을 변경한다.
     */
    private fun checkStayLocation(currentLocation: Location) {
        if(checkStartTime == null) {
            checkStartTime = Calendar.getInstance()
        }
        if (preLocation == null) {
            preLocation = currentLocation
        }

        val now = Calendar.getInstance()
        checkStartTime?.let { time ->
            val div = now.timeInMillis - time.timeInMillis
            MLog.WriteLog("sehwan", "checkStayLocation 시간차이 : ${div/1000}")
            if(div > 10 * 1000 * 60) {
                // 10분이 지났는데도 위치이동이 20m 근방이라면 움직임이 없다고 본다.
                preLocation?.let {location ->
                    val distance = CommonFunc.haversineDistance(location.latitude, location.longitude, currentLocation.latitude, currentLocation.longitude)

                    MLog.WriteLog("sehwan", "checkStayLocation 거리차이 : $distance")
                    if(distance <= 20) {
                        sendLogToActivity("위치정보 트리거 : 1분")
                        checkStartTime = null
                        preLocation = null
                        SharedPrefsUtil.putInt(this, "LOCATION_INFORMATION_REQUEST_INTERVAL", 1000 * 60)
                        resetLocationUpdates()
                    }

                    if(distance > 500) {
                        sendLogToActivity("위치정보 트리거 : 20초")
                        checkStartTime = null
                        preLocation = null
                        SharedPrefsUtil.putInt(this, "LOCATION_INFORMATION_REQUEST_INTERVAL", 1000 * 20)
                        resetLocationUpdates()
                    }
                }
            }
        }
    }

    /**
     * 위치정보가 변경될 때마다 이동장소를 확인하여 사용자에게 노티해준다.
     */
    private fun checkLocationInStoredCoordinates(location: Location?) {
        CoroutineScope(Dispatchers.IO).launch {
            if (location != null) {
                checkStayLocation(location)

                allSubwayInfos?.let { arrSubway ->
                    val address = getAddress(location.latitude, location.longitude)
                    MLog.WriteLog("sehwan", "현위치 주소 : ${address?.get(0)?.getAddressLine(0)}")

                    val currentLatLng = LatLng(location.latitude, location.longitude)

                    var stationInfos:MutableList<SubwayStation> = mutableListOf()
                    val stationInfo = nearestDistanceSubway(currentLatLng, arrSubway) // 단건 역정보

                    if(stationInfo != null) {
                        stationInfos.add(stationInfo)
                    } else {
                        // 정확한 단건정보가 없다면 근사치라도 뽑아 진행한다.
                        stationInfos = nearestDistanceSubways(
                            currentLatLng,
                            arrSubway
                        ).toMutableList() // 단건 역정보가 없을 경우를 위함
                    }

                    if(stationInfos.isNotEmpty()) {
                        movedDataInputProcess(stationInfos)
                    }
                }
            }
        }
    }

    fun areArraysEqual(arr1: ArrayList<String>, arr2: ArrayList<String>): Boolean {
        if (arr1.size != arr2.size) return false
        for (i in arr1.indices) {
            if (arr1[i] != arr2[i]) return false
        }
        return true
    }

    private var oldStationInfo = arrayListOf<String>()
    /**
     * location 정보를 가지고 유저의 이동 범위를 트리거하기위한 데이터 셋팅을 한다.
     */
    private suspend fun movedDataInputProcess(stationList: List<SubwayStation>) {
        val detailStationList: MutableList<SubwayStatioinDetailInfo> = mutableListOf()
        for (station in stationList) {
            val subwayStatioinDetailInfos =
                database.subwayStationDetailInfoDao().getStationsByNameAndLine(station.stationName, station.lineName)

            if(subwayStatioinDetailInfos.isNotEmpty()) {
                detailStationList.add(subwayStatioinDetailInfos[0])
                MLog.WriteLog("sehwan", "위치정보에 따른 역정보[${station.stationName}][${station.lineName}]")
            }
        }

        if(detailStationList.isNotEmpty()) {
            val checkStations = detailStationList.map { item -> "(${item.statnNm})(${item.lineName})" }

            // 로그를 위한 로직
            if(!areArraysEqual(ArrayList(checkStations), oldStationInfo)) {
                sendLogToActivity(ArrayList(checkStations).toString())
                oldStationInfo = ArrayList(checkStations)
            }

            val beforeTrackNumber = processor.getTrackedNumber()
            // 현재방향과 다른 정보가 하나만 들어왔을 떄 동일한 이름의 역을 가져오기위해 데이터를 보정한다.
            if(detailStationList.size == 1 && beforeTrackNumber > 0 && beforeTrackNumber != detailStationList[0].statnId) {
                val detailInfos = database.subwayStationDetailInfoDao().getStationsByStationName(detailStationList[0].statnNm)
                detailStationList.clear()
                detailStationList.addAll(detailInfos)
                MLog.WriteLog("sehwan", "detailStationList : $detailStationList")
            }
            // 역이름으로 검색 시 여러개가 나올수있다. 결과값중 statnId 리스트를 뽑아 Dataprocessor에 전달한다.
            // 들어오는 역ID리스트를 교집합을 통해 이동방향을 유추한다.
            MLog.WriteLog("sehwan", "수집시작")
            processor.processData(detailStationList)
            val currentTrackNumber = processor.getTrackedNumber()
            MLog.WriteLog("sehwan", "수집끝[${currentTrackNumber}]")

            // 도착했을 경우는 목적지를 초기화하여 다시 사용할수있도록 하자
            plusSelectedSubway?.let {
                if(it.statnId.toLong() == currentTrackNumber) {
                    sendLogToActivity("${it.stationName}역에 도착했습니다.")
                    arriveDestinationAlarm(this, "안내","${it.stationName}역에 도착했습니다.", it)

                    preMoveLineStationId = 0
                    processor.initDataProcessor()
                    plusSelectedSubway = null
                    return
                }
            }

            minusSelectedSubway?.let {
                if(it.statnId.toLong() == currentTrackNumber) {
                    sendLogToActivity("${it.stationName}역에 도착했습니다.")
                    arriveDestinationAlarm(this, "안내","${it.stationName}역에 도착했습니다.", it)

                    preMoveLineStationId = 0
                    processor.initDataProcessor()
                    minusSelectedSubway = null

                    return
                }
            }

            if(currentTrackNumber > 0) {
                val currentSubString = currentTrackNumber.toString().substring(0,4)
                if(preMoveLineStationId > 0) {
                    val preSubString = preMoveLineStationId.toString().substring(0,4)
                    MLog.WriteLog("sehwan", "currentSubString[$currentSubString], preSubString[$preSubString]")

                    if(currentSubString == preSubString) {
                        movedCheckProcess(currentTrackNumber, preMoveLineStationId)
                        MLog.WriteLog("sehwan", "2 processor.getTrackedNumber[${currentTrackNumber}], preMoveLineStationId[$preMoveLineStationId]")

                        if(currentTrackNumber == preMoveLineStationId) {
                            MLog.WriteLog("sehwan", "currentTrackNumber == preMoveLineStationId")
                            // 역ID가 같은건 처리하지말자
                        } else {
                            plusSelectedSubway?.let {
                                val remain = it.statnId.toLong() - currentTrackNumber
                                when {
                                    remain in 1..2 -> {
                                        showLocalNotification(
                                            this@MyForegroundService,
                                            "안내",
                                            "${it.stationName} 역 접근하고 있습니다.[$remain 정거장 전]"
                                        )
                                        sendLogToActivity("${it.stationName} 역 접근하고 있습니다.[$remain 정거장 전]")
                                    }
                                    remain > 2 -> {
                                        MLog.WriteLog("sehwan", "plusSelectedSubway remain[$remain]")
                                    }
                                    else -> {
                                        MLog.d("sehwan", "plusSelectedSubway 초기화")
                                        sendLogToActivity("${it.stationName}역에 도착했습니다.")
                                        arriveDestinationAlarm(this, "안내","${it.stationName}역에 도착했습니다.", it)

                                        preMoveLineStationId = 0
                                        processor.initDataProcessor()
                                        plusSelectedSubway = null
                                        minusSelectedSubway = null
                                    }
                                }
                            }

                            minusSelectedSubway?.let {
                                val remain = currentTrackNumber - it.statnId.toLong()
                                when {
                                    remain in 1..2 -> {
                                        showLocalNotification(
                                            this@MyForegroundService,
                                            "안내",
                                            "${it.stationName} 역 접근하고 있습니다.[$remain 정거장 전]"
                                        )
                                        sendLogToActivity("${it.stationName} 역 접근하고 있습니다.[$remain 정거장 전]")
                                    }
                                    remain > 2 -> {
                                        MLog.WriteLog("sehwan", "minusSelectedSubway remain[$remain]")
                                    }
                                    else -> {
                                        MLog.d("sehwan", "minusSelectedSubway 초기화")
                                        sendLogToActivity("${it.stationName}역에 도착했습니다.")
                                        arriveDestinationAlarm(this, "안내","${it.stationName}역에 도착했습니다.", it)

                                        preMoveLineStationId = 0
                                        processor.initDataProcessor()
                                        plusSelectedSubway = null
                                        minusSelectedSubway = null
                                    }
                                }
                            }
                        }
                    }

                }

                preMoveLineStationId = currentTrackNumber
            } else {
                //sendLogToActivity("아직 이동 방향이 확실하지 않다.")
                MLog.WriteLog("sehwan", "아직 이동 방향이 확실하지 않다.")
            }
        }
    }

    /**
     * 현재역과 이전역의 역ID를 통해 이동방향을 결정하고 방향에 따른 설정역이 있는지 체크하여 역을 셋팅한다.
     * 목적지를 찾는 로직
     */
    private suspend fun movedCheckProcess(currentStationId:Long, preStationId:Long) {
        val selectedSubwayInfos =
            database.selectedSubwayDao().getAllStations() // 선택했던 지하철 역

        if(selectedSubwayInfos.isNotEmpty() && currentStationId > 0 && preStationId > 0) {
            val div = currentStationId - preStationId // 둘간의 차이로 방향 결정

            MLog.WriteLog("sehwan", "movedCheckProcess 차이 : $div")

            val minStationCode =
                selectedSubwayInfos.minByOrNull { it.statnId.toLong() }
            val maxStationCode =
                selectedSubwayInfos.maxByOrNull { it.statnId.toLong() }

            var startID = currentStationId
            if (div > 0) {
                minusSelectedSubway = null
                // maxStationCode.statnId를 Long 값으로 비교
                val maxStationCodeValue =
                    maxStationCode?.statnId?.toLong() ?: 0L

                MLog.WriteLog("sehwan", "plusSelectedSubway[${plusSelectedSubway?.stationName}]")

                // startID가 Long 타입이고, maxStationCodeValue와 비교
                while (startID <= maxStationCodeValue && plusSelectedSubway == null) {
                    // 여기에 필요한 로직을 추가
                    for (info in selectedSubwayInfos) {
                        if (info.statnId.toLong() == startID) {
                            sendLogToActivity("[증가]발견된 역은 [${info.stationName}] 입니다.")
                            MLog.d("sehwan", "[증가]find station[${info.statnId}], current station[$currentStationId]")
                            plusSelectedSubway = info
                        }
                    }

                    // startID를 1씩 증가
                    startID++
                }
            } else if(div < 0) {
                plusSelectedSubway = null
                // minStationCode.statnId를 Long 값으로 비교
                val minStationCodeValue =
                    minStationCode?.statnId?.toLong() ?: 0L

                MLog.WriteLog("sehwan", "minusSelectedSubway[${minusSelectedSubway?.stationName}]")

                // startID가 Long 타입이고, minStationCodeValue와 비교
                while (startID >= minStationCodeValue && minusSelectedSubway == null) {
                    // 여기에 필요한 로직을 추가
                    for (info in selectedSubwayInfos) {
                        if (info.statnId.toLong() == startID) {
                            sendLogToActivity("[감소]발견된 역은 [${info.stationName}] 입니다.")
                            MLog.d("sehwan", "[감소]find station[${info.statnId}], current station[$currentStationId]")
                            minusSelectedSubway = info
                        }
                    }

                    // startID를 1씩 감소
                    startID--
                }
            }
        }
    }

    private fun getStoredCoordinates(): List<LatLng> {
        // 저장된 위경도 가져오는 로직 (예시 데이터)
        val savedSubwayStation = SharedPrefsUtil.getString(this, CommonInfo.KEY_SAVED_SELECT_SUBWAY_STATION)
        val station: SubwayStation = Gson().fromJson(savedSubwayStation, SubwayStation::class.java)
        return listOf(LatLng(station.latitude, station.longitude))
    }

    private fun getStoredStationInfo(): SubwayStation? {
        // 저장된 위경도 가져오는 로직 (예시 데이터)
        return try {
            val savedSubwayStation =
                SharedPrefsUtil.getString(this, CommonInfo.KEY_SAVED_SELECT_SUBWAY_STATION)
            Gson().fromJson(savedSubwayStation, SubwayStation::class.java)

            //SubwayStation(10000, "회사", "없음", 37.5043176, 127.044641) // 테스트(회사)

        } catch (e: Exception) {
            Log.e("sehwan", e.toString())
            null
        }
    }

    /**
    현 위치와 선택된 지하철 위치의 거리를 계산하여 offset 보다작으면(진입시) 리턴한다.(리스트)
     **/
    private fun isWithinSelectSubways(currentLatLng: LatLng, storedSubways: List<SelectedSubway>): List<SelectedSubway> {
        return storedSubways.filter { storedSubway ->
            // 거리 값을 담을 FloatArray 생성
            val distanceResult = FloatArray(1)

            // 두 지점 간의 거리 계산
            Location.distanceBetween(
                currentLatLng.latitude,
                currentLatLng.longitude,
                storedSubway.latitude,
                storedSubway.longitude,
                distanceResult
            )

            // 거리 값을 로그로 출력
            val distance = distanceResult[0]
            Log.d("sehwan", "현위치에서 ${storedSubway.stationName}_${storedSubway.lineName}까지 거리 : ${distance}m")

            // 계산된 거리가 ENTRY_CHECK_RADIUS 이내인지 확인
            distance < entryCheckRadius
        }
    }

    /**
    현 위치와 모든 지하철 위치의 거리를 계산하여 500m이내 3개까지 가져온다.
     **/
    private fun nearestDistanceSubways(
        currentLatLng: LatLng,
        storedSubways: List<SubwayStation>
    ): List<SubwayStation> {
        return storedSubways
            .map { storedCoordinate ->
                val distanceResult = FloatArray(1)

                // 두 지점 간의 거리 계산
                Location.distanceBetween(
                    currentLatLng.latitude,
                    currentLatLng.longitude,
                    storedCoordinate.latitude,
                    storedCoordinate.longitude,
                    distanceResult
                )

                // 거리 계산 후, 각 역과 거리 정보를 Pair로 반환
                Pair(storedCoordinate, distanceResult[0])
            }
            .filter { it.second <= 500 } // 500m 이내인 역만 필터링
            .sortedBy { it.second } // 거리 기준으로 오름차순 정렬
            .take(3) // 최대 3개 선택
            .map { it.first } // SubwayStation 객체만 반환
    }

    private fun nearestDistanceSubway(currentLatLng: LatLng, storedSubways: List<SubwayStation>): SubwayStation? {
        // 가장 가까운 지하철역을 찾되, 그 역이 100m 이내일 경우만 반환
        return storedSubways
            .map { storedCoordinate ->
                val distanceResult = FloatArray(1)

                // 두 지점 간의 거리 계산
                Location.distanceBetween(
                    currentLatLng.latitude,
                    currentLatLng.longitude,
                    storedCoordinate.latitude,
                    storedCoordinate.longitude,
                    distanceResult
                )

                // 거리 계산 후, 각 역과 거리 정보를 Pair로 반환
                Pair(storedCoordinate, distanceResult[0])
            }
            .minByOrNull { it.second } // 거리 기준으로 가장 가까운 역 선택
            ?.takeIf { it.second <= 100 } // 거리가 100m 이하일 경우만 반환
            ?.first // Pair에서 SubwayStation 객체만 반환
    }



    private fun isWithinGeofence(currentLatLng: LatLng, storedCoordinates: List<LatLng>): Boolean {
        return storedCoordinates.any { storedLatLng ->
            // 거리 값을 담을 FloatArray 생성
            val distanceResult = FloatArray(1)

            // 두 지점 간의 거리 계산
            Location.distanceBetween(
                currentLatLng.latitude,
                currentLatLng.longitude,
                storedLatLng.latitude,
                storedLatLng.longitude,
                distanceResult
            )

            // 거리 값을 로그로 출력
            val distance = distanceResult[0]
            //Log.d("GeofenceCheck", "Distance to stored location: $distance meters")
            sendLogToActivity("현재위치와의 거리는 : $distance 미터 입니다.");

            // 계산된 거리가 ENTRY_CHECK_RADIUS 이내인지 확인
            distance < entryCheckRadius
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null // 바인딩할 필요가 없으므로 null 반환
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("MyService", "Service Destroyed")

        try {
            fusedLocationClient.removeLocationUpdates(locationCallback).addOnSuccessListener {
                isLocationUpdates = false
                MLog.d("MyService", "removeLocationUpdates Success!!!")
            }
        } catch (e: Exception) {
            Log.e("sehwan", e.toString())
        }
    }

    companion object {
        const val ACTION_START_SERVICE = "com.junseo.subwayalram.action.START_SERVICE"
        const val ACTION_STOP_SERVICE = "com.junseo.subwayalram.action.STOP_SERVICE"

        const val ACTION_REQUEST_LOCATION_UPDATES = "com.junseo.subwayalram.action.REQUEST_LOCATION_UPDATES"
        const val ACTION_REQUEST_LOCATION_REUPDATES = "com.junseo.subwayalram.action.REQUEST_LOCATION_REUPDATES"
        const val ACTION_REQUEST_TRIGER_SUBWAY = "com.junseo.subwayalram.action.REQUEST_TRIGER_SUBWAY"
    }

    /**
     * 도착정보 API를 호출하여 역이름으로 도착정보를 가져온다. 아직은 정보만 뽑고 사용은 하지 않는다.
     */
    private fun fetchRealtimeStationArrival(searchSubwayStationName: String?, startIdx: Int, endIdx: Int, onResult: (List<ArrivalInfo>?) -> Unit) {

        if(searchSubwayStationName != null) {
            val call = RetrofitClient.swopenapiInstance.getRealtimeStationArrival(
                CommonInfo.SUBWAY_REAL_TIME_ARRIVAL_INFORMATION_KEY,
                startIdx,
                endIdx,
                searchSubwayStationName
            )

            call.enqueue(object : Callback<RealtimeStationArrivalResponse> {
                override fun onResponse(
                    call: Call<RealtimeStationArrivalResponse>,
                    response: Response<RealtimeStationArrivalResponse>
                ) {
                    if (response.isSuccessful) {
                        val arrivalList = response.body()?.realtimeArrivalList
//                        arrivalList?.forEach { arrival ->
//                            sendLogToActivity("열차 노선: ${arrival.trainLineNm}, 도착 메시지: ${arrival.arvlMsg2}")
//                        }
                        onResult(arrivalList) // 성공 시 리스트 반환
                    } else {
                        onResult(null) // 에러 시 null 반환
                        println("API 응답 에러: ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<RealtimeStationArrivalResponse>, t: Throwable) {
                    println("API 호출 실패: ${t.message}")
                    onResult(null) // 에러 시 null 반환
                }
            })
        }
    }

    private fun addCheck(selectedSubways: List<SelectedSubway>, arrivalInfo: ArrivalInfo, checkSubway: SelectedSubway): Boolean {
        return selectedSubways.any { selectedSubway ->
            selectedSubway.statnId.substring(0, 4) == arrivalInfo.statnId.substring(0, 4) &&
                    (
                            (selectedSubway.statnId.toLong() < checkSubway.statnId.toLong() && arrivalInfo.statnTid.toLong() < arrivalInfo.statnId.toLong()) ||
                                    (selectedSubway.statnId.toLong() > checkSubway.statnId.toLong() && arrivalInfo.statnTid.toLong() > arrivalInfo.statnId.toLong())
                            )
        }
    }


    private fun _addCheck(selectedSubways: List<SelectedSubway>, arrivalInfo: ArrivalInfo, checkSubway: SelectedSubway): Boolean {
        var isCheck = false
        for (selectedSubway in selectedSubways) {
            if(selectedSubway.statnId.substring(0, 4) == arrivalInfo.statnId.substring(0, 4)) {
                if (selectedSubway.statnId.toLong() < checkSubway.statnId.toLong()) {
                    if (arrivalInfo.statnTid.toLong() < arrivalInfo.statnId.toLong()) {
                        isCheck = true
                    }
                }

                if (selectedSubway.statnId.toLong() > checkSubway.statnId.toLong()) {
                    if (arrivalInfo.statnTid.toLong() > arrivalInfo.statnId.toLong()) {
                        isCheck = true
                    }
                }
            }
        }
        return isCheck
    }

    private fun getTransferStationInfo(list: List<ArrivalInfo>?, checkSubway: SelectedSubway) {
        CoroutineScope(Dispatchers.IO).launch {
            if(list != null) {
                val selectedSubways_org = database.selectedSubwayDao().getAllStations().filter {selectedSubway ->
                    selectedSubway.stationName != checkSubway.stationName
                }
                val lineNumbers = database.subwayStationDetailInfoDao().getStationsByStationName(checkSubway.stationName).map { it.statnId.toString().substring(0, 4) }

                val selectedSubways = selectedSubways_org.filter { subway ->
                    lineNumbers.any { number -> subway.statnId.contains(number) }
                }

                for (arrivalInfo in list) {
                    if(addCheck(selectedSubways, arrivalInfo, checkSubway)) {
                        sendLogToActivity("${arrivalInfo.trainLineNm} : ${arrivalInfo.arvlMsg2}")
                    }
                }
            }
        }


    }
}


/**
 * 여러개의 배열을 파라미터로 받고 해당 데이터를 가지고 연속된 item을 찾아 저장한다.
 * 연속된 item은 자하철ID이다.
 * 지하철 경로 체크를 위한 클래스
 */
class DataProcessor(private val maxFalseDetection: Int) {
    private var lastDetectedSet: Set<String> = emptySet()
    private var falseDetectionCount = 0
    private var trackedNumber:Long = 0 // 이동중으로 확정된 지하철 ID

    fun getTrackedNumber(): Long = trackedNumber
    fun setTrackedNumber(stationId:Long) {
        trackedNumber = stationId
    }

    fun initDataProcessor() {
        lastDetectedSet = emptySet()
        falseDetectionCount = 0
        trackedNumber = 0
    }

    fun processData(input: List<SubwayStatioinDetailInfo>) {
        val currentSet = input.map { it.lineName }.toSet()

        if (lastDetectedSet.isEmpty()) {
            // 들어온 데이터가 하나라면 그냥 확정한다.
            if(input.isNotEmpty() && input.size == 1) {
                trackedNumber = input[0].statnId
            }
            MLog.WriteLog("sehwan", "초기셋팅 개수: ${input.size}")
            // 초기 입력 데이터 설정
            lastDetectedSet = currentSet
            falseDetectionCount = 0
            return
        }

        // 교집합 계산
        val intersection = lastDetectedSet.intersect(currentSet)
        MLog.WriteLog("sehwan", "교집합 : $intersection")

        if (intersection.isNotEmpty()) {
            if(intersection.size == 1) {
                val matchedLine = intersection.first() // 교집합에서 일치하는 노선명 가져오기
                val matchedStation = input.firstOrNull { it.lineName == matchedLine } // 해당 노선의 첫 번째 지하철 정보를 찾음
                matchedStation?.let {
                    trackedNumber = it.statnId
                    MLog.d("sehwan", "셋팅할 statnId: $trackedNumber")
                }
            }
            // 연속 데이터인 경우
            lastDetectedSet = intersection
            falseDetectionCount = 0 // 오탐 카운터 초기화
        } else {
            // 잘못된 데이터인 경우
            falseDetectionCount++

            if (falseDetectionCount <= maxFalseDetection) {
                // 오탐 허용 범위 내에서는 기존 데이터 유지
                MLog.d("sehwan", "False detection within tolerance: $input")
                // 교집합이 없다는건 가지고 있는 trackedNumber 이 유효하지 않다고 보자. 따라서 초기화
                trackedNumber = 0
                lastDetectedSet = intersection
            } else {
                // 허용 범위를 초과하면 초기화
                lastDetectedSet = emptySet()
                falseDetectionCount = 0
                trackedNumber = 0
            }
        }
    }
}
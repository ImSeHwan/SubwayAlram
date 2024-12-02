package com.junseo.subwayalram

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.junseo.subwayalram.common.CommonFunc
import com.junseo.subwayalram.databaseutils.DatabaseProvider
import com.junseo.subwayalram.ui.theme.SubwayAlramTheme
import com.junseo.subwayalram.viewmodels.SubwayStationViewModel
import com.junseo.subwayalram.viewmodels.SubwayStationViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectSubWayActivity : ComponentActivity() {
    private lateinit var factory: SubwayStationViewModelFactory
    private lateinit var subwayStationViewModel: SubwayStationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SubwayAlramTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 검색된 지하철역 리스트를 추가
                    val stations by subwayStationViewModel.stations.collectAsState() // 지하철역 리스트 수집

                    // Column을 사용해 위쪽에 타이틀을 배치
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // 타이틀 텍스트 추가
                        Text(
                            text = "지하철역을 선택해주세요.",
                            style = MaterialTheme.typography.bodyLarge,  // 타이틀 텍스트 스타일
                            modifier = Modifier.padding(16.dp)
                        )

                        LazyColumn(
                            modifier = Modifier.fillMaxSize() // 화면 꽉 채우기
                        ) {
                            item {
                                SubwayStationSearch(subwayStationViewModel)
                            }

                            items(stations) { station ->
                                Text("${station.stationName}(${station.lineName})", modifier = Modifier.padding(8.dp)
                                    .clickable {

                                        CoroutineScope(Dispatchers.IO).launch {
                                            val extractStationName = CommonFunc.extractStationName(station.stationName)

                                            val stationDetails = DatabaseProvider.getDatabase(this@SelectSubWayActivity).subwayStationDetailInfoDao()
                                                .getStationsByNameAndLine(extractStationName, station.lineName)

                                            val intent = Intent().apply {
                                                putExtra("STATION_ID", station.id)
                                                putExtra("STATION_LINENAME", station.lineName)
                                                putExtra("STATION_NAME", extractStationName)
                                                putExtra("STATION_LATITUDE", station.latitude)
                                                putExtra("STATION_LONGITUDE", station.longitude)
                                                putExtra("STATION_INFO_ID", stationDetails[0].statnId)
                                            }
                                            setResult(RESULT_OK, intent)
                                            if (!isFinishing) finish()
                                        }

                                    })
                            }
                        }
                        // 여기에 다른 UI 요소 추가 가능
                    }
                }
            }
        }

        factory = SubwayStationViewModelFactory(DatabaseProvider.getDatabase(this).subwayStationDao())
        subwayStationViewModel = ViewModelProvider(this, factory).get(SubwayStationViewModel::class.java)
    }

    @Composable
    fun SubwayStationSearch(viewModel: SubwayStationViewModel) {
        //val stations by viewModel.stations.collectAsState() // 지하철역 리스트 수집
        var searchQuery by remember { mutableStateOf("") } // 검색 쿼리 상태

        Column(modifier = Modifier.padding(16.dp)) {
            TextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    viewModel.searchStations(it) // 쿼리 변경 시 검색
                },
                label = { Text("지하철역 검색") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true, // 한 줄로 제한
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search // 검색 액션으로 설정
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        // 필요 시 검색 동작 추가
                    }
                )
            )
        }
    }
}
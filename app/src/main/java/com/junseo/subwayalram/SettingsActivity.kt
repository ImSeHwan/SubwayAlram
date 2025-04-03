package com.junseo.subwayalram

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.junseo.subwayalram.common.CommonInfo
import com.junseo.subwayalram.services.MyForegroundService
import com.junseo.subwayalram.ui.theme.SubwayAlramTheme
import com.junseo.subwayalram.utils.SharedPrefsUtil

class DataViewModel : ViewModel() {
    var isChanged by mutableStateOf(false)
}

class SettingsActivity : ComponentActivity() {
    //var isChanged = false
    private lateinit var dataViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)

        setContent {
            SubwayAlramTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Text(
                            text = "반경과 주기를 셋팅해주세요.",
                            style = MaterialTheme.typography.bodyLarge,  // 타이틀 텍스트 스타일
                            modifier = Modifier.padding(16.dp)
                        )
                        FrameView(dataViewModel)
                    }
                }
            }
        }

        // OnBackPressedCallback 설정
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 사용자 정의 백 버튼 동작
                finishWithResult()
            }
        })
    }

    private fun finishWithResult() {

        // 서비스 내의 위치정보체크 재구동
        if(dataViewModel.isChanged) {
            val serviceIntent = Intent(this, MyForegroundService::class.java)
            serviceIntent.action = MyForegroundService.ACTION_REQUEST_LOCATION_REUPDATES
            startService(serviceIntent)
        }

        finish() // Activity 종료
    }
}

@Composable
fun FrameView(dataViewModel: DataViewModel? = null) {
    val context = LocalContext.current

    var entryCheckRadius = SharedPrefsUtil.getInt(context, "ENTRY_CHECK_RADIUS", CommonInfo.ENTRY_CHECK_RADIUS)
    var rocationInfomationRequestInterval = SharedPrefsUtil.getInt(context, "LOCATION_INFORMATION_REQUEST_INTERVAL", CommonInfo.LOCATION_INFORMATION_REQUEST_INTERVAL)


    var radius by remember { mutableStateOf(entryCheckRadius.toString()) }
    var periodTime by remember { mutableStateOf((rocationInfomationRequestInterval/1000).toString()) }

    Column(modifier = Modifier.padding(16.dp)) {
        // 첫 번째 Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "반경(m)", modifier = Modifier.weight(1f))
            TextField(
                value = radius, // 상태를 관리하는 변수를 여기에 넣으세요
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) { // 숫자만 입력 가능
                        radius = newValue
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                Log.d("sehwan", "현재반경 : ${entryCheckRadius}m, 변경 할 반경 : ${radius}m")

                if(entryCheckRadius != radius.toInt()) {
                    dataViewModel?.isChanged = true
                    entryCheckRadius = radius.toInt()
                    SharedPrefsUtil.putInt(context, "ENTRY_CHECK_RADIUS", radius.toInt())

                    Toast.makeText(context, "반경이 ${radius.toInt()}m로 셋팅되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("확인")
            }
        }

        Spacer(modifier = Modifier.height(16.dp)) // Row 간의 간격

        // 두 번째 Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "체크주기시간(초)", modifier = Modifier.weight(1f))
            TextField(
                value = periodTime, // 상태를 관리하는 변수를 여기에 넣으세요
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) { // 숫자만 입력 가능
                        periodTime = newValue
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                Log.d("sehwan", "현재 체크 주기 : ${rocationInfomationRequestInterval/1000}초, 변경될 체크 주기 : ${periodTime}초")

                if(rocationInfomationRequestInterval != periodTime.toInt()*1000) {
                    dataViewModel?.isChanged = true

                    rocationInfomationRequestInterval = periodTime.toInt()*1000
                    SharedPrefsUtil.putInt(context, "LOCATION_INFORMATION_REQUEST_INTERVAL", rocationInfomationRequestInterval)

                    Toast.makeText(context, "체크 주기가 ${periodTime}초로 셋팅되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("확인")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SubwayAlramTheme {
        FrameView()
    }
}
/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SubwayAlramTheme {
        Greeting("Android")
    }
}*/

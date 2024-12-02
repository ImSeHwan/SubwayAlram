package com.junseo.subwayalram.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LogModel : ViewModel() {
    private val _textState = MutableStateFlow("로그출력!!!!!")
    //StateFlow는 항상 하나의 값을 유지합니다. 마지막으로 방출된 값이 StateFlow에 저장되어, 새로운 구독자가 처음 구독할 때 이 최신 값을 즉시 받을 수 있습니다.
    val textState: StateFlow<String> = _textState

    fun appendText(newText: String) {
        val currentTime = SimpleDateFormat("yyyy.MM.dd H:mm", Locale.KOREAN).format(Date())

        _textState.value += "\n$currentTime - ${newText}" // 새 텍스트 누적
    }
}
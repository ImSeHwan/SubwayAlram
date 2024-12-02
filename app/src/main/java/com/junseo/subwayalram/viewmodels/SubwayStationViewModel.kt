package com.junseo.subwayalram.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.junseo.subwayalram.databaseutils.SubwayStation
import com.junseo.subwayalram.databaseutils.SubwayStationDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SubwayStationViewModel(private val subwayStationDao: SubwayStationDao) : ViewModel() {
    private val _stations = MutableStateFlow<List<SubwayStation>>(emptyList())
    val stations: StateFlow<List<SubwayStation>> = _stations

    fun searchStations(query: String) {
        viewModelScope.launch {
            if (query.isNotEmpty()) {
                val result = subwayStationDao.searchStations("%$query%")
                _stations.value = result
            } else {
                _stations.value = emptyList() // 입력이 없으면 빈 리스트
            }
        }
    }
}

// ViewModelProvider.Factory 구현
class SubwayStationViewModelFactory(private val subwayStationDao: SubwayStationDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubwayStationViewModel::class.java)) {
            return SubwayStationViewModel(subwayStationDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.junseo.subwayalram.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.junseo.subwayalram.databaseutils.SelectedSubway
import com.junseo.subwayalram.databaseutils.SelectedSubwayDao
import kotlinx.coroutines.launch

class SelectedSubwayViewModel(private val repository: SelectedSubwayRepository) : ViewModel() {

    private val _selectedSubways = MutableLiveData<List<SelectedSubway>>()
    val selectedSubways: LiveData<List<SelectedSubway>> get() = _selectedSubways

    // 지하철 역을 가져오는 함수
    fun loadSubways() {
        viewModelScope.launch {
            _selectedSubways.value = repository.getAllSubways()
        }
    }

    // 지하철 역 추가
    fun addSubway(station: SelectedSubway) {
        viewModelScope.launch {
            repository.insertSubway(station)
            loadSubways() // 추가 후 데이터 갱신
        }
    }

    fun deleteSubway(station: SelectedSubway) {
        viewModelScope.launch {
            repository.deleteSubway(station)
            loadSubways() // 추가 후 데이터 갱신
        }
    }
}

class SelectedSubwayRepository(private val selectedSubwayDao: SelectedSubwayDao) {

    // 모든 지하철 역을 가져오는 함수
    suspend fun getAllSubways(): List<SelectedSubway> {
        return selectedSubwayDao.getAllStations()
    }

    // 지하철 역 추가
    suspend fun insertSubway(station: SelectedSubway) {
        selectedSubwayDao.insertStation(station)
    }

    // 지하철 역 삭제
    suspend fun deleteSubway(station: SelectedSubway) {
        selectedSubwayDao.deleteStation(station)
    }
}

class SelectedSubwayViewModelFactory(private val repository: SelectedSubwayRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SelectedSubwayViewModel::class.java)) {
            return SelectedSubwayViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

package com.junseo.subwayalram

import android.app.Application
import android.content.Intent
import android.util.Log
import com.junseo.subwayalram.databaseutils.SubwayDatabase
import com.junseo.subwayalram.utils.GeofenceManager
import com.junseo.subwayalram.utils.log.LogHelper

class MyApplication : Application(){
    companion object {
        // 애플리케이션 클래스의 인스턴스를 전역에서 접근 가능하게 하기 위한 싱글톤
        private var instance: MyApplication? = null

        fun getInstance(): MyApplication {
            return instance ?: synchronized(this) {
                instance ?: MyApplication().also { instance = it }
            }
        }
    }

    var geofenceManager: GeofenceManager? = null

    override fun onCreate() {
        super.onCreate()
        instance = this

        geofenceManager = GeofenceManager(this)

        // 로그파일 설정
        if (BuildConfig.LOGFILE) LogHelper.Configure()

//        CoroutineScope(Dispatchers.IO).launch {
//            getAllStations(database)
//        }

    }

    private suspend fun getAllStations(database: SubwayDatabase) {
        val allStation = database.subwayStationDao().getAllStations()

        for (station in allStation) {
            Log.d("SubwayStation DB", "ID: ${station.id}, Name: ${station.stationName}, Line Name: ${station.lineName}")
        }
    }

    fun sendLogToActivity(data: String) {
        val intent = Intent("com.junseo.subwayalram.UPDATE_DATA")
        intent.putExtra("log_data", data)
        sendBroadcast(intent) // LocalBroadcastManager 대신 sendBroadcast 사용
    }


}
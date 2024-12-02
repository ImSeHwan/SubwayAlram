package com.junseo.subwayalram.services

import android.content.Context
import android.content.Intent
import com.junseo.subwayalram.databaseutils.SelectedSubway
import com.junseo.subwayalram.utils.log.MLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object GeofenceEventManager {
    private val geofenceEventBuffer:ArrayList<String> = arrayListOf()
    private var geofenceProcessingJob: Job? = null
    private val bufferDurationMillis = 2000L // 2초 동안 버퍼링

    fun addEvent(requestId: String, context: Context?) {
        context?.let { cxt ->
            MLog.WriteLog("sehwan", "GeofenceEventManager add data")
            geofenceEventBuffer.add(requestId)

            if (geofenceProcessingJob == null || geofenceProcessingJob?.isActive == false) {
                geofenceProcessingJob = CoroutineScope(Dispatchers.IO).launch {
                    MLog.WriteLog("sehwan", "GeofenceEventManager send data : [$requestId]")
                    delay(bufferDurationMillis)
                    processBufferedGeofenceEvents(cxt)
                }
            }
        }

    }

    private fun processBufferedGeofenceEvents(context: Context) {
        if (geofenceEventBuffer.isNotEmpty()) {
            // 합쳐진 데이터를 Foreground Service로 전송
            val serviceIntent = Intent(context, MyForegroundService::class.java).apply {
                putExtra("STATION_ID_LIST", geofenceEventBuffer)
                action = MyForegroundService.ACTION_REQUEST_TRIGER_SUBWAY
            }
            MLog.d("sehwan", "합쳐진 데이터를 Foreground Service로 전송")
            context.startService(serviceIntent)

            geofenceEventBuffer.clear()
        }
    }
}

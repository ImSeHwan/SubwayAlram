package com.junseo.subwayalram.utils

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.junseo.subwayalram.databaseutils.SelectedSubway
import com.junseo.subwayalram.services.GeofenceBroadcastReceiver

class GeofenceManager(private val context: Context) {
    private val geofencingClient: GeofencingClient = LocationServices.getGeofencingClient(context)
    private val geofenceList = mutableListOf<Geofence>()

    fun getSelectedSize(): Int {
        return geofenceList.size
    }

    // Geofence 추가 메서드
    fun addGeofence(selectedSubway: SelectedSubway, radius: Float) {
        val geofence = Geofence.Builder()
            .setCircularRegion(selectedSubway.latitude, selectedSubway.longitude, radius)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
            .setRequestId(selectedSubway.statnId)
            .build()

        geofenceList.add(geofence)

        // Geofence 요청
        val geofencingRequest = GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofence(geofence) // 바로 추가할 geofence 객체
            .build()

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            geofencingClient.addGeofences(geofencingRequest, getGeofencePendingIntent())
                .addOnSuccessListener {
                    // Geofence 추가 성공
                    Log.d("sehwan", "Geofence 추가 성공[${selectedSubway.stationName}][${selectedSubway.lineName}]")
                }
                .addOnFailureListener {
                    // Geofence 추가 실패
                    Log.d("sehwan", "Geofence 추가 실패")
                }
        }

    }

    // PendingIntent 정의
    private fun getGeofencePendingIntent(): PendingIntent {
        val intent = Intent(context, GeofenceBroadcastReceiver::class.java)
        return PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )
    }

    // Geofence 삭제 메서드
    fun removeGeofence(item: SelectedSubway) {
        // geofenceId를 통해 제거할 지오펜스를 찾음
        val geofenceToRemove = geofenceList.find { it.requestId == item.statnId }

        geofenceToRemove?.let {
            // 리스트에서 해당 지오펜스를 삭제
            geofenceList.remove(it)

            geofencingClient.removeGeofences(listOf(item.statnId)) // geofenceId를 리스트로 전달
                .addOnSuccessListener {
                    // Geofence 삭제 성공
                }
                .addOnFailureListener {
                    // Geofence 삭제 실패
                }
        }
    }
    /*
    private val geofencingClient: GeofencingClient = LocationServices.getGeofencingClient(context)
    private val geofenceList: MutableList<Geofence> = mutableListOf()

    private val geoPending: PendingIntent by lazy {
        val intent = Intent(context, GeofenceBroadcastReceiver::class.java)
        PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)
    }

    fun addGeofences(geofence: Geofence?) {
        if(geofence == null) {
            return
        }

        geofenceList.add(geofence)

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            geofencingClient.addGeofences(getGeofencingRequest(geofenceList), geoPending).run {
                addOnSuccessListener {
                    Log.e("addGeo", "add Success")
                    sendLogToActivity("GeofenceManager : add Success")
                }
                addOnFailureListener {
                    Log.e("addGeo", "add Fail")
                    sendLogToActivity("GeofenceManager : add Fail")
                }
            }
        }

    }

    fun removeGeofences() {
        geofencingClient.removeGeofences(geoPending).run {
            addOnSuccessListener {
                Log.e("removeGeo", "remove Success")
                sendLogToActivity("GeofenceManager : remove Success")
                geofenceList.clear()
            }
            addOnFailureListener {
                Log.e("removeGeo", "remove Fail")
                sendLogToActivity("GeofenceManager : remove Fail")
            }
        }
    }

    private fun getGeofencingRequest(list: List<Geofence>): GeofencingRequest {
        return GeofencingRequest.Builder().apply {
            setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            addGeofences(list)
        }.build()
    }

    /**
     * radius : 단위(m)
     */
    fun createGeofence(reqId: String, geo: LatLng, radius: Float): Geofence {
        return Geofence.Builder()
            .setRequestId(reqId)
            .setCircularRegion(geo.latitude, geo.longitude, radius)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setLoiteringDelay(10000)
            .setTransitionTypes(
                Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT
            )
            .build()
    }

    private fun sendLogToActivity(data: String) {
        val intent = Intent("com.junseo.subwayalram.UPDATE_DATA")
        intent.putExtra("log_data", data)
        context.sendBroadcast(intent) // LocalBroadcastManager 대신 sendBroadcast 사용
    }

     */
}

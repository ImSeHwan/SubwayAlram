package com.junseo.subwayalram.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent
import com.junseo.subwayalram.MyApplication
import com.junseo.subwayalram.databaseutils.SelectedSubway
import com.junseo.subwayalram.utils.log.MLog

class GeofenceBroadcastReceiver: BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {

        if(intent != null) {
            val geofencingEvent = GeofencingEvent.fromIntent(intent)

            if (geofencingEvent?.hasError() == true) {
                Log.d("GeofenceErr", GeofenceStatusCodes.getStatusCodeString(geofencingEvent.errorCode))
                MyApplication.getInstance().sendLogToActivity("GeofenceBroadcastReceiver : ${GeofenceStatusCodes.getStatusCodeString(geofencingEvent.errorCode)}")
            } else {
                val geofenceTransaction = geofencingEvent?.geofenceTransition

                if (geofenceTransaction == Geofence.GEOFENCE_TRANSITION_ENTER ||
                    geofenceTransaction == Geofence.GEOFENCE_TRANSITION_EXIT
                ) {
                    val triggeringGeofences = geofencingEvent.triggeringGeofences

                    when (geofenceTransaction) {
                        Geofence.GEOFENCE_TRANSITION_ENTER -> {
                            triggeringGeofences?.forEach {
                                //val subwayDetailInfo =
                                MLog.WriteLog("sehwan", "인입된 station id : ${it.requestId}")
                                GeofenceEventManager.addEvent(it.requestId, context)
                            }
                        }
                    }
                    /*                val triggeringGeofences = geofencingEvent.triggeringGeofences
                                    val transitionMsg = when (geofenceTransaction) {
                                        Geofence.GEOFENCE_TRANSITION_ENTER -> "Enter"
                                        Geofence.GEOFENCE_TRANSITION_EXIT -> "Exit"
                                        else -> "-"
                                    }
                                    triggeringGeofences?.forEach {
                                        Log.e("geofence", "${it.requestId} - $transitionMsg")
                                        MyApplication.getInstance().sendLogToActivity("GeofenceBroadcastReceiver ${it.requestId} - $transitionMsg")
                                    }*/
                } else {
                    Log.e("geofence", "Unknown")
                    MyApplication.getInstance().sendLogToActivity("GeofenceBroadcastReceiver Unknown")
                }
            }
        }

    }
}
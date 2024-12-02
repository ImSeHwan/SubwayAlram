package com.junseo.subwayalram.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class LocationAlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val serviceIntent = Intent(context, MyForegroundService::class.java).apply {
            action = MyForegroundService.ACTION_REQUEST_LOCATION_UPDATES
        }
        context.startService(serviceIntent)
    }
}
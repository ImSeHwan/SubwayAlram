package com.junseo.subwayalram.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.junseo.subwayalram.viewmodels.LogModel

class LogBroadcastReceiver: BroadcastReceiver {
    private var viewModel: LogModel? = null

    constructor() : super()
    constructor(viewModel: LogModel) : super() {
        this.viewModel = viewModel
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val data = intent?.getStringExtra("log_data")
        if (data != null) {
            Log.d("LogBroadcastReceiver", "Received data: $data")
            viewModel?.appendText(data) // ViewModel에 데이터 추가
        }
    }
}

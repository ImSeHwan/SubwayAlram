package com.junseo.subwayalram.utils

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.junseo.subwayalram.MyApplication
import com.junseo.subwayalram.utils.log.MLog

class BeaconScanner(private val context: Context) {

    private val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter
    private val bluetoothLeScanner: BluetoothLeScanner? = bluetoothAdapter?.bluetoothLeScanner


    // 비콘 검색 시작
    fun startScanning() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED) {
            Log.d("BeaconScanner", "startScanning")
            bluetoothLeScanner?.startScan(leScanCallback)
        } else {
            Log.d("BeaconScanner", "BLUETOOTH_SCAN 수락하지 않았다.")
        }
    }

    // 비콘 검색 종료
    fun stopScanning() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED) {
            Log.d("BeaconScanner", "stopScanning")
            bluetoothLeScanner?.stopScan(leScanCallback)
        }
    }

    // BLE 스캔 콜백
    private val leScanCallback = object : ScanCallback() {
        @RequiresApi(Build.VERSION_CODES.R)
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED) {
                val beaconAlias = result.device.alias
                val beaconName = result.device.name
                val beaconAddress = result.device.address
                val rssi = result.rssi


                // 비콘 정보 로그 출력
                Log.d("BeaconScanner", "비콘 이름: $beaconName, 비콘 Alias: $beaconAlias, 주소: $beaconAddress, 신호 세기: $rssi")

                if(beaconName != null && rssi >= -50 ) {
                    //MyApplication.getInstance().sendLogToActivity("비콘 이름: $beaconName, 비콘 Alias: $beaconAlias, 주소: $beaconAddress, 신호 세기: $rssi")
                    MLog.WriteLog("sehwan", "비콘 이름: $beaconName, 비콘 Alias: $beaconAlias, 주소: $beaconAddress, 신호 세기: $rssi")
                    if(rssi >= -30) {
                        MyApplication.getInstance().sendLogToActivity("[강한신호] 비콘 이름: $beaconName, 비콘 Alias: $beaconAlias, 주소: $beaconAddress, 신호 세기: $rssi")
                    } else {
                        MyApplication.getInstance().sendLogToActivity("[중간정도신호] 비콘 이름: $beaconName, 비콘 Alias: $beaconAlias, 주소: $beaconAddress, 신호 세기: $rssi")
                    }
                }
            }
        }

        override fun onBatchScanResults(results: List<ScanResult>) {
            super.onBatchScanResults(results)
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED) {
                for (result in results) {
                    val beaconName = result.device.name
                    val beaconAddress = result.device.address
                    val rssi = result.rssi
                    Log.d("BeaconScanner", "비콘 이름: $beaconName, 주소: $beaconAddress, 신호 세기: $rssi")
                }
            }
        }

        override fun onScanFailed(errorCode: Int) {
            super.onScanFailed(errorCode)
            Log.e("BeaconScanner", "BLE 스캔 실패: $errorCode")
        }
    }
}

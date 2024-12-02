package com.junseo.subwayalram.services

import android.Manifest
import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.ServiceCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.junseo.subwayalram.MainActivity
import com.junseo.subwayalram.R
import com.junseo.subwayalram.common.CommonInfo
import com.junseo.subwayalram.databaseutils.SubwayStation
import com.junseo.subwayalram.utils.GeofenceManager
import com.junseo.subwayalram.utils.SharedPrefsUtil
import java.io.IOException
import java.util.Locale
import kotlin.random.Random

class MyForegroundServiceGEO : Service() {
    private val CHANNEL_ID = "MyServiceChannel"
    //private lateinit var locationManager: LocationManager
    //private var isLocationUpdatesRequested = false // 위치 업데이트 요청 여부 체크

    //private lateinit var fusedLocationClient: FusedLocationProviderClient
    //private lateinit var alarmManager: AlarmManager
    //private lateinit var pendingIntent: PendingIntent

    var geofenceManager: GeofenceManager? = null

    private fun sendLogToActivity(data: String) {
        val intent = Intent("com.junseo.subwayalram.UPDATE_DATA")
        intent.putExtra("log_data", data)
        sendBroadcast(intent) // LocalBroadcastManager 대신 sendBroadcast 사용
    }

    override fun onCreate() {

        super.onCreate()
        createNotificationChannel() // 채널 생성
        Log.d("MyService", "Service Created")
        sendLogToActivity("Service Created");

        val subwayStationInfo = getStoredSubwayInfo()

//        if(subwayStationInfo != null) {
//            geofenceManager = GeofenceManager(this)
//            val geo = LatLng(subwayStationInfo.latitude, subwayStationInfo.longitude)
//            //val geo = LatLng(37.5043176, 127.044641) // 테스트 좌표(회사)
//            val geofence = geofenceManager?.createGeofence(subwayStationInfo.id.toString(), geo, 1000f)
//            geofenceManager?.addGeofences(geofence)
//        }

//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//
//        val intent = Intent(this, LocationAlarmReceiver::class.java)
//        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
//
//        val interval: Long = 10 * 1000 // 1분
//        val triggerAtMillis = SystemClock.elapsedRealtime() + interval
//
//        alarmManager.setInexactRepeating(
//            AlarmManager.ELAPSED_REALTIME_WAKEUP,
//            triggerAtMillis,
//            interval,
//            pendingIntent
//        )

        //locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // 위치 업데이트 요청 (최초 한 번만)
//        if (!isLocationUpdatesRequested) {
//            requestLocationUpdates()
//        }
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
//            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
//                if (location != null) {
//                    Log.d("LocationService", "Location: ${location.latitude}, ${location.longitude}")
//                    checkLocationInStoredCoordinates(location)
//                } else {
//                    Log.d("LocationService", "Location not available")
//                }
//            }
        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        if (intent?.action != null && intent.action.equals(
//                ACTION_STOP_FOREGROUND, ignoreCase = true)) {
//            stopSelf()
//        }

        sendLogToActivity("onStartCommand action : ${if(intent == null) "empty" else intent.action}")

        when (intent?.action) {
            ACTION_START_SERVICE -> {
                // 포그라운드 서비스로 전환
                val notification = createNotification()
                notification.flags = notification.flags or Notification.FLAG_NO_CLEAR

                ServiceCompat.startForeground(
                    this,
                    123456,
                    notification,
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
                    } else {
                        0
                    },
                )
            }

            ACTION_STOP_SERVICE -> {
                stopSelf()
            }

            ACTION_REQUEST_LOCATION_UPDATES -> {
                fetchLocation()
            }
        }

        return START_STICKY // 서비스가 종료된 후에도 다시 시작됨
    }

    private fun createNotification(): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE // Android 12 이상에서 FLAG_IMMUTABLE 필요
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("지하철 알림")
            .setContentText("지하철 알림 서비스가 실행중입니다.")
            .setSmallIcon(R.drawable.subway_48px)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setAutoCancel(false)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // 알림의 우선순위를 높게 설정
            .build()
    }

    private fun showLocalNotification(context: Context, title: String, message: String) {
        //val channelId = "local_channel_id"

        // 랜덤한 notificationId 생성
        val notificationId = Random.nextInt(1, 10000) // 0에서 9999 사이의 랜덤 숫자

        // 알림을 클릭했을 때 실행할 액티비티 설정
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE // Android 12 이상에서는 FLAG_IMMUTABLE 필요
        )

        // 소리 URI 설정
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL)

        // 진동 패턴 설정 (밀리초 단위)
        val vibrationPattern = longArrayOf(0, 500, 1000, 500) // 진동 대기, 진동 지속, 대기, 진동 지속

        // 알림 빌더 생성
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.subway_48px) // 알림 아이콘
            .setContentTitle(title) // 알림 제목
            .setContentText(message) // 알림 내용
            .setPriority(NotificationCompat.PRIORITY_HIGH) // 우선순위
            .setContentIntent(pendingIntent) // 클릭 시 동작할 인텐트
            //.setSound(soundUri) // 소리 설정
            //.setVibrate(vibrationPattern) // 진동 설정
            .setAutoCancel(true) // 클릭 시 자동으로 제거

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13 이상에서 권한 체크
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                // 권한이 이미 부여됨, 알림을 표시할 수 있음
                // 알림 표시
                val notificationManager = NotificationManagerCompat.from(context)
                notificationManager.notify(notificationId, builder.build())
            }
        } else {
            // Android 13 미만에서는 권한 체크가 필요 없음
            // 알림 표시
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(notificationId, builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 소리 URI 설정
            val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            // 진동 패턴 설정 (밀리초 단위)
            //var vibrationPattern = longArrayOf(0, 500, 1000, 500) // 진동 대기, 진동 지속, 대기, 진동 지속

            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "My Service Channel",
                NotificationManager.IMPORTANCE_HIGH
            )

            serviceChannel.apply {
                // 소리 설정
                setSound(soundUri, AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
                )

                enableVibration(true)
                // 진동 패턴 설정
                // 채널아이디가 완전히 지워지거나 바뀌기 전에는 진동패턴은 바뀌지 않는다.
                vibrationPattern = longArrayOf(100, 2000, 100, 2000)
            }

            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
    }

    private fun getAddress(lat: Double, lng: Double): List<Address>? {
        lateinit var address: List<Address>

        return try {
            val geocoder = Geocoder(this, Locale.KOREA)
            address = geocoder.getFromLocation(lat, lng, 1) as List<Address>
            address
        } catch (e: IOException) {
            Log.e("sehwan", e.toString())
            null
        }
    }

    private fun checkLocationInStoredCoordinates(location: Location?) {
        sendLogToActivity("checkLocationInStoredCoordinates 호출")
        if (location != null) {
            val address = getAddress(location.latitude, location.longitude)?.get(0)
            sendLogToActivity("주소 : ${address?.getAddressLine(0)}")

            val storedCoordinates = getStoredCoordinates() // 저장된 위경도 가져오는 로직
            val currentLatLng = LatLng(location.latitude, location.longitude)
            if (isWithinGeofence(currentLatLng, storedCoordinates)) {
                // 위치가 지오펜스 안에 있는 경우 처리
                Log.d("MyService", "현재 위치가 지오펜스 안에 있습니다.")
                sendLogToActivity("현재 위치가 지오펜스 안에 있습니다.")
                showLocalNotification(this, "지하철 알림", "대상 위치에 도착했어요.")
            }
        }
    }

    private fun getStoredCoordinates(): List<LatLng> {
        // 저장된 위경도 가져오는 로직 (예시 데이터)
        val savedSubwayStation = SharedPrefsUtil.getString(this, CommonInfo.KEY_SAVED_SELECT_SUBWAY_STATION)
        val station: SubwayStation = Gson().fromJson(savedSubwayStation, SubwayStation::class.java)
        return listOf(LatLng(station.latitude, station.longitude))
    }

    /// 저장된 지하철정보 가져오기
    private fun getStoredSubwayInfo(): SubwayStation? {
        try {
            val savedSubwayStation = SharedPrefsUtil.getString(this, CommonInfo.KEY_SAVED_SELECT_SUBWAY_STATION)
            return Gson().fromJson(savedSubwayStation, SubwayStation::class.java)
        } catch (e: Exception) {
            return null
        }
    }

    private fun isWithinGeofence(currentLatLng: LatLng, storedCoordinates: List<LatLng>): Boolean {
        return storedCoordinates.any {
            FloatArray(1).apply {
                Location.distanceBetween(currentLatLng.latitude, currentLatLng.longitude, it.latitude, it.longitude, this)
            }[0] < 100 // 100m 이내
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null // 바인딩할 필요가 없으므로 null 반환
    }

    override fun onDestroy() {
        super.onDestroy()

        //locationManager.removeUpdates(locationListener)
        //isLocationUpdatesRequested = false // 위치 업데이트 요청 상태 초기화
        Log.d("MyService", "Service Destroyed")

        //alarmManager.cancel(pendingIntent)
    }

    companion object {
        const val ACTION_START_SERVICE = "com.junseo.subwayalram.action.START_SERVICE"
        const val ACTION_STOP_SERVICE = "com.junseo.subwayalram.action.STOP_SERVICE"

        const val ACTION_REQUEST_LOCATION_UPDATES = "com.junseo.subwayalram.action.REQUEST_LOCATION_UPDATES"
    }
}

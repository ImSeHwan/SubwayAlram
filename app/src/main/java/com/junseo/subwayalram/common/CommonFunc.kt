package com.junseo.subwayalram.common

import android.location.Location
import android.util.Log
import com.junseo.subwayalram.MyApplication
import java.util.Calendar
import java.util.Date
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class CommonFunc {
    companion object {

        /// 정규 표현식으로 괄호와 그 안의 내용 제거
        fun extractStationName(input: String): String {
            // 정규 표현식으로 괄호와 그 안의 내용 제거
            val regex = "\\(.*?\\)".toRegex()
            return input.replace(regex, "").trim()
        }

        /**
         * 두 지점간의 거리를 구한다. 단위는 미터
         */
        fun haversineDistance(startLatitude: Double, startLongitude: Double, endLatitude: Double, endLongitude: Double): Double {
            val results = FloatArray(1)
            Location.distanceBetween(startLatitude, startLongitude, endLatitude, endLongitude, results)
            return results[0].toDouble() // 미터 단위 거리 반환
        }

        /**
         * 두 지점과 시간변화에 따른 속력 구하기
          */
        fun calculateSpeed(
            startLatitude: Double, startLongitude: Double, time1: Calendar,
            endLatitude: Double, endLongitude: Double, time2: Calendar
        ): Double {
            val distance = haversineDistance(startLatitude, startLongitude, endLatitude, endLongitude) // 미터 단위 거리

            // 시간 차이 계산 (초 단위)
            val timeDiffInSeconds = (time2.timeInMillis - time1.timeInMillis) / 1000

            Log.d("sehwan", "계산할 거리 : $distance")
            Log.d("sehwan", "계산할 시간차이 : $timeDiffInSeconds")
            //MyApplication.getInstance().sendLogToActivity("계산할 거리 : $distance, 계산할 시간차이 : $timeDiffInSeconds")

            return if (timeDiffInSeconds > 0) (distance / timeDiffInSeconds) * 3.6 else 0.0 // m/s에서 km/h로 변환하여 반환
        }
    }
}
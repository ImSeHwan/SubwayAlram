package com.junseo.subwayalram.common

import android.location.Location
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CommonFunc {
    companion object {

        /// 가중치에 따른 배열 평균
        fun calculateWeightedAverage(arr: MutableList<Double>): Double {
            if (arr.isEmpty()) return 0.0 // 배열이 비어있다면 무시

            var weightedSum = 0.0 // 가중합
            var weightSum = 0  // 가중치의 합

            for (i in arr.indices) { // 모든 인덱스 탐색
                val weight = i + 1 // 가중치는 인덱스 + 1
                weightedSum += arr[i] * weight
                weightSum += weight
            }

            return if (weightSum != 0) weightedSum.toDouble() / weightSum else 0.0
        }

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
         * Location을 스트링으로 변환
         */
        fun formatLocationTime(location: Location): String {
            val timeInMillis = location.time // Long 타입 시간 (밀리초)
            val date = Date(timeInMillis) // Date 객체로 변환
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()) // 원하는 포맷
            return format.format(date) // 포맷된 시간 문자열 반환
        }

        /**
         * 두 지점과 시간변화에 따른 속력 구하기
         */
        fun calculateSpeed(
            preLocation: Location, currentLocation: Location
            /*startLatitude: Double, startLongitude: Double, time1: Calendar,
            endLatitude: Double, endLongitude: Double, time2: Calendar*/
        ): Double {
            val distance = currentLocation.distanceTo(preLocation) // 미터
            val time = (currentLocation.time - preLocation.time) / 1000.0 // 초

            return if (time > 0) (distance / time)*3.6 else 0.0

//            val distance = haversineDistance(preLocation.latitude, preLocation.longitude, currentLocation.latitude, currentLocation.longitude) // 미터 단위 거리
//
//            // 시간 차이 계산 (초 단위)
//            val timeDiffInSeconds = (time2.timeInMillis - time1.timeInMillis) / 1000
//
//            Log.d("sehwan", "계산할 거리 : $distance")
//            Log.d("sehwan", "계산할 시간차이 : $timeDiffInSeconds")
//            //MyApplication.getInstance().sendLogToActivity("계산할 거리 : $distance, 계산할 시간차이 : $timeDiffInSeconds")
//
//            return if (timeDiffInSeconds > 0) (distance / timeDiffInSeconds) * 3.6 else 0.0 // m/s에서 km/h로 변환하여 반환
        }
    }
}
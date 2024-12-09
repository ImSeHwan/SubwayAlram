package com.junseo.subwayalram.utils

import android.content.Context
import android.content.Intent
import androidx.core.text.isDigitsOnly
import com.junseo.subwayalram.MyApplication
import com.junseo.subwayalram.common.CommonInfo
import com.junseo.subwayalram.databaseutils.DatabaseProvider
import com.junseo.subwayalram.databaseutils.SubwayDatabase
import com.junseo.subwayalram.databaseutils.SubwayLineInfoEntity
import com.junseo.subwayalram.datas.StationInfo
import com.junseo.subwayalram.services.MyForegroundService
import com.junseo.subwayalram.utils.log.MLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.abs

class MoveInfoManager {
    private var preDetectedSet: Set<StationInfo> = emptySet()
    private var _moveLineInfo = ""
    private var falseDetectionCount = 0 // 오탐지 허용카운트
    private var _maxFalseDetection = 0
    private var database: SubwayDatabase

    private var context:Context

    constructor(contenx: Context, maxFalseDetection: Int) {
        _maxFalseDetection = maxFalseDetection
        database = DatabaseProvider.getDatabase(contenx)
        context = contenx
    }

    /**
     * 역간 거리가 1개 차이인지 확인
     * 역에대한 교집합 로직을 실행하고 나서도 데이터가 2개일경우 처리
     */
    private fun checkStations(preStations:Set<StationInfo>, currentStations:Set<StationInfo>): Set<StationInfo>? {
        var intersection: MutableSet<StationInfo>? = null

        val combined = preStations.map { Pair(it, "pre") } + currentStations.map { Pair(it, "current") }
        val groupedByLine = combined.groupBy { it.first.LINE_NUM }

        groupedByLine.forEach { (_, stations) ->
            if(stations.size == 2) {
                var pre:StationInfo? = null
                var current:StationInfo? = null

                stations.forEach { (station, source) ->
                    if(source == "pre") {
                        pre = station
                    } else if(source == "current") {
                        current = station
                    }
                }

                if(intersection == null) {
                    val preData = pre
                    val currentData = current

                    if (preData != null && currentData != null) {

                        val cal = abs(calculate(preData.FR_CODE) - calculate(currentData.FR_CODE))
                        if (cal == 1) {
                            intersection = mutableSetOf(currentData)
                        }
                    }
                }
            }
        }

        return intersection
    }

    /**
     * 중복 호출을 막기위함
     */
    private var preStation = ""
    fun inputDetecteInfo(input: List<StationInfo>) {
        CoroutineScope(Dispatchers.IO).launch {
            val currentSet = input.toSet()

            MLog.WriteLog("sehwan", "인입된 지하철 정보 : $currentSet")

            if(preDetectedSet.isEmpty()) {
                preDetectedSet = currentSet
            } else {
                // 교집합 계산
                val frLastDetectedSet = preDetectedSet.map { it.LINE_NUM }.toSet()
                val frCurrentSet = currentSet.map { it.LINE_NUM }.toSet()

                // 교집합 구하기
                val commonFrCodes = frLastDetectedSet.intersect(frCurrentSet)
                var intersection = currentSet.filter { it.LINE_NUM in commonFrCodes }

                MLog.WriteLog("sehwan", "이전 지하철 정보 : $preDetectedSet")
                MLog.WriteLog("sehwan", "교집합 지하철 정보 : $intersection")

                // 2개일떄는 한번더 체크해주자
                if(intersection.size == 2) {
                    val stationInfo = checkStations(preDetectedSet, intersection.toSet())
                    stationInfo?.let {
                        intersection = it.toList()
                    }
                }


                if(intersection.size == 1) {
                    if(_moveLineInfo != intersection[0].LINE_NUM) {
                        _moveLineInfo = intersection[0].LINE_NUM
                    }

                    // 이동하는 노선을 찾았을때
                    if(preStation.isEmpty() || preStation != intersection[0].STATION_NM) {
                        preStation = intersection[0].STATION_NM
                        nextStationProcess(intersection.toSet())
                    }
                }

                preDetectedSet = currentSet
            }
        }
    }

    private suspend fun nextStationProcess(currentSet: Set<StationInfo>) {
        if(currentSet.isNotEmpty()) {
            MyApplication.getInstance().sendLogToActivity("현재역 : ${currentSet.toList()[0].STATION_NM}")
        }

        val findStation = currentSet.filter { it.LINE_NUM == _moveLineInfo }
        MLog.WriteLog("sehwan", "findStation : $findStation")
        val preStation = preDetectedSet.filter { it.LINE_NUM == _moveLineInfo }
        if(findStation.isNotEmpty()) {
            // 있다면 해당하는 StationInfo 정보를 preDetectedSet 셋팅
            checkMyList(preStation[0], findStation[0], _moveLineInfo)
            //preDetectedSet = findStation.toSet()
            falseDetectionCount = 0
        } else {
            if(_maxFalseDetection <= falseDetectionCount) {
                MLog.d("sehwan", "initMoveInfoManager 초기화!!!!!")
                initMoveInfoManager()
            } else {
                falseDetectionCount++
            }
        }
    }

    fun initMoveInfoManager() {
        //preDetectedSet = emptySet()
        _moveLineInfo = ""
        falseDetectionCount = 0
    }

    /**
     * 해당하는 노선의 역정보 리스트를 가져와 있는지체크.
     * 각 노선에 대한 특성을 가지고 파싱하는 작업 필요
     */
    private suspend fun checkMyList(preStationInfo: StationInfo?, currentStationInfo: StationInfo, moveLineInfo: String) {
        MLog.WriteLog("sehwan", "설정된 호선정보 : $moveLineInfo")
        CommonInfo.groupedStations?.let { allSubwayDatas ->
            val dataList = allSubwayDatas[moveLineInfo]
            when (moveLineInfo) {
                "1호선" -> {
                    dataList?.let {
                        process1Line(preStationInfo, currentStationInfo, dataList)
                    }
                }
                "2호선" -> {
                    dataList?.let {
                        process2Line(preStationInfo, currentStationInfo, dataList)
                    }
                }
                "3호선" -> {
                    dataList?.let {
                        processLine(preStationInfo, currentStationInfo, dataList)
                    }
                }
                "4호선" -> {
                    dataList?.let {
                        processLine(preStationInfo, currentStationInfo, dataList)
                    }
                }
                "5호선" -> {}
                "6호선" -> {
                    dataList?.let {
                        processLine(preStationInfo, currentStationInfo, dataList)
                    }
                }
                "7호선" -> {
                    dataList?.let {
                        processLine(preStationInfo, currentStationInfo, dataList)
                    }
                }
                "8호선" -> {
                    dataList?.let {
                        processLine(preStationInfo, currentStationInfo, dataList)
                    }
                }
                "9호선" -> {
                    dataList?.let {
                        processLine(preStationInfo, currentStationInfo, dataList)
                    }
                }
                "GTX-A" -> {}
                "경강선" -> {}
                "경의선" -> {}
                "경춘선" -> {}
                "공항철도" -> {}
                "김포도시철도" -> {}
                "서해선" -> {}
                "수인분당선" -> {
                    dataList?.let {
                        processLine(preStationInfo, currentStationInfo, dataList)
                    }
                }
                "신림선" -> {}
                "신분당선" -> {}
                "용인경전철" -> {}
                "우이신설경전철" -> {}
                "의정부경전철" -> {}
                "인천2호선" -> {}
                "인천선" -> {}
                else -> {}
            }
        }
    }

    /**
     * 문자열에 "P"가 있다면 제거하고 계산식(-)을 계산하여 결과를 리턴한다.
     */
    private fun calculate(expression: String) : Int{
        var result = -1
        val convExpression = expression.replace("P", "")

        val isMinus = convExpression.contains("-")
        if(isMinus) {
            val parts = convExpression.split("-")
            if(parts.size == 2 && parts[0].isDigitsOnly() && parts[1].isDigitsOnly()) {
                result = parts[0].toInt() - parts[1].toInt()
            }
        } else {
            if(convExpression.isDigitsOnly()) {
                result = convExpression.toInt()
            }
        }

        return result
    }

    /**
     * 결과값이 양수이면 위쪽(신창->구로, 구로->연천 등), 음수이면 아래쪽(연천->구로, 가산->신창)
     */
    private fun calDirection(preData:StationInfo?, currentData:StationInfo): Int {
        var result = 0
        if(preData != null) {
            val preValue = calculate(preData.FR_CODE)
            val currentValue = calculate(currentData.FR_CODE)

            if(preValue > 0 && currentValue > 0) {
                result = preValue - currentValue
            }
        }

        return  result
    }


    private fun calDirection2Line(preData:StationInfo?, currentData:StationInfo, subwayLineInfos: List<SubwayLineInfoEntity>): Int {
        var result = 0
        if(preData != null) {
            var preValue = calculate(preData.FR_CODE)
            val currentValue = calculate(currentData.FR_CODE)

            /*
            *
            * if(currentData.FR_CODE == subwayLineInfos.last().FR_CODE) {
                nextStation = subwayLineInfos.first().toStationInfo()
            } else if (currentData.FR_CODE == subwayLineInfos.first().FR_CODE) {
                nextStation = subwayLineInfos[1].toStationInfo()
            } else {
                nextStation = nextSubwayLineInfoEntity?.toStationInfo()
            }
            * */
            if(preValue > 0 && currentValue > 0) {
                MLog.d("sehwan", "preValue : $preValue, currentValue : $currentValue")
                result = preValue - currentValue
            }
        }

        return  result
    }

    private suspend fun process2Line(preData:StationInfo?, currentData:StationInfo, subwayLineInfos: List<SubwayLineInfoEntity>) {
        // 방향계산이 안되면 진행하지 않는다.
        var result = calDirection2Line(preData, currentData, subwayLineInfos)
        MLog.d("sehwan", "calDirection 이전 : ${preData?.STATION_NM}, 현재 : ${currentData.STATION_NM}")
        MLog.d("sehwan", " calDirection result : $result")

        // 시청, 충정로 처리
        if(preData?.FR_CODE == subwayLineInfos.last().FR_CODE &&
            currentData.FR_CODE == subwayLineInfos.first().FR_CODE) {
            result = -1
        }

        if(preData?.FR_CODE == subwayLineInfos.first().FR_CODE &&
            currentData.FR_CODE == subwayLineInfos.last().FR_CODE) {
            result = 1
        }

        if(result != 0) {
            val nextStation = nextStationIndexFor2Line(currentData, subwayLineInfos, result < 0)

            val isTrasferStation = isTrasferStation(subwayLineInfos, nextStation) // 다음역 환승역체크

            if(isTrasferStation) {
                showNotification("안내", "다음역은 ${nextStation?.STATION_NM}(으)로 환승역 입니다.")
                MyApplication.getInstance().sendLogToActivity("다음역은 ${nextStation?.STATION_NM}(으)로 환승역 입니다.")
            }

            MLog.d("sehwan", "예상 다음역 : ${nextStation?.STATION_NM}")
            //MyApplication.getInstance().sendLogToActivity("예상 다음역 : ${nextStation?.STATION_NM}")

            val selectedSubway = database.selectedSubwayDao().getAllStations() // 선택한 역들
            val distSeletedSubway = selectedSubway.filter { currentData.LINE_NUM.contains(it.lineName) } //선택한 역중에 해당하는 호선만 구별

            val findStation = distSeletedSubway.find {
                it.stationName == nextStation?.STATION_NM
            }

            if(findStation != null) {
                showNotification("안내", "다음역은 ${nextStation?.STATION_NM} 입니다.")
                MyApplication.getInstance().sendLogToActivity("다음역은 ${nextStation?.STATION_NM} 입니다.")
            } else {
                val isTrasferCurrentStation = isTrasferStation(subwayLineInfos, currentData) // 현재역 환승역체크

                if(isTrasferCurrentStation) {
                    val findStation = distSeletedSubway.find { it.statnId == "${currentData.FR_CODE}-1" }
                    if(findStation != null) {
                        showNotification("안내", "다음역은 ${findStation.stationName} 입니다.")
                        MyApplication.getInstance().sendLogToActivity("다음역은 ${nextStation?.STATION_NM} 입니다.")
                    }
                }
            }
        }
    }

    private suspend fun process1Line(preData:StationInfo?, currentData:StationInfo, subwayLineInfos: List<SubwayLineInfoEntity>) {
        // 방향계산이 안되면 진행하지 않는다.
        val result = calDirection(preData, currentData)
        if(result != 0) {
            val nextStation = nextStationIndex(currentData, subwayLineInfos, result < 0)

            val isTrasferStation = isTrasferStation(subwayLineInfos, nextStation) // 다음역 환승역체크

            if(isTrasferStation) {
                showNotification("안내", "다음역은 ${nextStation?.STATION_NM}(으)로 환승역 입니다.")
                MyApplication.getInstance().sendLogToActivity("다음역은 ${nextStation?.STATION_NM}(으)로 환승역 입니다.")
            }

            MLog.d("sehwan", "예상 다음역 : ${nextStation?.STATION_NM}")
            MyApplication.getInstance().sendLogToActivity("예상 다음역 : ${nextStation?.STATION_NM}")

            val selectedSubway = database.selectedSubwayDao().getAllStations() // 선택한 역들
            val distSeletedSubway = selectedSubway.filter { currentData.LINE_NUM.contains(it.lineName) } //선택한 역중에 해당하는 호선만 구별

            val findStation = distSeletedSubway.find { it.stationName == nextStation?.STATION_NM }

            if(findStation != null) {
                showNotification("안내", "다음역은 ${nextStation?.STATION_NM} 입니다.")
                MyApplication.getInstance().sendLogToActivity("다음역은 ${nextStation?.STATION_NM} 입니다.")
            } else {
                val isTrasferCurrentStation = isTrasferStation(subwayLineInfos, currentData) // 현재역 환승역체크

                if(isTrasferCurrentStation) {
                    val findStation = distSeletedSubway.find { it.statnId == "${currentData.FR_CODE}-1" }
                    if(findStation != null) {
                        showNotification("안내", "다음역은 ${findStation.stationName} 입니다.")
                        MyApplication.getInstance().sendLogToActivity("다음역은 ${findStation.stationName} 입니다.")
                    }
                }
            }

        }
    }

    private suspend fun processLine(preData:StationInfo?, currentData:StationInfo, subwayLineInfos: List<SubwayLineInfoEntity>) {
        // 방향계산이 안되면 진행하지 않는다.
        val result = calDirection(preData, currentData)
        if(result != 0) {
            val nextStation = nextStationIndex(currentData, subwayLineInfos, result < 0)

            MLog.d("sehwan", "예상 다음역 : ${nextStation?.STATION_NM}")
            MyApplication.getInstance().sendLogToActivity("예상 다음역 : ${nextStation?.STATION_NM}")

            val selectedSubway = database.selectedSubwayDao().getAllStations() // 선택한 역들
            val distSeletedSubway = selectedSubway.filter { currentData.LINE_NUM.contains(it.lineName) } //선택한 역중에 해당하는 호선만 구별

            val findStation = distSeletedSubway.find { it.stationName == nextStation?.STATION_NM }

            if(findStation != null) {
                showNotification("안내", "다음역은 ${nextStation?.STATION_NM} 입니다.")
                MyApplication.getInstance().sendLogToActivity("다음역은 ${nextStation?.STATION_NM} 입니다.")
            }
        }
    }

    /**
     * subwayLineInfos : 호선역정보 리스트
     * stationInfo 내부의 환승역인지 체크할 역정보
     */
    private fun isTrasferStation(subwayLineInfos: List<SubwayLineInfoEntity>, stationInfo:StationInfo?): Boolean {
        var result = false

        if(stationInfo?.FR_CODE != "100") {
            val isTrasferStation = subwayLineInfos.find {
                it.FR_CODE.contains("${stationInfo?.FR_CODE}-")
            }
            if (isTrasferStation != null || stationInfo?.FR_CODE == "141") {
                result = true
            }
        }

        return result
    }

    /**
     * 현재 코드로 다음 역을 유추한다.
     * 현재역이 분기되는 위치라면 null
     *
     * currentData 현재 역정보
     * subwayLineInfos 해당하는 호선 역 리스트
     * direction 위 -> 아래 : true, 아래 -> 위: false
     */
    private fun nextStationIndex(currentData:StationInfo, subwayLineInfos: List<SubwayLineInfoEntity>, direction:Boolean) : StationInfo? {
        var nextStation:StationInfo? = null

        val firstChar:Char? = currentData.FR_CODE.firstOrNull()?.takeIf { !it.isDigit() }

        if(direction) {
            //위 -> 아래
            val nextSubwayLineInfoEntity = subwayLineInfos.find {
                val findCode = if(firstChar == null) "${calculate(currentData.FR_CODE) +1}" else "$firstChar${calculate(currentData.FR_CODE) +1}"

                val firstCharByItem:Char? = it.FR_CODE.firstOrNull()?.takeIf { char -> !char.isDigit() }
                val num = calculate(it.FR_CODE)
                val code = if(firstCharByItem == null) "$num" else "$firstCharByItem$num"

                code == findCode
            }
            nextStation = nextSubwayLineInfoEntity?.toStationInfo()
        } else {
            // 아래 -> 위
            val nextSubwayLineInfoEntity = subwayLineInfos.find {
                val findCode = if(firstChar == null) "${calculate(currentData.FR_CODE) -1}" else "$firstChar${calculate(currentData.FR_CODE) -1}"

                val firstCharByItem:Char? = it.FR_CODE.firstOrNull()?.takeIf { char -> !char.isDigit() }
                val num = calculate(it.FR_CODE)
                val code = if(firstCharByItem == null) "$num" else "$firstCharByItem$num"

                code == findCode
            }

            nextStation = nextSubwayLineInfoEntity?.toStationInfo()
        }

        return nextStation
    }

    /**
     * 현재 코드로 다음 역을 유추한다.
     * 현재역이 분기되는 위치라면 null
     *
     * currentData 현재 역정보
     * subwayLineInfos 해당하는 호선 역 리스트
     * direction 위 -> 아래 : true, 아래 -> 위: false
     */
    private fun nextStationIndexFor2Line(currentData:StationInfo, subwayLineInfos: List<SubwayLineInfoEntity>, direction:Boolean) : StationInfo? {
        var nextStation:StationInfo? = null

        val firstChar:Char? = currentData.FR_CODE.firstOrNull()?.takeIf { !it.isDigit() }

        if(direction) {
            //위 -> 아래
            val nextSubwayLineInfoEntity = subwayLineInfos.find {
                val findCode = if(firstChar == null) "${calculate(currentData.FR_CODE) +1}" else "$firstChar${calculate(currentData.FR_CODE) +1}"

                val firstCharByItem:Char? = it.FR_CODE.firstOrNull()?.takeIf { char -> !char.isDigit() }
                val num = calculate(it.FR_CODE)
                val code = if(firstCharByItem == null) "$num" else "$firstCharByItem$num"

                code == findCode
            }

            if(currentData.FR_CODE == subwayLineInfos.last().FR_CODE) {
                nextStation = subwayLineInfos.first().toStationInfo()
            } else if (currentData.FR_CODE == subwayLineInfos.first().FR_CODE) {
                nextStation = subwayLineInfos[1].toStationInfo()
            } else {
                nextStation = nextSubwayLineInfoEntity?.toStationInfo()
            }
        } else {
            // 아래 -> 위
            val nextSubwayLineInfoEntity = subwayLineInfos.find {
                val findCode = if(firstChar == null) "${calculate(currentData.FR_CODE) -1}" else "$firstChar${calculate(currentData.FR_CODE) -1}"

                val firstCharByItem:Char? = it.FR_CODE.firstOrNull()?.takeIf { char -> !char.isDigit() }
                val num = calculate(it.FR_CODE)
                val code = if(firstCharByItem == null) "$num" else "$firstCharByItem$num"

                code == findCode
            }

            if (currentData.FR_CODE == subwayLineInfos.first().FR_CODE) {
                nextStation = subwayLineInfos.last().toStationInfo()
            } else {
                nextStation = nextSubwayLineInfoEntity?.toStationInfo()
            }
        }

        return nextStation
    }

    /**
     * 문자열에서 문자와 숫자를 분리해 해당 숫자를 들어온 수 만큼 계산하고 다시 붙힌다.
     * 문자열에서 숫자랑 문자가 번갈아있으면 안된다. 1q2w3e4r
     * str : P123, offset(음/양) 계산
     */
    private fun transStringNumber(str:String, offset:Int): String {
        // 문자열에서 접두사와 숫자를 분리
        val prefix = str.takeWhile { it.isLetter() } // "P"
        val number = str.dropWhile { it.isLetter() }.toInt() // 123

        // 숫자 감소
        val updatedNumber = number + offset // 122

        // 변경된 문자열 생성
        return "$prefix$updatedNumber"
    }

    private fun showNotification(title:String, contents:String) {
        val serviceIntent = Intent(context, MyForegroundService::class.java)
        serviceIntent.action = MyForegroundService.ACTION_REQUEST_SHOW_NOTIFICATION
        serviceIntent.putExtra("TITLE", title)
        serviceIntent.putExtra("CONTENTS", contents)
        context.startService(serviceIntent)
    }

    fun SubwayLineInfoEntity.toStationInfo(): StationInfo {
        return StationInfo(
            STATION_CD = this.STATION_CD,
            STATION_NM = this.STATION_NM,
            LINE_NUM = this.LINE_NUM,
            FR_CODE = this.FR_CODE
        )
    }

    private fun StationInfo.toEntity(): SubwayLineInfoEntity {
        return SubwayLineInfoEntity(
            STATION_CD = this.STATION_CD,
            STATION_NM = this.STATION_NM,
            LINE_NUM = this.LINE_NUM,
            FR_CODE = this.FR_CODE
        )
    }
}
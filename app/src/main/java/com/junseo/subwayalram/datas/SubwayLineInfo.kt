package com.junseo.subwayalram.datas

// 최상위 응답 데이터 클래스
data class SearchSTNBySubwayLineResponse(
    val SearchSTNBySubwayLineInfo: SubwayLineInfo
)

// 지하철 노선 정보 클래스
data class SubwayLineInfo(
    val list_total_count: Int,
    val RESULT: ResultInfo,
    val row: List<StationInfo>
)

// 결과 정보 클래스
data class ResultInfo(
    val CODE: String,
    val MESSAGE: String
)

// 지하철 역 정보 클래스
data class StationInfo(
    val STATION_CD: String,
    val STATION_NM: String,
    val LINE_NUM: String,
    val FR_CODE: String
)


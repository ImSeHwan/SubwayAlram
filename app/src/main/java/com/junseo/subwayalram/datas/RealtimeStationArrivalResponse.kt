package com.junseo.subwayalram.datas

data class RealtimeStationArrivalResponse(
    val realtimeArrivalList: List<ArrivalInfo>
)

data class ArrivalInfo(
    val trainLineNm: String, // 열차 노선명
    val statnFid: String, // 이전 역 ID
    val statnTid: String, // 다음 역 ID
    val statnId: String, // 현재 역 ID
    val bstatnNm: String,    // 현재 열차 위치
    val arvlMsg2: String,    // 도착 메세지 (몇 번째 전역 등)

    ///0: 열차가 현재 열차 선로에 진입하여, 승강장으로 곧 들어올 예정임을 의미합니다.
    ///1: 열차가 승강장에 도착한 상태입니다. (즉, 현재 승객들이 탑승하거나 내리고 있는 상태)
    ///2: 열차가 곧 출발할 예정입니다.
    ///3: 열차가 출발 후 다음 역으로 이동 중임을 의미합니다.
    val arvlCd: String       // 도착 코드 (예: 도착임박)
)
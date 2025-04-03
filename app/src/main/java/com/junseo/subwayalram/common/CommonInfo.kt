package com.junseo.subwayalram.common

import com.junseo.subwayalram.databaseutils.SubwayLineInfoEntity
import com.junseo.subwayalram.databaseutils.SubwayStation

class CommonInfo {
    companion object {
        const val KEY_SUBWAY_INFO_SAVED_DATE = "SUBWAY_INFO_SAVED_DATE"
        const val KEY_SAVED_SELECT_SUBWAY_STATION = "SAVED_SELECT_SUBWAY_STATION"
        const val KEY_SAVED_CHECK_DATE_TIME = "SAVED_CHECK_DATE_TIME"

        const val DATE_FORMAT = "yyyy-MM-dd"

        const val LOCATION_INFORMATION_REQUEST_INTERVAL = 1000 * 10
        const val LOCATION_INFORMATION_REQUEST_MIN_INTERVAL = LOCATION_INFORMATION_REQUEST_INTERVAL / 2
        const val ENTRY_CHECK_RADIUS = 100 // 단위 m

        val testLocation: Array<SubwayStation> = arrayOf(
            SubwayStation(outStnNum = 2546, stationName = "아차산", lineName = "5호선", latitude = 37.5744489, longitude = 126.9578855),
            SubwayStation(outStnNum = 2547, stationName = "광나루", lineName = "5호선", latitude = 37.5758057, longitude = 126.9741147),
            SubwayStation(outStnNum = 2548, stationName = "천호", lineName = "5호선", latitude = 37.570406, longitude = 126.991847),
            SubwayStation(outStnNum = 2549, stationName = "강동", lineName = "5호선", latitude = 37.5660206, longitude = 126.9926685),
            SubwayStation(outStnNum = 2555, stationName = "둔촌동", lineName = "5호선", latitude = 37.5660134, longitude = 126.9926694),
            SubwayStation(outStnNum = 2556, stationName = "올림픽공원", lineName = "5호선", latitude = 37.5608269, longitude = 126.994436),
        )
        /**
         * 호선별 역코드 배열
         */
        var groupedStations:Map<String, List<SubwayLineInfoEntity>>? = null
        val subwayStations3: Array<Array<SubwayStation>> = arrayOf(
            arrayOf(
                SubwayStation(id=582, outStnNum=4130, stationName="종합운동장", lineName="9호선(연장)", latitude=37.511426, longitude=127.076275),
                SubwayStation(id=617, outStnNum=218, stationName="종합운동장", lineName="2호선", latitude=37.511022, longitude=127.073704)
            ),
            arrayOf(
                SubwayStation(id=381, outStnNum=217, stationName="잠실새내", lineName="2호선", latitude=37.511687, longitude=127.086162)
            ),
            arrayOf(
                SubwayStation(id=304, outStnNum=2815, stationName="잠실(송파구청)", lineName="8호선", latitude=37.514692, longitude=127.104338),
                SubwayStation(id=388, outStnNum=216, stationName="잠실(송파구청)", lineName="2호선", latitude=37.513262, longitude=127.100159)
            ),
            arrayOf(
                SubwayStation(id=395, outStnNum=215, stationName="잠실나루", lineName="2호선", latitude=37.520733, longitude=127.10379)
            ),
            arrayOf(
                SubwayStation(id=631, outStnNum=214, stationName="강변(동서울터미널)", lineName="2호선", latitude=37.535095, longitude=127.094681)
            ),
        )


        // 테스트
        val subwayStations2DArray: Array<Array<SubwayStation>> = arrayOf(
            arrayOf(
                SubwayStation(outStnNum = 328, stationName = "연신내", lineName = "3호선", latitude = 37.512759, longitude = 127.01122),
                SubwayStation(outStnNum = 328, stationName = "연신내", lineName = "6호선", latitude = 37.512759, longitude = 127.01122),
            ),
            arrayOf(
                SubwayStation(outStnNum = 324, stationName = "불광", lineName = "3호선", latitude = 37.548034, longitude = 127.015872),
                SubwayStation(outStnNum = 324, stationName = "불광", lineName = "6호선", latitude = 37.548034, longitude = 127.015872),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "녹번", lineName = "3호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "홍제", lineName = "3호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "무악재", lineName = "3호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "독립문", lineName = "3호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "경복궁", lineName = "3호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "안국", lineName = "3호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "종로3가", lineName = "1호선", latitude = 37.566672, longitude = 126.992548),
                SubwayStation(outStnNum = 320, stationName = "종로3가", lineName = "3호선", latitude = 37.566672, longitude = 126.992548),
                SubwayStation(outStnNum = 320, stationName = "종로3가", lineName = "5호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "을지로3", lineName = "2호선", latitude = 37.566672, longitude = 126.992548),
                SubwayStation(outStnNum = 320, stationName = "을지로3", lineName = "3호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "충무로", lineName = "3호선", latitude = 37.566672, longitude = 126.992548),
                SubwayStation(outStnNum = 320, stationName = "충무로", lineName = "4호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "동대입구", lineName = "3호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "약수", lineName = "6호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "금호", lineName = "3호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "압구정", lineName = "3호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "잠원", lineName = "3호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "고속터미널", lineName = "3호선", latitude = 37.566672, longitude = 126.992548),
                SubwayStation(outStnNum = 320, stationName = "고속터미널", lineName = "7호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "교대", lineName = "2호선", latitude = 37.566672, longitude = 126.992548),
                SubwayStation(outStnNum = 320, stationName = "교대", lineName = "3호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "강남", lineName = "2호선", latitude = 37.566672, longitude = 126.992548),
                SubwayStation(outStnNum = 320, stationName = "강남", lineName = "신분당선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "역삼", lineName = "2호선", latitude = 37.566672, longitude = 126.992548),
            ),
            arrayOf(
                SubwayStation(outStnNum = 320, stationName = "선릉", lineName = "2호선", latitude = 37.566672, longitude = 126.992548),
                SubwayStation(outStnNum = 320, stationName = "선릉", lineName = "수인분당선", latitude = 37.566672, longitude = 126.992548),
            ),
        )

        //https://data.seoul.go.kr/dataList/OA-12764/A/1/datasetView.do
        // 실시간 지하철 도착 API KEY 공공데이터(http://swopenapi.seoul.go.kr/api/subway/565553514d736568313139654c615564/json/realtimeStationArrival/0/10/%EC%97%B0%EC%8B%A0%EB%82%B4)
        const val SUBWAY_REAL_TIME_ARRIVAL_INFORMATION_KEY = "565553514d736568313139654c615564" 
        const val SUBWAY_STATION_LIST_KEY = "57f2b9e0-a6f9-4c3c-ad84-ca40fe171194" // 지하철역 목록 API KEY
        const val SUBWAY_STATION_INFO_LIST = "[{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000100,\"STATN_NM\":\"소요산\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000101,\"STATN_NM\":\"동두천\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000102,\"STATN_NM\":\"보산\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000103,\"STATN_NM\":\"동두천중앙\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000104,\"STATN_NM\":\"지행\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000105,\"STATN_NM\":\"덕정\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000106,\"STATN_NM\":\"덕계\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000107,\"STATN_NM\":\"양주\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000108,\"STATN_NM\":\"녹양\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000109,\"STATN_NM\":\"가능\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000110,\"STATN_NM\":\"의정부\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000111,\"STATN_NM\":\"회룡\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000112,\"STATN_NM\":\"망월사\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000113,\"STATN_NM\":\"도봉산\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000114,\"STATN_NM\":\"도봉\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000115,\"STATN_NM\":\"방학\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000116,\"STATN_NM\":\"창동\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000117,\"STATN_NM\":\"녹천\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000118,\"STATN_NM\":\"월계\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000119,\"STATN_NM\":\"광운대\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000120,\"STATN_NM\":\"석계\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000121,\"STATN_NM\":\"신이문\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000122,\"STATN_NM\":\"외대앞\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000123,\"STATN_NM\":\"회기\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000124,\"STATN_NM\":\"청량리\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000125,\"STATN_NM\":\"제기동\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000126,\"STATN_NM\":\"신설동\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000127,\"STATN_NM\":\"동묘앞\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000128,\"STATN_NM\":\"동대문\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000129,\"STATN_NM\":\"종로5가\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000130,\"STATN_NM\":\"종로3가\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000131,\"STATN_NM\":\"종각\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000132,\"STATN_NM\":\"시청\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000133,\"STATN_NM\":\"서울\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000134,\"STATN_NM\":\"남영\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000135,\"STATN_NM\":\"용산\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000136,\"STATN_NM\":\"노량진\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000137,\"STATN_NM\":\"대방\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000138,\"STATN_NM\":\"신길\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000139,\"STATN_NM\":\"영등포\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000140,\"STATN_NM\":\"신도림\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000141,\"STATN_NM\":\"구로\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000142,\"STATN_NM\":\"구일\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000143,\"STATN_NM\":\"개봉\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000144,\"STATN_NM\":\"오류동\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000145,\"STATN_NM\":\"온수\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000146,\"STATN_NM\":\"역곡\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000147,\"STATN_NM\":\"소사\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000148,\"STATN_NM\":\"부천\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000149,\"STATN_NM\":\"중동\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000150,\"STATN_NM\":\"송내\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000151,\"STATN_NM\":\"부개\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000152,\"STATN_NM\":\"부평\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000153,\"STATN_NM\":\"백운\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000154,\"STATN_NM\":\"동암\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000155,\"STATN_NM\":\"간석\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000156,\"STATN_NM\":\"주안\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000157,\"STATN_NM\":\"도화\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000158,\"STATN_NM\":\"제물포\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000159,\"STATN_NM\":\"도원\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000160,\"STATN_NM\":\"동인천\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001000161,\"STATN_NM\":\"인천\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001001001,\"STATN_NM\":\"청산\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001001002,\"STATN_NM\":\"전곡\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001001003,\"STATN_NM\":\"연천\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001075410,\"STATN_NM\":\"광명\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080142,\"STATN_NM\":\"가산디지털단지\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080143,\"STATN_NM\":\"독산\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080144,\"STATN_NM\":\"금천구청\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080145,\"STATN_NM\":\"석수\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080146,\"STATN_NM\":\"관악\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080147,\"STATN_NM\":\"안양\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080148,\"STATN_NM\":\"명학\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080149,\"STATN_NM\":\"금정\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080150,\"STATN_NM\":\"군포\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080151,\"STATN_NM\":\"당정\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080152,\"STATN_NM\":\"의왕\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080153,\"STATN_NM\":\"성균관대\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080154,\"STATN_NM\":\"화서\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080155,\"STATN_NM\":\"수원\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080156,\"STATN_NM\":\"세류\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080157,\"STATN_NM\":\"병점\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080158,\"STATN_NM\":\"세마\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080159,\"STATN_NM\":\"오산대\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080160,\"STATN_NM\":\"오산\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080161,\"STATN_NM\":\"진위\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080162,\"STATN_NM\":\"송탄\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080163,\"STATN_NM\":\"서정리\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080164,\"STATN_NM\":\"지제\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080165,\"STATN_NM\":\"평택\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080166,\"STATN_NM\":\"성환\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080167,\"STATN_NM\":\"직산\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080168,\"STATN_NM\":\"두정\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080169,\"STATN_NM\":\"천안\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080170,\"STATN_NM\":\"봉명\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080171,\"STATN_NM\":\"쌍용(나사렛대)\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080172,\"STATN_NM\":\"아산\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080173,\"STATN_NM\":\"탕정\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080174,\"STATN_NM\":\"배방\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080175,\"STATN_NM\":\"온양온천\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001080176,\"STATN_NM\":\"신창\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1001,\"STATN_ID\":1001801571,\"STATN_NM\":\"서동탄\",\"LINE_NAME\":\"1호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000201,\"STATN_NM\":\"시청\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000202,\"STATN_NM\":\"을지로입구\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000203,\"STATN_NM\":\"을지로3가\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000204,\"STATN_NM\":\"을지로4가\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000205,\"STATN_NM\":\"동대문역사문화공원\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000206,\"STATN_NM\":\"신당\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000207,\"STATN_NM\":\"상왕십리\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000208,\"STATN_NM\":\"왕십리\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000209,\"STATN_NM\":\"한양대\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000210,\"STATN_NM\":\"뚝섬\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000211,\"STATN_NM\":\"성수\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000212,\"STATN_NM\":\"건대입구\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000213,\"STATN_NM\":\"구의\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000214,\"STATN_NM\":\"강변\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000215,\"STATN_NM\":\"잠실나루\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000216,\"STATN_NM\":\"잠실\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000217,\"STATN_NM\":\"잠실새내\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000218,\"STATN_NM\":\"종합운동장\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000219,\"STATN_NM\":\"삼성\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000220,\"STATN_NM\":\"선릉\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000221,\"STATN_NM\":\"역삼\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000222,\"STATN_NM\":\"강남\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000223,\"STATN_NM\":\"교대\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000224,\"STATN_NM\":\"서초\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000225,\"STATN_NM\":\"방배\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000226,\"STATN_NM\":\"사당\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000227,\"STATN_NM\":\"낙성대\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000228,\"STATN_NM\":\"서울대입구\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000229,\"STATN_NM\":\"봉천\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000230,\"STATN_NM\":\"신림\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000231,\"STATN_NM\":\"신대방\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000232,\"STATN_NM\":\"구로디지털단지\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000233,\"STATN_NM\":\"대림\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000234,\"STATN_NM\":\"신도림\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000235,\"STATN_NM\":\"문래\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000236,\"STATN_NM\":\"영등포구청\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000237,\"STATN_NM\":\"당산\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000238,\"STATN_NM\":\"합정\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000239,\"STATN_NM\":\"홍대입구\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000240,\"STATN_NM\":\"신촌\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000241,\"STATN_NM\":\"이대\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000242,\"STATN_NM\":\"아현\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002000243,\"STATN_NM\":\"충정로\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002002111,\"STATN_NM\":\"용답\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002002112,\"STATN_NM\":\"신답\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002002113,\"STATN_NM\":\"용두\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002002114,\"STATN_NM\":\"신설동\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002002341,\"STATN_NM\":\"도림천\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002002342,\"STATN_NM\":\"양천구청\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002002343,\"STATN_NM\":\"신정네거리\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1002,\"STATN_ID\":1002002344,\"STATN_NM\":\"까치산\",\"LINE_NAME\":\"2호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000309,\"STATN_NM\":\"대화\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000310,\"STATN_NM\":\"주엽\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000311,\"STATN_NM\":\"정발산\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000312,\"STATN_NM\":\"마두\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000313,\"STATN_NM\":\"백석\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000314,\"STATN_NM\":\"대곡\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000315,\"STATN_NM\":\"화정\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000316,\"STATN_NM\":\"원당\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000317,\"STATN_NM\":\"원흥\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000318,\"STATN_NM\":\"삼송\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000319,\"STATN_NM\":\"지축\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000320,\"STATN_NM\":\"구파발\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000321,\"STATN_NM\":\"연신내\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000322,\"STATN_NM\":\"불광\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000323,\"STATN_NM\":\"녹번\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000324,\"STATN_NM\":\"홍제\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000325,\"STATN_NM\":\"무악재\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000326,\"STATN_NM\":\"독립문\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000327,\"STATN_NM\":\"경복궁\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000328,\"STATN_NM\":\"안국\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000329,\"STATN_NM\":\"종로3가\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000330,\"STATN_NM\":\"을지로3가\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000331,\"STATN_NM\":\"충무로\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000332,\"STATN_NM\":\"동대입구\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000333,\"STATN_NM\":\"약수\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000334,\"STATN_NM\":\"금호\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000335,\"STATN_NM\":\"옥수\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000336,\"STATN_NM\":\"압구정\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000337,\"STATN_NM\":\"신사\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000338,\"STATN_NM\":\"잠원\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000339,\"STATN_NM\":\"고속터미널\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000340,\"STATN_NM\":\"교대\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000341,\"STATN_NM\":\"남부터미널\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000342,\"STATN_NM\":\"양재\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000343,\"STATN_NM\":\"매봉\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000344,\"STATN_NM\":\"도곡\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000345,\"STATN_NM\":\"대치\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000346,\"STATN_NM\":\"학여울\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000347,\"STATN_NM\":\"대청\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000348,\"STATN_NM\":\"일원\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000349,\"STATN_NM\":\"수서\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000350,\"STATN_NM\":\"가락시장\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000351,\"STATN_NM\":\"경찰병원\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1003,\"STATN_ID\":1003000352,\"STATN_NM\":\"오금\",\"LINE_NAME\":\"3호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000409,\"STATN_NM\":\"당고개\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000410,\"STATN_NM\":\"상계\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000411,\"STATN_NM\":\"노원\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000412,\"STATN_NM\":\"창동\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000413,\"STATN_NM\":\"쌍문\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000414,\"STATN_NM\":\"수유\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000415,\"STATN_NM\":\"미아\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000416,\"STATN_NM\":\"미아사거리\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000417,\"STATN_NM\":\"길음\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000418,\"STATN_NM\":\"성신여대입구\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000419,\"STATN_NM\":\"한성대입구\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000420,\"STATN_NM\":\"혜화\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000421,\"STATN_NM\":\"동대문\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000422,\"STATN_NM\":\"동대문역사문화공원\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000423,\"STATN_NM\":\"충무로\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000424,\"STATN_NM\":\"명동\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000425,\"STATN_NM\":\"회현\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000426,\"STATN_NM\":\"서울\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000427,\"STATN_NM\":\"숙대입구\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000428,\"STATN_NM\":\"삼각지\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000429,\"STATN_NM\":\"신용산\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000430,\"STATN_NM\":\"이촌\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000431,\"STATN_NM\":\"동작\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000432,\"STATN_NM\":\"총신대입구(이수)\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000433,\"STATN_NM\":\"사당\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000434,\"STATN_NM\":\"남태령\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000435,\"STATN_NM\":\"선바위\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000436,\"STATN_NM\":\"경마공원\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000437,\"STATN_NM\":\"대공원\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000438,\"STATN_NM\":\"과천\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000439,\"STATN_NM\":\"정부과천청사\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000440,\"STATN_NM\":\"인덕원\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000441,\"STATN_NM\":\"평촌\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000442,\"STATN_NM\":\"범계\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000443,\"STATN_NM\":\"금정\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000444,\"STATN_NM\":\"산본\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000445,\"STATN_NM\":\"수리산\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000446,\"STATN_NM\":\"대야미\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000447,\"STATN_NM\":\"반월\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000448,\"STATN_NM\":\"상록수\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000449,\"STATN_NM\":\"한대앞\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000450,\"STATN_NM\":\"중앙\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000451,\"STATN_NM\":\"고잔\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000452,\"STATN_NM\":\"초지\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000453,\"STATN_NM\":\"안산\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000454,\"STATN_NM\":\"신길온천\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000455,\"STATN_NM\":\"정왕\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1004,\"STATN_ID\":1004000456,\"STATN_NM\":\"오이도\",\"LINE_NAME\":\"4호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000510,\"STATN_NM\":\"방화\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000511,\"STATN_NM\":\"개화산\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000512,\"STATN_NM\":\"김포공항\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000513,\"STATN_NM\":\"송정\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000514,\"STATN_NM\":\"마곡\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000515,\"STATN_NM\":\"발산\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000516,\"STATN_NM\":\"우장산\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000517,\"STATN_NM\":\"화곡\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000518,\"STATN_NM\":\"까치산\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000519,\"STATN_NM\":\"신정(은행정)\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000520,\"STATN_NM\":\"목동\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000521,\"STATN_NM\":\"오목교(목동운동장앞)\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000522,\"STATN_NM\":\"양평\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000523,\"STATN_NM\":\"영등포구청\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000524,\"STATN_NM\":\"영등포시장\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000525,\"STATN_NM\":\"신길\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000526,\"STATN_NM\":\"여의도\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000527,\"STATN_NM\":\"여의나루\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000528,\"STATN_NM\":\"마포\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000529,\"STATN_NM\":\"공덕\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000530,\"STATN_NM\":\"애오개\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000531,\"STATN_NM\":\"충정로\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000532,\"STATN_NM\":\"서대문\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000533,\"STATN_NM\":\"광화문\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000534,\"STATN_NM\":\"종로3가\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000535,\"STATN_NM\":\"을지로4가\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000536,\"STATN_NM\":\"동대문역사문화공원\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000537,\"STATN_NM\":\"청구\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000538,\"STATN_NM\":\"신금호\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000539,\"STATN_NM\":\"행당\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000540,\"STATN_NM\":\"왕십리\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000541,\"STATN_NM\":\"마장\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000542,\"STATN_NM\":\"답십리\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000543,\"STATN_NM\":\"장한평\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000544,\"STATN_NM\":\"군자(능동)\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000545,\"STATN_NM\":\"아차산(어린이대공원후문)\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000546,\"STATN_NM\":\"광나루(장신대)\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000547,\"STATN_NM\":\"천호(풍납토성)\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000548,\"STATN_NM\":\"강동\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000549,\"STATN_NM\":\"길동\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000550,\"STATN_NM\":\"굽은다리(강동구민회관앞)\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000551,\"STATN_NM\":\"명일\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000552,\"STATN_NM\":\"고덕\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000553,\"STATN_NM\":\"상일동\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000554,\"STATN_NM\":\"강일\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000555,\"STATN_NM\":\"미사\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000556,\"STATN_NM\":\"하남풍산\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000557,\"STATN_NM\":\"하남시청\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005000558,\"STATN_NM\":\"하남검단산\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005080549,\"STATN_NM\":\"둔촌동\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005080550,\"STATN_NM\":\"올림픽공원(한국체대)\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005080551,\"STATN_NM\":\"방이\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005080552,\"STATN_NM\":\"오금\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005080553,\"STATN_NM\":\"개롱\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005080554,\"STATN_NM\":\"거여\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1005,\"STATN_ID\":1005080555,\"STATN_NM\":\"마천\",\"LINE_NAME\":\"5호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000610,\"STATN_NM\":\"응암순환(상선)\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000611,\"STATN_NM\":\"역촌\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000612,\"STATN_NM\":\"불광\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000613,\"STATN_NM\":\"독바위\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000614,\"STATN_NM\":\"연신내\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000615,\"STATN_NM\":\"구산\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000616,\"STATN_NM\":\"새절(신사)\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000617,\"STATN_NM\":\"증산(명지대앞)\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000618,\"STATN_NM\":\"디지털미디어시티\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000619,\"STATN_NM\":\"월드컵경기장(성산)\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000620,\"STATN_NM\":\"마포구청\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000621,\"STATN_NM\":\"망원\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000622,\"STATN_NM\":\"합정\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000623,\"STATN_NM\":\"상수\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000624,\"STATN_NM\":\"광흥창\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000625,\"STATN_NM\":\"대흥(서강대앞)\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000626,\"STATN_NM\":\"공덕\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000627,\"STATN_NM\":\"효창공원앞\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000628,\"STATN_NM\":\"삼각지\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000629,\"STATN_NM\":\"녹사평\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000630,\"STATN_NM\":\"이태원\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000631,\"STATN_NM\":\"한강진\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000632,\"STATN_NM\":\"버티고개\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000633,\"STATN_NM\":\"약수\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000634,\"STATN_NM\":\"청구\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000635,\"STATN_NM\":\"신당\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000636,\"STATN_NM\":\"동묘앞\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000637,\"STATN_NM\":\"창신\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000638,\"STATN_NM\":\"보문\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000639,\"STATN_NM\":\"안암(고대병원앞)\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000640,\"STATN_NM\":\"고려대\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000641,\"STATN_NM\":\"월곡(동덕여대)\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000642,\"STATN_NM\":\"상월곡(한국과학기술연구원)\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000643,\"STATN_NM\":\"돌곶이\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000644,\"STATN_NM\":\"석계\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000645,\"STATN_NM\":\"태릉입구\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000646,\"STATN_NM\":\"화랑대(서울여대입구)\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000647,\"STATN_NM\":\"봉화산\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1006,\"STATN_ID\":1006000648,\"STATN_NM\":\"신내\",\"LINE_NAME\":\"6호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000709,\"STATN_NM\":\"장암\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000710,\"STATN_NM\":\"도봉산\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000711,\"STATN_NM\":\"수락산\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000712,\"STATN_NM\":\"마들\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000713,\"STATN_NM\":\"노원\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000714,\"STATN_NM\":\"중계\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000715,\"STATN_NM\":\"하계\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000716,\"STATN_NM\":\"공릉(서울산업대입구)\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000717,\"STATN_NM\":\"태릉입구\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000718,\"STATN_NM\":\"먹골\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000719,\"STATN_NM\":\"중화\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000720,\"STATN_NM\":\"상봉\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000721,\"STATN_NM\":\"면목\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000722,\"STATN_NM\":\"사가정\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000723,\"STATN_NM\":\"용마산\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000724,\"STATN_NM\":\"중곡\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000725,\"STATN_NM\":\"군자(능동)\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000726,\"STATN_NM\":\"어린이대공원(세종대)\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000727,\"STATN_NM\":\"건대입구\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000728,\"STATN_NM\":\"뚝섬유원지\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000729,\"STATN_NM\":\"청담\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000730,\"STATN_NM\":\"강남구청\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000731,\"STATN_NM\":\"학동\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000732,\"STATN_NM\":\"논현\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000733,\"STATN_NM\":\"반포\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000734,\"STATN_NM\":\"고속터미널\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000735,\"STATN_NM\":\"내방\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000736,\"STATN_NM\":\"총신대입구(이수)\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000737,\"STATN_NM\":\"남성\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000738,\"STATN_NM\":\"숭실대입구(살피재)\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000739,\"STATN_NM\":\"상도(중앙대앞)\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000740,\"STATN_NM\":\"장승배기\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000741,\"STATN_NM\":\"신대방삼거리\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000742,\"STATN_NM\":\"보라매\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000743,\"STATN_NM\":\"신풍\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000744,\"STATN_NM\":\"대림\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000745,\"STATN_NM\":\"남구로\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000746,\"STATN_NM\":\"가산디지털단지\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000747,\"STATN_NM\":\"철산\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000748,\"STATN_NM\":\"광명사거리\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000749,\"STATN_NM\":\"천왕\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000750,\"STATN_NM\":\"온수\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000751,\"STATN_NM\":\"까치울\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000752,\"STATN_NM\":\"부천종합운동장\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000753,\"STATN_NM\":\"춘의\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000754,\"STATN_NM\":\"신중동\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000755,\"STATN_NM\":\"부천시청\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000756,\"STATN_NM\":\"상동\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000757,\"STATN_NM\":\"삼산체육관\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000758,\"STATN_NM\":\"굴포천\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000759,\"STATN_NM\":\"부평구청\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000760,\"STATN_NM\":\"산곡\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1007,\"STATN_ID\":1007000761,\"STATN_NM\":\"석남\",\"LINE_NAME\":\"7호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000804,\"STATN_NM\":\"별내\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000805,\"STATN_NM\":\"다산\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000806,\"STATN_NM\":\"동구릉\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000807,\"STATN_NM\":\"구리\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000808,\"STATN_NM\":\"장자호수공원\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000809,\"STATN_NM\":\"암사역사공원\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000810,\"STATN_NM\":\"암사\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000811,\"STATN_NM\":\"천호(풍납토성)\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000812,\"STATN_NM\":\"강동구청\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000813,\"STATN_NM\":\"몽촌토성(평화의문)\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000814,\"STATN_NM\":\"잠실\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000815,\"STATN_NM\":\"석촌\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000816,\"STATN_NM\":\"송파\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000817,\"STATN_NM\":\"가락시장\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000818,\"STATN_NM\":\"문정\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000819,\"STATN_NM\":\"장지\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000820,\"STATN_NM\":\"복정\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000821,\"STATN_NM\":\"남위례\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000822,\"STATN_NM\":\"산성\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000823,\"STATN_NM\":\"남한산성입구(성남법원,검찰청)\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000824,\"STATN_NM\":\"단대오거리\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000825,\"STATN_NM\":\"신흥\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000826,\"STATN_NM\":\"수진\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1008,\"STATN_ID\":1008000827,\"STATN_NM\":\"모란\",\"LINE_NAME\":\"8호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000901,\"STATN_NM\":\"개화\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000902,\"STATN_NM\":\"김포공항\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000903,\"STATN_NM\":\"공항시장\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000904,\"STATN_NM\":\"신방화\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000905,\"STATN_NM\":\"마곡나루\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000906,\"STATN_NM\":\"양천향교\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000907,\"STATN_NM\":\"가양\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000908,\"STATN_NM\":\"증미\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000909,\"STATN_NM\":\"등촌\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000910,\"STATN_NM\":\"염창\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000911,\"STATN_NM\":\"신목동\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000912,\"STATN_NM\":\"선유도\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000913,\"STATN_NM\":\"당산\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000914,\"STATN_NM\":\"국회의사당\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000915,\"STATN_NM\":\"여의도\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000916,\"STATN_NM\":\"샛강\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000917,\"STATN_NM\":\"노량진\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000918,\"STATN_NM\":\"노들\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000919,\"STATN_NM\":\"흑석\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000920,\"STATN_NM\":\"동작\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000921,\"STATN_NM\":\"구반포\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000922,\"STATN_NM\":\"신반포\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000923,\"STATN_NM\":\"고속터미널\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000924,\"STATN_NM\":\"사평\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000925,\"STATN_NM\":\"신논현\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000926,\"STATN_NM\":\"언주\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000927,\"STATN_NM\":\"선정릉\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000928,\"STATN_NM\":\"삼성중앙\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000929,\"STATN_NM\":\"봉은사\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000930,\"STATN_NM\":\"종합운동장\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000931,\"STATN_NM\":\"삼전\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000932,\"STATN_NM\":\"석촌고분\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000933,\"STATN_NM\":\"석촌\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000934,\"STATN_NM\":\"송파나루\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000935,\"STATN_NM\":\"한성백제\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000936,\"STATN_NM\":\"올림픽공원\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000937,\"STATN_NM\":\"둔촌오륜\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1009,\"STATN_ID\":1009000938,\"STATN_NM\":\"중앙보훈병원\",\"LINE_NAME\":\"9호선\"},\n" +
                "{\"SUBWAY_ID\":1032,\"STATN_ID\":1032000353,\"STATN_NM\":\"수서\",\"LINE_NAME\":\"GTX-A\"},\n" +
                "{\"SUBWAY_ID\":1032,\"STATN_ID\":1032000354,\"STATN_NM\":\"성남\",\"LINE_NAME\":\"GTX-A\"},\n" +
                "{\"SUBWAY_ID\":1032,\"STATN_ID\":1032000355,\"STATN_NM\":\"구성\",\"LINE_NAME\":\"GTX-A\"},\n" +
                "{\"SUBWAY_ID\":1032,\"STATN_ID\":1032000356,\"STATN_NM\":\"동탄\",\"LINE_NAME\":\"GTX-A\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075110,\"STATN_NM\":\"용산\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075111,\"STATN_NM\":\"이촌\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075112,\"STATN_NM\":\"서빙고\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075113,\"STATN_NM\":\"한남\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075114,\"STATN_NM\":\"옥수\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075115,\"STATN_NM\":\"응봉\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075116,\"STATN_NM\":\"왕십리\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075117,\"STATN_NM\":\"청량리\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075118,\"STATN_NM\":\"회기\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075119,\"STATN_NM\":\"중랑\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075120,\"STATN_NM\":\"상봉\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075121,\"STATN_NM\":\"망우\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075122,\"STATN_NM\":\"양원\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075123,\"STATN_NM\":\"구리\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075124,\"STATN_NM\":\"도농\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075125,\"STATN_NM\":\"양정\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075126,\"STATN_NM\":\"덕소\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075127,\"STATN_NM\":\"도심\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075128,\"STATN_NM\":\"팔당\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075129,\"STATN_NM\":\"운길산\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075130,\"STATN_NM\":\"양수\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075131,\"STATN_NM\":\"신원\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075132,\"STATN_NM\":\"국수\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075133,\"STATN_NM\":\"아신\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075134,\"STATN_NM\":\"오빈\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075135,\"STATN_NM\":\"양평\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075136,\"STATN_NM\":\"원덕\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075137,\"STATN_NM\":\"용문\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075138,\"STATN_NM\":\"지평\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075312,\"STATN_NM\":\"공덕\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075313,\"STATN_NM\":\"서강대\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075314,\"STATN_NM\":\"홍대입구\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075315,\"STATN_NM\":\"가좌\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075316,\"STATN_NM\":\"디지털미디어시티\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075317,\"STATN_NM\":\"수색\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075318,\"STATN_NM\":\"한국항공대\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075319,\"STATN_NM\":\"강매\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075320,\"STATN_NM\":\"행신\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075321,\"STATN_NM\":\"능곡\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075322,\"STATN_NM\":\"대곡\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075323,\"STATN_NM\":\"곡산\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075324,\"STATN_NM\":\"백마\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075325,\"STATN_NM\":\"풍산\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075326,\"STATN_NM\":\"일산\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075327,\"STATN_NM\":\"탄현\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075328,\"STATN_NM\":\"야당\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075329,\"STATN_NM\":\"운정\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075330,\"STATN_NM\":\"금릉\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075331,\"STATN_NM\":\"금촌\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075333,\"STATN_NM\":\"월롱\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075334,\"STATN_NM\":\"파주\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075335,\"STATN_NM\":\"문산\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075336,\"STATN_NM\":\"운천\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075337,\"STATN_NM\":\"임진강\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063075826,\"STATN_NM\":\"효창공원앞\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063080312,\"STATN_NM\":\"신촌(경의중앙선)\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1063,\"STATN_ID\":1063080313,\"STATN_NM\":\"서울\",\"LINE_NAME\":\"경의중앙선\"},\n" +
                "{\"SUBWAY_ID\":1065,\"STATN_ID\":1065006501,\"STATN_NM\":\"서울\",\"LINE_NAME\":\"공항철도\"},\n" +
                "{\"SUBWAY_ID\":1065,\"STATN_ID\":1065006502,\"STATN_NM\":\"공덕\",\"LINE_NAME\":\"공항철도\"},\n" +
                "{\"SUBWAY_ID\":1065,\"STATN_ID\":1065006503,\"STATN_NM\":\"홍대입구\",\"LINE_NAME\":\"공항철도\"},\n" +
                "{\"SUBWAY_ID\":1065,\"STATN_ID\":1065006504,\"STATN_NM\":\"디지털미디어시티\",\"LINE_NAME\":\"공항철도\"},\n" +
                "{\"SUBWAY_ID\":1065,\"STATN_ID\":1065006505,\"STATN_NM\":\"김포공항\",\"LINE_NAME\":\"공항철도\"},\n" +
                "{\"SUBWAY_ID\":1065,\"STATN_ID\":1065006506,\"STATN_NM\":\"계양\",\"LINE_NAME\":\"공항철도\"},\n" +
                "{\"SUBWAY_ID\":1065,\"STATN_ID\":1065006507,\"STATN_NM\":\"검암\",\"LINE_NAME\":\"공항철도\"},\n" +
                "{\"SUBWAY_ID\":1065,\"STATN_ID\":1065006508,\"STATN_NM\":\"운서\",\"LINE_NAME\":\"공항철도\"},\n" +
                "{\"SUBWAY_ID\":1065,\"STATN_ID\":1065006509,\"STATN_NM\":\"공항화물청사\",\"LINE_NAME\":\"공항철도\"},\n" +
                "{\"SUBWAY_ID\":1065,\"STATN_ID\":1065006510,\"STATN_NM\":\"인천공항1터미널\",\"LINE_NAME\":\"공항철도\"},\n" +
                "{\"SUBWAY_ID\":1065,\"STATN_ID\":1065006511,\"STATN_NM\":\"인천공항2터미널\",\"LINE_NAME\":\"공항철도\"},\n" +
                "{\"SUBWAY_ID\":1065,\"STATN_ID\":1065065042,\"STATN_NM\":\"마곡나루\",\"LINE_NAME\":\"공항철도\"},\n" +
                "{\"SUBWAY_ID\":1065,\"STATN_ID\":1065065071,\"STATN_NM\":\"청라국제도시\",\"LINE_NAME\":\"공항철도\"},\n" +
                "{\"SUBWAY_ID\":1065,\"STATN_ID\":1065065072,\"STATN_NM\":\"영종\",\"LINE_NAME\":\"공항철도\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080116,\"STATN_NM\":\"청량리\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080117,\"STATN_NM\":\"회기\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080118,\"STATN_NM\":\"중랑\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080119,\"STATN_NM\":\"광운대\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080120,\"STATN_NM\":\"상봉\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080121,\"STATN_NM\":\"망우\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080122,\"STATN_NM\":\"신내\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080123,\"STATN_NM\":\"갈매\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080124,\"STATN_NM\":\"별내\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080125,\"STATN_NM\":\"퇴계원\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080126,\"STATN_NM\":\"사릉\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080127,\"STATN_NM\":\"금곡\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080128,\"STATN_NM\":\"평내호평\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080129,\"STATN_NM\":\"천마산\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080130,\"STATN_NM\":\"마석\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080131,\"STATN_NM\":\"대성리\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080132,\"STATN_NM\":\"청평\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080133,\"STATN_NM\":\"상천\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080134,\"STATN_NM\":\"가평\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080135,\"STATN_NM\":\"굴봉산\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080136,\"STATN_NM\":\"백양리\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080137,\"STATN_NM\":\"강촌\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080138,\"STATN_NM\":\"김유정\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080139,\"STATN_NM\":\"남춘천\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1067,\"STATN_ID\":1067080140,\"STATN_NM\":\"춘천\",\"LINE_NAME\":\"경춘선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075209,\"STATN_NM\":\"청량리\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075210,\"STATN_NM\":\"왕십리\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075211,\"STATN_NM\":\"서울숲\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075212,\"STATN_NM\":\"압구정로데오\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075213,\"STATN_NM\":\"강남구청\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075214,\"STATN_NM\":\"선정릉\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075215,\"STATN_NM\":\"선릉\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075216,\"STATN_NM\":\"한티\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075217,\"STATN_NM\":\"도곡\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075218,\"STATN_NM\":\"구룡\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075219,\"STATN_NM\":\"개포동\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075220,\"STATN_NM\":\"대모산입구\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075221,\"STATN_NM\":\"수서\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075222,\"STATN_NM\":\"복정\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075223,\"STATN_NM\":\"가천대\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075224,\"STATN_NM\":\"태평\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075225,\"STATN_NM\":\"모란\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075226,\"STATN_NM\":\"야탑\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075227,\"STATN_NM\":\"이매\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075228,\"STATN_NM\":\"서현\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075229,\"STATN_NM\":\"수내\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075230,\"STATN_NM\":\"정자\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075231,\"STATN_NM\":\"미금\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075232,\"STATN_NM\":\"오리\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075233,\"STATN_NM\":\"죽전\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075234,\"STATN_NM\":\"보정\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075235,\"STATN_NM\":\"구성\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075236,\"STATN_NM\":\"신갈\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075237,\"STATN_NM\":\"기흥\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075238,\"STATN_NM\":\"상갈\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075239,\"STATN_NM\":\"청명\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075240,\"STATN_NM\":\"영통\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075241,\"STATN_NM\":\"망포\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075242,\"STATN_NM\":\"매탄권선\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075243,\"STATN_NM\":\"수원시청\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075244,\"STATN_NM\":\"매교\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075245,\"STATN_NM\":\"수원\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075246,\"STATN_NM\":\"고색\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075247,\"STATN_NM\":\"오목천\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075248,\"STATN_NM\":\"어천\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075249,\"STATN_NM\":\"야목\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075250,\"STATN_NM\":\"사리\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075251,\"STATN_NM\":\"한대앞\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075252,\"STATN_NM\":\"중앙\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075253,\"STATN_NM\":\"고잔\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075254,\"STATN_NM\":\"초지\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075255,\"STATN_NM\":\"안산\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075256,\"STATN_NM\":\"신길온천\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075257,\"STATN_NM\":\"정왕\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075258,\"STATN_NM\":\"오이도\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075259,\"STATN_NM\":\"달월\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075260,\"STATN_NM\":\"월곶\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075261,\"STATN_NM\":\"소래포구\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075262,\"STATN_NM\":\"인천논현\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075263,\"STATN_NM\":\"호구포\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075264,\"STATN_NM\":\"남동인더스파크\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075265,\"STATN_NM\":\"원인재\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075266,\"STATN_NM\":\"연수\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075267,\"STATN_NM\":\"송도\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075268,\"STATN_NM\":\"인하대\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075269,\"STATN_NM\":\"숭의\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075270,\"STATN_NM\":\"신포\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1075,\"STATN_ID\":1075075271,\"STATN_NM\":\"인천\",\"LINE_NAME\":\"수인분당선\"},\n" +
                "{\"SUBWAY_ID\":1077,\"STATN_ID\":1077000684,\"STATN_NM\":\"신사\",\"LINE_NAME\":\"신분당선\"},\n" +
                "{\"SUBWAY_ID\":1077,\"STATN_ID\":1077000685,\"STATN_NM\":\"논현\",\"LINE_NAME\":\"신분당선\"},\n" +
                "{\"SUBWAY_ID\":1077,\"STATN_ID\":1077000686,\"STATN_NM\":\"신논현\",\"LINE_NAME\":\"신분당선\"},\n" +
                "{\"SUBWAY_ID\":1077,\"STATN_ID\":1077000687,\"STATN_NM\":\"강남\",\"LINE_NAME\":\"신분당선\"},\n" +
                "{\"SUBWAY_ID\":1077,\"STATN_ID\":1077000688,\"STATN_NM\":\"양재\",\"LINE_NAME\":\"신분당선\"},\n" +
                "{\"SUBWAY_ID\":1077,\"STATN_ID\":1077000689,\"STATN_NM\":\"양재시민의숲\",\"LINE_NAME\":\"신분당선\"},\n" +
                "{\"SUBWAY_ID\":1077,\"STATN_ID\":1077006810,\"STATN_NM\":\"청계산입구\",\"LINE_NAME\":\"신분당선\"},\n" +
                "{\"SUBWAY_ID\":1077,\"STATN_ID\":1077006811,\"STATN_NM\":\"판교\",\"LINE_NAME\":\"신분당선\"},\n" +
                "{\"SUBWAY_ID\":1077,\"STATN_ID\":1077006812,\"STATN_NM\":\"정자\",\"LINE_NAME\":\"신분당선\"},\n" +
                "{\"SUBWAY_ID\":1077,\"STATN_ID\":1077006813,\"STATN_NM\":\"미금\",\"LINE_NAME\":\"신분당선\"},\n" +
                "{\"SUBWAY_ID\":1077,\"STATN_ID\":1077006814,\"STATN_NM\":\"동천\",\"LINE_NAME\":\"신분당선\"},\n" +
                "{\"SUBWAY_ID\":1077,\"STATN_ID\":1077006815,\"STATN_NM\":\"수지구청\",\"LINE_NAME\":\"신분당선\"},\n" +
                "{\"SUBWAY_ID\":1077,\"STATN_ID\":1077006816,\"STATN_NM\":\"성복\",\"LINE_NAME\":\"신분당선\"},\n" +
                "{\"SUBWAY_ID\":1077,\"STATN_ID\":1077006817,\"STATN_NM\":\"상현\",\"LINE_NAME\":\"신분당선\"},\n" +
                "{\"SUBWAY_ID\":1077,\"STATN_ID\":1077006818,\"STATN_NM\":\"광교중앙\",\"LINE_NAME\":\"신분당선\"},\n" +
                "{\"SUBWAY_ID\":1077,\"STATN_ID\":1077006819,\"STATN_NM\":\"광교\",\"LINE_NAME\":\"신분당선\"},\n" +
                "{\"SUBWAY_ID\":1081,\"STATN_ID\":1081037409,\"STATN_NM\":\"판교\",\"LINE_NAME\":\"경강선\"},\n" +
                "{\"SUBWAY_ID\":1081,\"STATN_ID\":1081037410,\"STATN_NM\":\"성남\",\"LINE_NAME\":\"경강선\"},\n" +
                "{\"SUBWAY_ID\":1081,\"STATN_ID\":1081037411,\"STATN_NM\":\"이매\",\"LINE_NAME\":\"경강선\"},\n" +
                "{\"SUBWAY_ID\":1081,\"STATN_ID\":1081037412,\"STATN_NM\":\"삼동\",\"LINE_NAME\":\"경강선\"},\n" +
                "{\"SUBWAY_ID\":1081,\"STATN_ID\":1081037413,\"STATN_NM\":\"경기광주\",\"LINE_NAME\":\"경강선\"},\n" +
                "{\"SUBWAY_ID\":1081,\"STATN_ID\":1081037414,\"STATN_NM\":\"초월\",\"LINE_NAME\":\"경강선\"},\n" +
                "{\"SUBWAY_ID\":1081,\"STATN_ID\":1081037415,\"STATN_NM\":\"곤지암\",\"LINE_NAME\":\"경강선\"},\n" +
                "{\"SUBWAY_ID\":1081,\"STATN_ID\":1081037416,\"STATN_NM\":\"신둔도예촌\",\"LINE_NAME\":\"경강선\"},\n" +
                "{\"SUBWAY_ID\":1081,\"STATN_ID\":1081037417,\"STATN_NM\":\"이천\",\"LINE_NAME\":\"경강선\"},\n" +
                "{\"SUBWAY_ID\":1081,\"STATN_ID\":1081037418,\"STATN_NM\":\"부발\",\"LINE_NAME\":\"경강선\"},\n" +
                "{\"SUBWAY_ID\":1081,\"STATN_ID\":1081037419,\"STATN_NM\":\"세종왕릉\",\"LINE_NAME\":\"경강선\"},\n" +
                "{\"SUBWAY_ID\":1081,\"STATN_ID\":1081037420,\"STATN_NM\":\"여주\",\"LINE_NAME\":\"경강선\"},\n" +
                "{\"SUBWAY_ID\":1092,\"STATN_ID\":1092004701,\"STATN_NM\":\"북한산우이\",\"LINE_NAME\":\"우이신설선\"},\n" +
                "{\"SUBWAY_ID\":1092,\"STATN_ID\":1092004702,\"STATN_NM\":\"솔밭공원\",\"LINE_NAME\":\"우이신설선\"},\n" +
                "{\"SUBWAY_ID\":1092,\"STATN_ID\":1092004703,\"STATN_NM\":\"4.19 민주묘지\",\"LINE_NAME\":\"우이신설선\"},\n" +
                "{\"SUBWAY_ID\":1092,\"STATN_ID\":1092004704,\"STATN_NM\":\"가오리\",\"LINE_NAME\":\"우이신설선\"},\n" +
                "{\"SUBWAY_ID\":1092,\"STATN_ID\":1092004705,\"STATN_NM\":\"화계\",\"LINE_NAME\":\"우이신설선\"},\n" +
                "{\"SUBWAY_ID\":1092,\"STATN_ID\":1092004706,\"STATN_NM\":\"삼양\",\"LINE_NAME\":\"우이신설선\"},\n" +
                "{\"SUBWAY_ID\":1092,\"STATN_ID\":1092004707,\"STATN_NM\":\"삼양사거리\",\"LINE_NAME\":\"우이신설선\"},\n" +
                "{\"SUBWAY_ID\":1092,\"STATN_ID\":1092004708,\"STATN_NM\":\"솔샘\",\"LINE_NAME\":\"우이신설선\"},\n" +
                "{\"SUBWAY_ID\":1092,\"STATN_ID\":1092004709,\"STATN_NM\":\"북한산보국문\",\"LINE_NAME\":\"우이신설선\"},\n" +
                "{\"SUBWAY_ID\":1092,\"STATN_ID\":1092004710,\"STATN_NM\":\"정릉\",\"LINE_NAME\":\"우이신설선\"},\n" +
                "{\"SUBWAY_ID\":1092,\"STATN_ID\":1092004711,\"STATN_NM\":\"성신여대입구\",\"LINE_NAME\":\"우이신설선\"},\n" +
                "{\"SUBWAY_ID\":1092,\"STATN_ID\":1092004712,\"STATN_NM\":\"보문\",\"LINE_NAME\":\"우이신설선\"},\n" +
                "{\"SUBWAY_ID\":1092,\"STATN_ID\":1092004713,\"STATN_NM\":\"신설동\",\"LINE_NAME\":\"우이신설선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004001,\"STATN_NM\":\"일산\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004002,\"STATN_NM\":\"풍산\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004003,\"STATN_NM\":\"백마\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004004,\"STATN_NM\":\"곡산\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004005,\"STATN_NM\":\"대곡\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004006,\"STATN_NM\":\"능곡\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004007,\"STATN_NM\":\"김포공항\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004008,\"STATN_NM\":\"원종\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004009,\"STATN_NM\":\"부천종합운동장\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004010,\"STATN_NM\":\"소사\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004011,\"STATN_NM\":\"소새울\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004012,\"STATN_NM\":\"시흥대야\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004013,\"STATN_NM\":\"신천\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004014,\"STATN_NM\":\"신현\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004016,\"STATN_NM\":\"시흥시청\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004017,\"STATN_NM\":\"시흥능곡\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004018,\"STATN_NM\":\"달미\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004019,\"STATN_NM\":\"선부\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004020,\"STATN_NM\":\"초지\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004021,\"STATN_NM\":\"시우\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1093,\"STATN_ID\":1093004022,\"STATN_NM\":\"원시\",\"LINE_NAME\":\"서해선\"},\n" +
                "{\"SUBWAY_ID\":1094,\"STATN_ID\":1094000401,\"STATN_NM\":\"샛강\",\"LINE_NAME\":\"신림선\"},\n" +
                "{\"SUBWAY_ID\":1094,\"STATN_ID\":1094000402,\"STATN_NM\":\"대방\",\"LINE_NAME\":\"신림선\"},\n" +
                "{\"SUBWAY_ID\":1094,\"STATN_ID\":1094000403,\"STATN_NM\":\"서울지방병무청\",\"LINE_NAME\":\"신림선\"},\n" +
                "{\"SUBWAY_ID\":1094,\"STATN_ID\":1094000404,\"STATN_NM\":\"보라매\",\"LINE_NAME\":\"신림선\"},\n" +
                "{\"SUBWAY_ID\":1094,\"STATN_ID\":1094000405,\"STATN_NM\":\"보라매공원\",\"LINE_NAME\":\"신림선\"},\n" +
                "{\"SUBWAY_ID\":1094,\"STATN_ID\":1094000406,\"STATN_NM\":\"보라매병원\",\"LINE_NAME\":\"신림선\"},\n" +
                "{\"SUBWAY_ID\":1094,\"STATN_ID\":1094000407,\"STATN_NM\":\"당곡\",\"LINE_NAME\":\"신림선\"},\n" +
                "{\"SUBWAY_ID\":1094,\"STATN_ID\":1094000408,\"STATN_NM\":\"신림\",\"LINE_NAME\":\"신림선\"},\n" +
                "{\"SUBWAY_ID\":1094,\"STATN_ID\":1094000409,\"STATN_NM\":\"서원\",\"LINE_NAME\":\"신림선\"},\n" +
                "{\"SUBWAY_ID\":1094,\"STATN_ID\":1094000410,\"STATN_NM\":\"서울대벤처타운\",\"LINE_NAME\":\"신림선\"},\n" +
                "{\"SUBWAY_ID\":1094,\"STATN_ID\":1094000411,\"STATN_NM\":\"관악산\",\"LINE_NAME\":\"신림선\"}]"
    }
}
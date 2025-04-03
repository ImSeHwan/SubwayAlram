package com.junseo.subwayalram.retrofit

import com.junseo.subwayalram.common.CommonInfo
import com.junseo.subwayalram.datas.RealtimeStationArrivalResponse
import com.junseo.subwayalram.datas.SearchSTNBySubwayLineResponse
import com.junseo.subwayalram.datas.SubwayStationApiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SubwayApiService {
    @GET("apig/apiman-gateway/tapi/TaimsKsccDvSubwayStationGeom/1.0?apikey=${CommonInfo.SUBWAY_STATION_LIST_KEY}")
    fun getSubwayStations(): Call<List<SubwayStationApiResponse>>

    @GET("subway/{apiKey}/json/realtimeStationArrival/{startIdx}/{endIdx}/{stationName}")
    fun getRealtimeStationArrival(
        @Path("apiKey") apiKey: String,
        @Path("startIdx") startIdx: Int,
        @Path("endIdx") endIdx: Int,
        @Path("stationName") stationName: String
    ): Call<RealtimeStationArrivalResponse>

    @GET("/{serviceKey}/json/SearchSTNBySubwayLineInfo/{startIndex}/{endIndex}/%20/%20")
    suspend fun getSubwayLineInfo(
        @Path("serviceKey") serviceKey: String, // API 키
        @Path("startIndex") startIndex: Int, // 시작 인덱스
        @Path("endIndex") endIndex: Int, // 끝 인덱스
    ): Response<SearchSTNBySubwayLineResponse>

//    @GET("subway/{apiKey}/json/realtimeStationArrival/{startIdx}/{endIdx}/{stationName}")
//    fun getRealtimeStationArrival(
//        @Path("apiKey") apiKey: String,
//        @Path("startIdx") startIdx: Int,
//        @Path("endIdx") endIdx: Int,
//        @Path("stationName") stationName: String
//    ): Call<RealtimeStationArrivalResponse>
}
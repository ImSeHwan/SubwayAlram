package com.junseo.subwayalram.databaseutils

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Dao
interface SubwayStationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // 기존 데이터가 있을 경우 덮어쓰기
    suspend fun insertStation(station: SubwayStation)

    @Query("SELECT * FROM subway_station WHERE stationName = :stationName AND lineName = :lineName LIMIT 1")
    suspend fun getStationByNameAndLine(stationName: String, lineName: String): SubwayStation?

    suspend fun insertUniqueStation(station: SubwayStation) {
        val existingStation = getStationByNameAndLine(station.stationName, station.lineName)
        if (existingStation == null) {
            insertStation(station) // 중복되지 않으면 삽입
        }
    }

    @Query("SELECT * FROM subway_station")
    suspend fun getAllStations(): List<SubwayStation>

    @Query("SELECT * FROM subway_station WHERE stationName LIKE :query")
    suspend fun searchStations(query: String): List<SubwayStation>

}

@Dao
interface SelectedSubwayDao {

    // 데이터를 삽입 (중복 시 기존 데이터를 대체)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStation(station: SelectedSubway)

    // 데이터를 삭제
    @Delete
    suspend fun deleteStation(station: SelectedSubway)

    @Query("SELECT * FROM selected_subway")
    suspend fun getAllStations(): List<SelectedSubway>
}

@Dao
interface SubwayStationDetailInfoDao {

    @Insert
    suspend fun insertAll(subwayStatioinDetailInfos: List<SubwayStatioinDetailInfo>)

    @Query("SELECT * FROM subway_stations_detail_info")
    suspend fun getAllStations(): List<SubwayStatioinDetailInfo>

    @Query("SELECT COUNT(*) FROM subway_stations_detail_info")
    suspend fun getCount(): Int

    //@Query("SELECT * FROM subway_stations_detail_info WHERE statnNm = :statnNm AND lineName = :lineName")
    @Query("SELECT * FROM subway_stations_detail_info WHERE statnNm LIKE '%' || :statnNm || '%' AND lineName LIKE '%' || :lineName || '%'")
    fun getStationsByNameAndLine(statnNm: String, lineName: String): List<SubwayStatioinDetailInfo>

    @Query("SELECT * FROM subway_stations_detail_info WHERE statnId = :statnId")
    fun getStationsByStationId(statnId: String): List<SubwayStatioinDetailInfo>

    @Query("SELECT * FROM subway_stations_detail_info WHERE statnNm = :statnNm")
    fun getStationsByStationName(statnNm: String): List<SubwayStatioinDetailInfo>

    @Query("SELECT * FROM subway_stations_detail_info WHERE lineName = :lineName")
    fun getStationsByStationLineName(lineName: String): List<SubwayStatioinDetailInfo>

    suspend fun insertSubwayStationsIfNeeded(subwayStations: List<SubwayStatioinDetailInfo>) {
        val count = getCount()
        if (count == 0) { // 데이터가 없을 때만 삽입
            withContext(Dispatchers.IO) {
                insertAll(subwayStations)
            }
        }
    }
}

@Dao
interface SubwayLineInfoDao {
    @Insert
    suspend fun insertStation(station: SubwayLineInfoEntity)

    @Insert
    suspend fun insertStations(stations: List<SubwayLineInfoEntity>)

    @Query("SELECT * FROM subway_line_info ORDER BY LINE_NUM, FR_CODE")
    suspend fun getAllStations(): List<SubwayLineInfoEntity>

    @Query("SELECT * FROM subway_line_info WHERE FR_CODE = :stationCode")
    suspend fun getStationByCode(stationCode: String): SubwayLineInfoEntity?

    @Query("SELECT * FROM subway_line_info WHERE STATION_CD = :stationCode")
    suspend fun getStationByStationCode(stationCode: String): SubwayLineInfoEntity?

    @Query("SELECT * FROM subway_line_info WHERE STATION_NM = :stationName")
    suspend fun getStationByName(stationName: String): List<SubwayLineInfoEntity>

    @Query("SELECT * FROM subway_line_info WHERE FR_CODE LIKE :query")
    suspend fun searchTransferStationsByCode(query: String): List<SubwayLineInfoEntity>

    //stationName = :stationName AND lineName = :lineName
    @Query("SELECT * FROM subway_line_info WHERE STATION_NM = :stationName AND LINE_NUM = :stationLine")
    suspend fun getStation(stationName: String, stationLine: String): SubwayLineInfoEntity?

    @Delete
    suspend fun deleteStation(station: SubwayLineInfoEntity)

    @Query("DELETE FROM subway_line_info")
    suspend fun deleteAllStations()

    @Query("SELECT COUNT(*) FROM subway_line_info WHERE STATION_CD = :stationCode")
    suspend fun isStationExists(stationCode: String): Int
}
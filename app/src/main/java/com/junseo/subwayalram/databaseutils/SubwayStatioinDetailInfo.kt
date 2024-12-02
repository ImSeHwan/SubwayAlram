package com.junseo.subwayalram.databaseutils

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subway_stations_detail_info")
data class SubwayStatioinDetailInfo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val subwayId: Int,
    val statnId: Long,
    val statnNm: String,
    val lineName: String
)
package com.junseo.subwayalram.databaseutils

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subway_station")
data class SubwayStation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val outStnNum: Int,
    val stationName: String,
    val lineName: String,
    val latitude: Double,
    val longitude: Double
)

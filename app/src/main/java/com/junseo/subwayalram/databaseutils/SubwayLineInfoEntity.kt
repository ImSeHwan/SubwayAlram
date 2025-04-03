package com.junseo.subwayalram.databaseutils

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subway_line_info")
data class SubwayLineInfoEntity(
    @PrimaryKey val STATION_CD: String, // Primary Key로 설정
                val STATION_NM: String,
                val LINE_NUM: String,
                val FR_CODE: String)

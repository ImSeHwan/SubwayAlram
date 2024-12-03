package com.junseo.subwayalram.databaseutils

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SubwayStation::class, SelectedSubway::class, SubwayStatioinDetailInfo::class, SubwayLineInfoEntity::class], version = 1, exportSchema = false)
abstract class SubwayDatabase : RoomDatabase() {
    abstract fun subwayStationDao(): SubwayStationDao
    abstract fun selectedSubwayDao(): SelectedSubwayDao
    abstract fun subwayStationDetailInfoDao(): SubwayStationDetailInfoDao
    abstract fun subwayLineInfoDao(): SubwayLineInfoDao
}
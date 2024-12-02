package com.junseo.subwayalram.databaseutils

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private const val DATABASE_NAME = "subway_database.db"

    @Volatile
    private var INSTANCE: SubwayDatabase? = null

    fun getDatabase(context: Context): SubwayDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                SubwayDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()  // 마이그레이션을 제공하지 않으면 데이터를 삭제하고 새로 생성
                .build()
            INSTANCE = instance
            instance
        }
    }
}
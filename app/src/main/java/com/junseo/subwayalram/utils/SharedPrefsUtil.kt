package com.junseo.subwayalram.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPrefsUtil {
    private const val PREFS_NAME = "com.junseo.subwayalram"

    // SharedPreferences 초기화
    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    // 문자열 저장
    fun putString(context: Context, key: String, value: String) {
        getPreferences(context).edit().putString(key, value).apply()
    }

    // 문자열 불러오기
    fun getString(context: Context, key: String, defaultValue: String? = null): String? {
        return getPreferences(context).getString(key, defaultValue)
    }

    // 정수값 저장
    fun putInt(context: Context, key: String, value: Int) {
        getPreferences(context).edit().putInt(key, value).apply()
    }

    // 정수값 불러오기
    fun getInt(context: Context, key: String, defaultValue: Int = 0): Int {
        return getPreferences(context).getInt(key, defaultValue)
    }

    // Boolean 값 저장
    fun putBoolean(context: Context, key: String, value: Boolean) {
        getPreferences(context).edit().putBoolean(key, value).apply()
    }

    // Boolean 값 불러오기
    fun getBoolean(context: Context, key: String, defaultValue: Boolean = false): Boolean {
        return getPreferences(context).getBoolean(key, defaultValue)
    }

    // 날짜 저장
    fun putDate(context: Context, key: String, date: String) {
        putString(context, key, date)
    }

    // 날짜 불러오기
    fun getDate(context: Context, key: String): String? {
        return getString(context, key)
    }

    // 데이터 삭제
    fun remove(context: Context, key: String) {
        getPreferences(context).edit().remove(key).apply()
    }

    // 모든 데이터 초기화
    fun clear(context: Context) {
        getPreferences(context).edit().clear().apply()
    }
}
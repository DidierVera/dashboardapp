package com.came.parkare.dashboardapp.config.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.came.parkare.dashboardapp.config.constants.Constants.BASE_SP


class SharedPreferencesWrapper (context: Context) : SharedPreferencesProvider {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(BASE_SP, Context.MODE_PRIVATE)

    override fun <T> put(key: String, value: T) {
        sharedPreferences.edit {
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is Float -> putFloat(key, value)
                is Boolean -> putBoolean(key, value)
                else -> throw IllegalArgumentException("Unsupported data type")
            }
        }
    }

    override fun <T> get(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> sharedPreferences.getString(key, defaultValue as String) as T
            is Int -> sharedPreferences.getInt(key, defaultValue as Int) as T
            is Long -> sharedPreferences.getLong(key, defaultValue as Long) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue as Float) as T
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue as Boolean) as T
            else -> throw IllegalArgumentException("Unsupported data type")
        }
    }
}
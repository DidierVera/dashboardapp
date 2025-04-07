package com.came.parkare.dashboardapp.config.utils

interface SharedPreferencesProvider {
    fun <T> put(key: String, value: T)
    fun <T> get(key: String, defaultValue: T): T
}
package com.came.parkare.dashboardapp.config.utils

interface WasmSharedPreferencesProvider:SharedPreferencesProvider  {
    fun contains(key: String): Boolean
    fun remove(key: String)
    fun clear()
}
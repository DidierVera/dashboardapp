package com.came.parkare.dashboardapp.config.utils

import org.w3c.dom.Storage

class WebStoragePreferences(
    private val storage: Storage
): WasmSharedPreferencesProvider {
    override fun <T> put(key: String, value: T) {
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            else -> throw IllegalArgumentException("Unsupported data type")
        }
    }

    override fun contains(key: String): Boolean = storage.getItem(key) != null
    override fun remove(key: String) = storage.removeItem(key)
    override fun clear() = storage.clear()

    override fun <T> get(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> getString(key, defaultValue as String) as T
            is Int -> getInt(key, defaultValue as Int) as T
            is Boolean -> getBoolean(key, defaultValue as Boolean) as T
            else -> throw IllegalArgumentException("Unsupported data type")
        }
    }

    private fun putString(key: String, value: String) = storage.setItem(key, value)
    private fun getString(key: String, defaultValue: String = ""): String =
        storage.getItem(key) ?: defaultValue

    private fun putInt(key: String, value: Int) = storage.setItem(key, value.toString())
    private fun getInt(key: String, defaultValue: Int = 0): Int =
        storage.getItem(key)?.toIntOrNull() ?: defaultValue

    private fun putBoolean(key: String, value: Boolean) = storage.setItem(key, value.toString())
    private fun getBoolean(key: String, defaultValue: Boolean = false): Boolean =
        storage.getItem(key)?.toBoolean() ?: defaultValue

}
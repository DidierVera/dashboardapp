package com.came.parkare.dashboardapp.ui.utils

import android.content.Context
import android.provider.Settings
import java.io.BufferedReader
import java.io.InputStreamReader

class SystemBrightnessManager(private val context: Context) {

    companion object {
        private const val MIN_BRIGHTNESS = 0
        private const val MAX_BRIGHTNESS = 255
    }

    fun setBrightness(value: Int, useSystemMethod: Boolean = false): Boolean {
        val normalizedValue = value.coerceIn(MIN_BRIGHTNESS, MAX_BRIGHTNESS)

        return if (useSystemMethod || !hasRootAccess()) {
            setBrightnessSystemAPI(normalizedValue)
        } else {
            setBrightnessRoot(normalizedValue)
        }
    }

    private fun setBrightnessRoot(value: Int): Boolean {
        return try {
            // Intentar diferentes métodos con root
            val methods = listOf(
                { setViaSysFS(value) },
                { setViaServiceCall(value) },
                { setViaSettingsPut(value) }
            )

            methods.any { it.invoke() }
        } catch (e: Exception) {
            false
        }
    }

    private fun setViaSysFS(value: Int): Boolean {
        val commonPaths = arrayOf(
            "/sys/class/backlight/backlight/brightness",
            "/sys/class/backlight/panel/brightness",
            "/sys/class/backlight/pwm-backlight/brightness",
            "/sys/class/leds/lcd-backlight/brightness"
        )

        for (path in commonPaths) {
            if (executeRootCommand("echo $value > $path")) {
                return true
            }
        }
        return false
    }

    private fun setViaServiceCall(value: Int): Boolean {
        val command = "service call display 36 i32 0 i32 $value"
        return executeRootCommand(command)
    }

    private fun setViaSettingsPut(value: Int): Boolean {
        val command = "settings put system screen_brightness $value"
        return executeRootCommand(command)
    }

    private fun setBrightnessSystemAPI(value: Int): Boolean {
        return try {
            // Método estándar de Android (requiere permisos)
            if (Settings.System.canWrite(context)) {
                Settings.System.putInt(
                    context.contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS,
                    value
                )
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    private fun executeRootCommand(command: String): Boolean {
        return try {
            val process = Runtime.getRuntime().exec(arrayOf("su", "-c", command))
            val exitCode = process.waitFor()
            exitCode == 0
        } catch (e: Exception) {
            false
        }
    }

    fun hasRootAccess(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec("su -c \"echo 'root_test'\"")
            val exitCode = process.waitFor()
            exitCode == 0
        } catch (e: Exception) {
            false
        }
    }

    fun getCurrentBrightness(): Int {
        return try {
            // Intentar leer de sysfs primero
            val process = Runtime.getRuntime().exec("su -c \"cat /sys/class/backlight/*/brightness\"")
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val brightness = reader.readLine()?.toIntOrNull()
            process.waitFor()

            brightness ?: Settings.System.getInt(
                context.contentResolver,
                Settings.System.SCREEN_BRIGHTNESS,
                128
            )
        } catch (e: Exception) {
            Settings.System.getInt(
                context.contentResolver,
                Settings.System.SCREEN_BRIGHTNESS,
                128
            )
        }
    }
}
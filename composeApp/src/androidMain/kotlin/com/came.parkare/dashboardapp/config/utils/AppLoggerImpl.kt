package com.came.parkare.dashboardapp.config.utils

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.core.os.toPersistableBundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


class AppLoggerImpl (
    private val context: Context,
    private val firebaseAnalytics: FirebaseAnalytics
): AppLogger {

    // Directorio donde se guardarán los logs
    private val logDirectory: File = File(
        context.getExternalFilesDir("logs"),
        ""
    )

    // Formato de la fecha para nombrar los archivos
    private val dateFormat = SimpleDateFormat("dd-MM-yyyy")

    init {
        // Crear el directorio si no existe
        if (!logDirectory.exists()) {
            logDirectory.mkdirs()
        }
    }

    // Método para registrar excepciones
    override fun trackError(e: Exception) {
        e.printStackTrace()

        FirebaseCrashlytics.getInstance().recordException(e)

        val currentDate = dateFormat.format(Date())
        val exceptionFile = File(logDirectory, "$currentDate-exceptions.txt")

        try {
            FileWriter(exceptionFile, true).use { writer ->
                writer.appendLine("${getTimeStamp()} - EXCEPTION: ${e.localizedMessage}")
                writer.appendLine(e.stackTraceToString())
            }
        } catch (ioe: IOException) {
            FirebaseCrashlytics.getInstance().recordException(e)
            ioe.printStackTrace()
        } catch (e: Exception){
            FirebaseCrashlytics.getInstance().recordException(e)
            e.printStackTrace()
        }
    }

    // Método para registrar logs
    override fun trackLog(tag: String, message: String?) {
        cleanupOldLogs()
        Log.i(tag, message?: "")
        val currentDate = dateFormat.format(Date())
        val logFile = File(logDirectory, "$currentDate.txt")

        try {
            FileWriter(logFile, true).use { writer ->
                writer.appendLine("${getTimeStamp()} - TAG: $tag, ${message?.let { "MESSAGE: ${it}" }.orEmpty() }")
            }
        } catch (ioe: IOException) {
            // Manejo de error en caso de fallo en la escritura del archivo
            ioe.printStackTrace()
        }
    }

    // Método auxiliar para obtener la marca de tiempo en formato HH:mm:ss
    private fun getTimeStamp(): String {
        val timeFormat = SimpleDateFormat("HH:mm:ss")
        return timeFormat.format(Date())
    }

    private fun cleanupOldLogs() {
        try {
            val files = logDirectory.listFiles() ?: return
            val currentDate = Date()
            val calendar = Calendar.getInstance()

            // Get current date minus 3 days
            calendar.time = currentDate
            calendar.add(Calendar.DAY_OF_YEAR, -3)
            val threeDaysAgo = calendar.time

            for (file in files) {
                try {
                    // Extract date from filename (format: dd-MM-yyyy or dd-MM-yyyy-exceptions.txt)
                    val fileName = file.name
                    val dateString = fileName.substring(0, 10) // Get first 10 characters (dd-MM-yyyy)
                    val fileDate = dateFormat.parse(dateString)

                    if (fileDate != null) {
                        if (fileDate.before(threeDaysAgo)) {
                            file.delete()
                        }
                    }
                } catch (e: Exception) {
                    // Skip files that don't match our naming pattern
                    continue
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            trackError(e)
        }
    }
    override fun trackEvent(eventName: String, params: Map<String, Any>) {
        firebaseAnalytics.logEvent(eventName, Bundle(params.toPersistableBundle()))

        // Optional: Also log to file
        val currentDate = dateFormat.format(Date())
        val eventFile = File(logDirectory, "$currentDate-events.txt")

        FileWriter(eventFile, true).use { writer ->
            writer.appendLine("${getTimeStamp()} - EVENT: $eventName")
            params.forEach { (key, value) ->
                writer.appendLine("  $key: $value")
            }
        }
    }
}
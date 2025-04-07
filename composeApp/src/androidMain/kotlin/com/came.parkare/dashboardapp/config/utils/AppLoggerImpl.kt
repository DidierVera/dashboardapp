package com.came.parkare.dashboardapp.config.utils

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date


class AppLoggerImpl (private val context: Context): AppLogger {

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
        val currentDate = dateFormat.format(Date())
        val exceptionFile = File(logDirectory, "$currentDate-exceptions.txt")

        try {
            FileWriter(exceptionFile, true).use { writer ->
                writer.appendLine("${getTimeStamp()} - EXCEPTION: ${e.localizedMessage}")
                writer.appendLine(e.stackTraceToString())
            }
        } catch (ioe: IOException) {
            // Manejo de error en caso de fallo en la escritura del archivo
            ioe.printStackTrace()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    // Método para registrar logs
    override fun trackLog(tag: String, message: String?) {
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
}
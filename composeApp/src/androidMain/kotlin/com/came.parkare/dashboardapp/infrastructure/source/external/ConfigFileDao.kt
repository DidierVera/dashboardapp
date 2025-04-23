package com.came.parkare.dashboardapp.infrastructure.source.external

import android.content.Context
import android.os.Build
import android.os.Environment
import com.came.parkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import java.io.File

class ConfigFileDao (
    private val context: Context,
    val appLogger: AppLogger
) {

    fun getFolderPath(): File? {
        val result = when(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            true -> context.getExternalFilesDir("/")
            false -> Environment.getExternalStoragePublicDirectory("${Environment.DIRECTORY_PICTURES}/Dashboard/")
        }
        return result //Environment.getExternalStoragePublicDirectory("${Environment.DIRECTORY_DOCUMENTS}/Dashboard/config")
    }

    inline fun <reified T> readJsonFromFile(filename: String, defaultValues: String): ServiceResult<T> {
        val folder = getFolderPath() ?: return ServiceResult.Error(ErrorTypeClass.CanNoAccessToConfigFile)

        var file = File(folder, filename)

        var fileExist = true
        var success = true

        if (folder?.exists() == false)
            success = folder.mkdirs()

        if (!file.exists()){
            success = file.createNewFile()
            fileExist = false
        }else{
            file = File(folder, filename)
        }

        if (success) {
            // directory exists or already created
            file.setExecutable(true, false)
            val jsonContent = if (!fileExist) {
                file.writeText(defaultValues)
                defaultValues
            } else {
                val fileContent = file.readText(Charsets.UTF_8).trimStart('\uFEFF')
                appLogger.trackLog("The $filename content is:", fileContent)
                fileContent
            }

            // Read data from a JSON string
            return try {
                val result = json.decodeFromString<T>(jsonContent)
                ServiceResult.Success(result)
            } catch (ex: Exception) {
                appLogger.trackLog("READ CONFIG FILE: ", message = "ERROR: See the exception file")
                appLogger.trackError(ex)
                ServiceResult.Error(ErrorTypeClass.WrongConfigFile)
            }
        } else {
            return ServiceResult.Error(ErrorTypeClass.ConfigFileNotExist)
        }
    }

    inline fun <reified T> writeJsonToFile(filename: String, content: T): ServiceResult<Unit> {
        val folder = getFolderPath() ?: return ServiceResult.Error(ErrorTypeClass.CanNoAccessToConfigFile)

        // Ensure directory exists
        if (folder.exists().not() && !folder.mkdirs()) {
            return ServiceResult.Error(ErrorTypeClass.ConfigFileNotExist)
        }

        val file = File(folder, filename)

        return try {
            // Convert content to JSON string
            val jsonString = json.encodeToString(content)

            // Write to file
            file.writeText(jsonString)
            file.setExecutable(true, false)

            appLogger.trackLog("Successfully save info to $filename", jsonString)
            ServiceResult.Success(Unit)
        } catch (ex: Exception) {
            appLogger.trackLog("WRITE CONFIG FILE: ", message = "ERROR: See the exception file")
            appLogger.trackError(ex)
            ServiceResult.Error(ErrorTypeClass.CanNoAccessToConfigFile)
        }
    }

    val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        serializersModule = SerializersModule {
            polymorphic(ElementDto::class) {
                subclass(ElementDto.BoxDto::class, ElementDto.BoxDto.serializer())
                subclass(ElementDto.SpacerDto::class, ElementDto.SpacerDto.serializer())
                subclass(ElementDto.ColumnDto::class, ElementDto.ColumnDto.serializer())
                subclass(ElementDto.TextDto::class, ElementDto.TextDto.serializer())
                subclass(ElementDto.RowDto::class, ElementDto.RowDto.serializer())
                subclass(ElementDto.ImageDto::class, ElementDto.ImageDto.serializer())
                subclass(ElementDto.VideoDto::class, ElementDto.VideoDto.serializer())
            }
        }
    }
}
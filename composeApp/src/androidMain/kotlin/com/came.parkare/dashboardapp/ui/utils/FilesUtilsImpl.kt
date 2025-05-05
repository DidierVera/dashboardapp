package com.came.parkare.dashboardapp.ui.utils

import android.content.Context
import android.os.Environment
import android.util.Log
import com.came.parkare.dashboardapp.domain.usecases.GetImageFromDbByName
import java.io.File


class FilesUtilsImpl(
    private val context: Context,
    private val getImageFromDb: GetImageFromDbByName): FilesUtils {

    override suspend fun getImageFromDatabase(filename: String): String? {
        val extensions = listOf(".jpg", ".png", ".svg", ".jpeg")

        val imageFromFolder = getImageFromDirectory("/Dashboard", filename)
        if(!imageFromFolder.isNullOrEmpty()) return imageFromFolder //check image on directory first

        extensions.firstOrNull { filename.endsWith(it) }?.let {
            return getImageFromDb.invoke(filename)?.fileContent
        }

        return extensions.firstNotNullOfOrNull { ext ->
            getImageFromDb.invoke("$filename$ext")?.fileContent
        }
    }

    override fun getImageFromDirectory(folder: String, filename: String): String? {
        val directory = Environment.getExternalStoragePublicDirectory(
            "${Environment.DIRECTORY_PICTURES}$folder")
        val extensions = listOf(".jpg", ".png", ".svg", ".jpeg")
        var result : String? = null
        for(ext in extensions){
            val file = File(directory, "$filename$ext")

            if (directory?.exists() == false) {
                directory.mkdirs()
            }

            if (file.exists()){
                result = file.absolutePath
                break
            }
        }
        return result
    }

    override fun getVideoFromDirectory(folder: String, filename: String): String? {
        val directory = Environment.getExternalStoragePublicDirectory("${Environment.DIRECTORY_MOVIES}$folder")
        val extensions = listOf(".mp4", ".mov")
        var result : String? = null
        for(ext in extensions){
            val file = File(directory, "$filename$ext")

            if (directory?.exists() == false) {
                directory.mkdirs()
            }

            if (file.exists()){
                result = file.absolutePath
                break
            }
        }
        return result
    }

    override fun getConfigFile(filename: String): String? {
        TODO("Not yet implemented")
    }

    override fun getVideosFiles(path: String?): List<String> {
        val files = Environment.getExternalStoragePublicDirectory(path)?.listFiles()
        val filesToReturn: MutableList<File> = mutableListOf()

        if (!files.isNullOrEmpty()){
            Log.d("getVideosFiles", "Size: " + files.size)
            for (i in files.indices) {
                Log.d("getVideosFiles", "FilePath:" + files[i].absolutePath)
                val file = File(files[i].absolutePath)
                if (file.exists()) {
                    filesToReturn.add(file)
                }
            }
        }

        return filesToReturn.map { it.absolutePath }.sorted()
    }
}
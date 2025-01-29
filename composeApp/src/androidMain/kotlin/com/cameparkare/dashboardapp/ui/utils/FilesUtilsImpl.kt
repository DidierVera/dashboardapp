package com.cameparkare.dashboardapp.ui.utils

import android.content.Context
import android.os.Environment
import android.util.Log
import com.cameparkare.dashboardapp.ui.interfaces.FilesUtils
import java.io.File


class FilesUtilsImpl(private val context: Context): FilesUtils {
    override fun getImageFromDirectory(folder: String, filename: String): String? {
        val directory = Environment.getExternalStoragePublicDirectory(folder)
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

    override fun getConfigFile(filename: String): String? {
        TODO("Not yet implemented")
    }

    override fun getVideosFiles(path: String?): List<String> {
        val files = Environment.getExternalStoragePublicDirectory(path)?.listFiles()
        val filesToReturn: MutableList<File> = mutableListOf()

        if (!files.isNullOrEmpty()){
            Log.d("Files", "Size: " + files.size)
            for (i in files.indices) {
                Log.d("Files", "FilePath:" + files[i].absolutePath)
                val file = File(files[i].absolutePath)
                if (file.exists()) {
                    filesToReturn.add(file)
                }
            }
        }

        return filesToReturn.map { it.absolutePath }.sorted()
    }
}
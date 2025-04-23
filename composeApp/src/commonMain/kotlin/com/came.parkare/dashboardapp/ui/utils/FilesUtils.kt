package com.came.parkare.dashboardapp.ui.utils


interface FilesUtils {
    suspend fun getImageFromDatabase(filename: String): String?
    fun getImageFromDirectory(folder: String = "/Dashboard", filename: String): String?
    fun getVideoFromDirectory(folder: String, filename: String): String?
    fun getConfigFile(filename: String): String?
    fun getVideosFiles(path: String? = "/videos"): List<String>
}
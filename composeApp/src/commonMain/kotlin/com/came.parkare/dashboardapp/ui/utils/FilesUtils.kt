package com.came.parkare.dashboardapp.ui.utils


interface FilesUtils {
    fun getImageFromDirectory(folder: String, filename: String): String?
    fun getVideoFromDirectory(folder: String, filename: String): String?
    fun getConfigFile(filename: String): String?
    fun getVideosFiles(path: String? = "/videos"): List<String>
}
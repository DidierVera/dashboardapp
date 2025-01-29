package com.cameparkare.dashboardapp.ui.interfaces


interface FilesUtils {
    fun getImageFromDirectory(folder: String, filename: String): String?
    fun getConfigFile(filename: String): String?
    fun getVideosFiles(path: String? = "/videos"): List<String>
}
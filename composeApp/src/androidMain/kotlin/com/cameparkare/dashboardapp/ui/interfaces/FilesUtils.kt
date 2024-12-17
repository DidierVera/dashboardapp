package com.cameparkare.dashboardapp.ui.interfaces

import java.io.File


interface FilesUtils {
    fun getImageFromDirectory(folder: String, filename: String): String?
    fun getConfigFile(filename: String): File?
    fun getVideosFiles(path: String? = "/videos"): List<File>
}
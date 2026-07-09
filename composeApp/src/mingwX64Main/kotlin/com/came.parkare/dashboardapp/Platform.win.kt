package com.came.parkare.dashboardapp

actual fun getPlatform(): Platform = WindowsPlatform

object WindowsPlatform : Platform {
    override val name: String = "Windows (mingwX64)"
}

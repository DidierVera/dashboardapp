package com.came.parkare.dashboardapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
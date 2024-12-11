package com.cameparkare.dashboardapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
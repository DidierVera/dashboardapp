package com.cameparkare.dashboardapp.config.dataclasses

sealed class ServiceResult<T> {
    data class Success<T>(val data: T? = null): ServiceResult<T>()
    data class Error<T>(val error: ErrorTypeClass): ServiceResult<T>()
}
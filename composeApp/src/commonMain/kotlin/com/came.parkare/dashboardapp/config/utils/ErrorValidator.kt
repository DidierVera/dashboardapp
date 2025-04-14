package com.came.parkare.dashboardapp.config.utils

import com.came.parkare.dashboardapp.config.dataclasses.ErrorTypeClass

interface ErrorValidator {
    fun validate(error: ErrorTypeClass)
}
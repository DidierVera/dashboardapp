package com.cameparkare.dashboardapp.ui.screens.settings.connection

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.cameparkare.dashboardapp.ui.screens.settings.components.TabTitle
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.connection_title
import dashboardapp.composeapp.generated.resources.import_export_title

@Composable
fun ConnectionTab() {

    Column {
        TabTitle(Res.string.connection_title)
    }
}


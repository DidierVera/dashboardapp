package com.came.parkare.dashboardapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.ic_close
import org.jetbrains.compose.resources.painterResource


@Composable
fun CloseButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(modifier = modifier) {
        IconButton(onClick = { onClick.invoke() },
            modifier = Modifier.align(Alignment.Center).fillMaxSize(0.82F)) {
            Image(painter = painterResource(Res.drawable.ic_close),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier.align(Alignment.Center).fillMaxSize())
        }
    }
}
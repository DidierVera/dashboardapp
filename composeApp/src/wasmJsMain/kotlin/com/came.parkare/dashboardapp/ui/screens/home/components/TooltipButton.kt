@file:OptIn(ExperimentalMaterial3Api::class)

package com.came.parkare.dashboardapp.ui.screens.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TooltipButton(modifier: Modifier = Modifier,
                  value: Boolean, tooltipTextId: StringResource, onClick: () -> Unit,
                  content: @Composable () -> Unit){
    val tooltipState = rememberTooltipState()

    TooltipBox(
        modifier = modifier,
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            Text("${if(value) "hide" else "show"} ${stringResource(tooltipTextId)}")
        },
        state = tooltipState
    ) {
        OutlinedButton(onClick = { onClick.invoke() }){
            content()
        }
    }
}
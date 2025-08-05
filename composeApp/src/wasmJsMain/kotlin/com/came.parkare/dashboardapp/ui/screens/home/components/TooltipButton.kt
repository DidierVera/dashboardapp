@file:OptIn(ExperimentalMaterial3Api::class)

package com.came.parkare.dashboardapp.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.ui.theme.WarningColor
import com.came.parkare.dashboardapp.ui.theme.WhiteColor
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TooltipButton(modifier: Modifier = Modifier,
                  tooltipTextId: StringResource, onClick: () -> Unit,
                  content: @Composable () -> Unit){
    val tooltipState = rememberTooltipState()

    TooltipBox(
        modifier = modifier,
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            Text(stringResource(tooltipTextId),
                modifier = Modifier.clip(CircleShape.copy( CornerSize(4.dp)))
                    .background(WarningColor .copy(alpha = 0.1f))
                    .padding(4.dp))
        },
        state = tooltipState
    ) {
        OutlinedButton(onClick = { onClick.invoke() }){
            content()
        }
    }
}
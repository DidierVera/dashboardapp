package com.came.parkare.dashboardapp.ui.components.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.components.ColumnDataModel
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.ui.components.BuildComposable
import com.came.parkare.dashboardapp.ui.theme.hexToColor

@Composable
fun BuildColumnView(
    modifier: Modifier = Modifier,
    column: ColumnDataModel,
    textSizeScale: Int, scaleFactor: Float,
    buildChildren: @Composable (ElementModel, Int) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            (column.spacing.toFloat() * scaleFactor).dp,
            alignment = Alignment.CenterVertically),
        modifier = modifier
            .shadow(
                elevation = if (column.style.hasShadow) 1.5.dp else 0.dp,
                shape = RoundedCornerShape(column.style.roundBorder.dp)
            )
            .background(
                hexToColor(column.style.backgroundColor)
                    .copy(alpha = (column.style.density / 100f))
            )
            .fillMaxWidth()
            .padding(((column.style.padding ?: 1).toFloat() * scaleFactor).dp)
    ) {
        column.content.forEach { contentDto ->
            buildChildren(contentDto, textSizeScale)
        }
    }
}
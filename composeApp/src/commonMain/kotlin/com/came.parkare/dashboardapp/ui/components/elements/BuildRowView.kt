package com.came.parkare.dashboardapp.ui.components.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.domain.models.components.RowDataModel
import com.came.parkare.dashboardapp.ui.components.BuildComposable
import com.came.parkare.dashboardapp.ui.theme.hexToColor

@Composable
fun BuildRowView(
    modifier: Modifier = Modifier,
    row: RowDataModel,
    textSizeScale: Int, scaleFactor: Float,
    buildChildren: @Composable (ElementModel, Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            (row.spacing.toFloat() * scaleFactor).dp,
            alignment = Alignment.CenterHorizontally
        ),
        modifier = modifier
            .shadow(
                elevation = if (row.style.hasShadow) 1.5.dp else 0.dp,
                shape = RoundedCornerShape(row.style.roundBorder.dp)
            )
            .background(
                hexToColor(row.style.backgroundColor)
                    .copy(alpha = (row.style.density / 100f))
            )
            .fillMaxWidth()
            .padding(((row.style.padding ?: 1).toFloat() * scaleFactor).dp)
    ) {
        row.content.forEach { contentDto ->
            buildChildren(contentDto, textSizeScale)
        }
    }
}
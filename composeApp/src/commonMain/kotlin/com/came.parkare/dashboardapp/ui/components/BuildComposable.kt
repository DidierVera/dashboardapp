package com.came.parkare.dashboardapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.ui.components.elements.BuildBoxView
import com.came.parkare.dashboardapp.ui.components.elements.BuildColumnView
import com.came.parkare.dashboardapp.ui.components.elements.BuildImageView
import com.came.parkare.dashboardapp.ui.components.elements.BuildRowView
import com.came.parkare.dashboardapp.ui.components.elements.BuildTextView

@Composable
fun BuildComposable(elementModel: ElementModel, textSizeScale: Int): Unit {
    val scaleFactor = (textSizeScale / 10f).coerceIn(0.5f, 3f)
    val state = remember {
        MutableTransitionState(initialState = false).apply {
            targetState = true
        }
    }
    return AnimatedVisibility(
        visibleState = state,
        enter = fadeIn(animationSpec = tween(1000)),
        exit = fadeOut(animationSpec = tween(1000))
    ) {
        when (elementModel) {
            is ElementModel.BoxModel -> {
                // Box Composable with appropriate styling
                BuildBoxView(box = elementModel.data, textSizeScale = textSizeScale, scaleFactor = scaleFactor){ dto, scale ->
                    BuildComposable(dto, scale)
                }
            }
            is ElementModel.SpacerModel -> {
                Spacer(modifier = Modifier.size((elementModel.data.value.toFloat() * scaleFactor).dp))
            }
            is ElementModel.ColumnModel -> {
                BuildColumnView(column = elementModel.data, textSizeScale = textSizeScale, scaleFactor = scaleFactor){ dto, scale ->
                    BuildComposable(dto, scale)
                }
            }
            is ElementModel.TextModel -> {
                BuildTextView(elementModel.data, scaleFactor)
            }

            is ElementModel.RowModel -> {
                BuildRowView(row = elementModel.data, textSizeScale = textSizeScale, scaleFactor = scaleFactor){ dto, scale ->
                    BuildComposable(dto, scale)
                }
            }

            is ElementModel.ImageModel -> {
                BuildImageView(image = elementModel.data, scaleFactor = scaleFactor)
            }
            is ElementModel.VideoModel -> {}
        }
    }
}
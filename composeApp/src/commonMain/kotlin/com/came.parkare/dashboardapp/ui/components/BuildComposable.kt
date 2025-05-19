package com.came.parkare.dashboardapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.ui.components.elements.BuildBoxView
import com.came.parkare.dashboardapp.ui.components.elements.BuildColumnView
import com.came.parkare.dashboardapp.ui.components.elements.BuildImageView
import com.came.parkare.dashboardapp.ui.components.elements.BuildRowView
import com.came.parkare.dashboardapp.ui.components.elements.BuildTextView
import com.came.parkare.dashboardapp.ui.components.itemStyles.LicensePlateItemStyle
import com.came.parkare.dashboardapp.ui.components.itemStyles.imageItemStyle
import com.came.parkare.dashboardapp.ui.theme.LicensePlateFont
import com.came.parkare.dashboardapp.ui.theme.Rubik
import com.came.parkare.dashboardapp.ui.theme.hexToColor

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
                BuildBoxView(box = elementModel.data, textSizeScale = textSizeScale, scaleFactor = scaleFactor)
            }
            is ElementModel.SpacerModel -> {
                Spacer(modifier = Modifier.size((elementModel.data.value.toFloat() * scaleFactor).dp))
            }
            is ElementModel.ColumnModel -> {
                BuildColumnView(column = elementModel.data, textSizeScale = textSizeScale, scaleFactor = scaleFactor)
            }
            is ElementModel.TextModel -> {
                BuildTextView(elementModel.data, scaleFactor)
            }

            is ElementModel.RowModel -> {
                BuildRowView(row = elementModel.data, textSizeScale = textSizeScale, scaleFactor = scaleFactor)
            }

            is ElementModel.ImageModel -> {
                BuildImageView(image = elementModel.data, scaleFactor = scaleFactor)
            }
            is ElementModel.VideoModel -> {}
        }
    }
}
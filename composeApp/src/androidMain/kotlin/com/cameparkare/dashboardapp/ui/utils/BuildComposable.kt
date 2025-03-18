package com.cameparkare.dashboardapp.ui.utils

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.ui.components.itemStyles.LicensePlateItemStyle
import com.cameparkare.dashboardapp.ui.components.itemStyles.imageItemStyle
import com.cameparkare.dashboardapp.ui.theme.LicensePlateFont
import com.cameparkare.dashboardapp.ui.theme.Rubik

//
//@Composable
//fun BuildComposable(elementModel: ElementModel, textSizeScale: Int): Unit {
//    val scaleFactor = (textSizeScale / 10f).coerceIn(0.5f, 3f)
//    val state = remember {
//        MutableTransitionState(initialState = false).apply {
//            targetState = true
//        }
//    }
//    return AnimatedVisibility(
//        visibleState = state,
//        enter = fadeIn(animationSpec = tween(1000)),
//        exit = fadeOut(animationSpec = tween(1000))
//    ) {
//        when (elementModel) {
//            is ElementModel.BoxModel -> {
//                // Box Composable with appropriate styling
//                Box(
//                    modifier = Modifier
//                        .shadow(
//                            elevation = if (elementModel.data.style.hasShadow) 1.5.dp else 0.dp,
//                            shape = RoundedCornerShape(elementModel.data.style.roundBorder.dp)
//                        )
//                        .background(
//                            Color(android.graphics.Color.parseColor(elementModel.data.style.backgroundColor))
//                                .copy(alpha = (elementModel.data.style.density / 100f))
//                        )
//                        .fillMaxWidth()
//                        .padding(((elementModel.data.style.padding ?: 1).toFloat() * scaleFactor).dp), contentAlignment = Alignment.Center
//                ) {
//                    // Recursively build content Composable
//                    Box(modifier = Modifier.align(Alignment.Center)) {
//                        elementModel.data.content.forEach { contentDto ->
//                            BuildComposable(contentDto, textSizeScale)
//                        }
//                    }
//                }
//            }
//            is ElementModel.SpacerModel -> {
//                Spacer(modifier = Modifier.size((elementModel.data.value.toFloat() * scaleFactor).dp))
//            }
//            is ElementModel.ColumnModel -> {
//                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.spacedBy(
//                        (elementModel.data.spacing.toFloat() * scaleFactor).dp,
//                        alignment = Alignment.CenterVertically),
//                    modifier = Modifier
//                        .shadow(
//                            elevation = if (elementModel.data.style.hasShadow) 1.5.dp else 0.dp,
//                            shape = RoundedCornerShape(elementModel.data.style.roundBorder.dp)
//                        )
//                        .background(
//                            Color(android.graphics.Color.parseColor(elementModel.data.style.backgroundColor))
//                                .copy(alpha = (elementModel.data.style.density / 100f))
//                        )
//                        .fillMaxWidth()
//                        .padding(((elementModel.data.style.padding ?: 1).toFloat() * scaleFactor).dp)
//                ) {
//                    elementModel.data.content.forEach { contentDto ->
//                        BuildComposable(contentDto, textSizeScale)
//                    }
//                }
//            }
//            is ElementModel.TextModel -> {
//                val weight = when {
//                    elementModel.data.fontWeight.lowercase().contains("bold") -> FontWeight.Bold
//                    elementModel.data.fontWeight.lowercase().contains("medium") -> FontWeight.Medium
//                    else -> FontWeight.Normal
//                }
//                val textSize = (elementModel.data.textSize.toFloat() * scaleFactor)
//                    .coerceAtLeast((elementModel.data.textSize - 8).toFloat())
//                if (elementModel.data.dashboardItemId.contains("license-plate-value")) {
//                    println("license-plate-value==== ${elementModel.data.defaultText}")
//                    LicensePlateItemStyle { modifier ->
//                        Text(
//                            text = elementModel.data.defaultText,
//                            fontSize = textSize.sp,
//                            fontFamily = LicensePlateFont,
//                            letterSpacing = 2.sp,
//                            fontWeight = weight,
//                            textAlign = TextAlign.Center,
//                            color = Color(android.graphics.Color.parseColor(elementModel.data.textColor)),
//                            modifier = modifier
//                                .padding(((elementModel.data.style.padding ?: 1).toFloat() * scaleFactor).dp)
//                        )
//                    }
//
//                } else {
//                    val animatedText = animateFloatAsState(targetValue = scaleFactor)
//                    Text(
//                        text = elementModel.data.defaultText,
//                        fontSize = textSize.sp * animatedText.value,
//                        fontFamily = Rubik,
//                        fontWeight = weight,
//                        textAlign = TextAlign.Center,
//                        color = Color(android.graphics.Color.parseColor(elementModel.data.textColor)),
//                        modifier = Modifier.padding(((elementModel.data.style.padding ?: 1).toFloat() * scaleFactor).dp)
//                    )
//                }
//            }
//
//            is ElementModel.RowModel -> {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(
//                        (elementModel.data.spacing.toFloat() * scaleFactor).dp,
//                        alignment = Alignment.CenterHorizontally
//                    ),
//                    modifier = Modifier
//                        .shadow(
//                            elevation = if (elementModel.data.style.hasShadow) 1.5.dp else 0.dp,
//                            shape = RoundedCornerShape(elementModel.data.style.roundBorder.dp)
//                        )
//                        .background(
//                            Color(android.graphics.Color.parseColor(elementModel.data.style.backgroundColor))
//                                .copy(alpha = (elementModel.data.style.density / 100f))
//                        )
//                        .fillMaxWidth()
//                        .padding(((elementModel.data.style.padding ?: 1).toFloat() * scaleFactor).dp)
//                ) {
//                    elementModel.data.content.forEach { contentDto ->
//                        BuildComposable(contentDto, textSizeScale)
//                    }
//                }
//            }
//
//            is ElementModel.ImageModel -> {
//                if (!elementModel.data.folderPath.isNullOrBlank()) {
//                    Image(
//                        painter = imageItemStyle(elementModel.data.folderPath),
//                        contentDescription = null,
//                        contentScale = ContentScale.FillHeight,
//                        modifier = Modifier
//                            .size(((elementModel.data.style.width ?: 1).toFloat() * scaleFactor).dp,
//                                ((elementModel.data.style.height ?: 1).toFloat() * scaleFactor).dp)
//                    )
//                } else Unit
//            }
//            is ElementModel.VideoModel -> {}
//        }
//    }
//}
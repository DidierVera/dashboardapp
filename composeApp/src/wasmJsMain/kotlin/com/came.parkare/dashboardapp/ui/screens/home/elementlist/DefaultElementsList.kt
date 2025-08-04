@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home.elementlist

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.toModel
import com.came.parkare.dashboardapp.ui.screens.settings.components.BuildElement
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.default_elements_label
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun DefaultElementsList(modifier: Modifier = Modifier){
    val viewModel: ElementListViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    if (state.showDefaultTab){
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.widthIn(max = 260.dp).verticalScroll(ScrollState(0))
                .fillMaxSize().floatingButton().padding(8.dp).graphicsLayer {
                    this.scaleX = 1.0f
                    this.scaleY = 1.0f
                }) {
            Text(stringResource(Res.string.default_elements_label), fontWeight = FontWeight.Bold)
            HorizontalDivider()
            LoadElements(state.defaultItems)
        }
    }
}

@Composable
private fun LoadElements(elements: List<ElementDto>){
    for(mItem in elements){
        var elementType = ""
        Column(verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.floatingButton {

            }.fillMaxWidth().graphicsLayer {
                this.scaleX = 0.90f
                this.scaleY = 0.90f
            }, contentAlignment = Alignment.Center) {
                when(mItem){
                    is ElementDto.BoxDto -> {
                        BuildElement(
                            mItem.data.toModel(),
                            10, emptyList()
                        )
                        elementType = mItem.elementType
                    }
                    is ElementDto.ColumnDto -> {
                        BuildElement(
                            mItem.data.toModel(),
                            10, emptyList()
                        )

                        elementType = mItem.elementType
                    }
                    is ElementDto.ImageDto -> {
                        BuildElement(
                            mItem.data.toModel(),
                            10, emptyList()
                        )

                        elementType = mItem.elementType
                    }
                    is ElementDto.RowDto -> {
                        BuildElement(
                            mItem.data.toModel(),
                            10, emptyList()
                        )

                        elementType = mItem.elementType
                    }
                    is ElementDto.SpacerDto -> {
                        BuildElement(
                            mItem.data.toModel(),
                            10, emptyList()
                        )

                        elementType = mItem.elementType
                    }
                    is ElementDto.TextDto -> {
                        BuildElement(
                            mItem.data.toModel(),
                            10, emptyList()
                        )

                        elementType = mItem.elementType
                    }
                    is ElementDto.VideoDto -> {
                        BuildElement(
                            mItem.data.toModel(),
                            10, emptyList()
                        )

                        elementType = mItem.elementType
                    }
                }
            }

            Text(text = elementType, fontWeight = FontWeight.SemiBold)

            HorizontalDivider()
        }

    }
}
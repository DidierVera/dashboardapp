@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home.properties

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun PropertiesEditor(
    modifier: Modifier = Modifier
){
    val viewModel: PropertiesViewModel = koinViewModel()
    viewModel.initTab()
    val state by viewModel.state.collectAsState()

    if(state.showTab){
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.widthIn(max = 260.dp).verticalScroll(ScrollState(0))
                .fillMaxSize().floatingButton().padding(8.dp).graphicsLayer {
                    this.scaleX = 1.0f
                    this.scaleY = 1.0f
                }) {

        }
        when(val item = state.element){
            is ElementDto.BoxDto -> BoxProperties(item.data){

            }
            is ElementDto.ColumnDto -> ColumnProperties(item.data){

            }
            is ElementDto.ImageDto -> ImageProperties(item.data){

            }
            is ElementDto.RowDto -> RowProperties(item.data){

            }
            is ElementDto.SpacerDto -> SpacerProperties(item.data){

            }
            is ElementDto.TextDto -> TextProperties(item.data){

            }
            is ElementDto.VideoDto -> VideoProperties(item.data){

            }
            null -> {}
        }
    }
}
@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.home.elementlist

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.screen.elements.ElementDto
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ElementList(modifier: Modifier = Modifier) {
    val viewModel: ElementListViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = modifier.widthIn(max = 260.dp)
    ) { padding ->
        if (state.showTab){
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = modifier.verticalScroll(ScrollState(0))
                    .fillMaxSize().padding(padding).floatingButton().padding(8.dp).graphicsLayer {
                        this.scaleX = 1.0f
                        this.scaleY = 1.0f
                    }) {
                Text("Blank Elements", fontWeight = FontWeight.Bold)
                HorizontalDivider()
                loadElements(state.blankElements)
            }
        }
    }
}

@Composable
fun loadElements(blankElements: List<ElementDto>) {
    blankElements.forEach { element ->
        when(element){
            is ElementDto.BoxDto -> { BlankElement(element.elementType){

                }
            }
            is ElementDto.ColumnDto -> { BlankElement(element.elementType){

            }
            }
            is ElementDto.ImageDto -> { BlankElement(element.elementType){

            }
            }
            is ElementDto.RowDto -> { BlankElement(element.elementType){

            }
            }
            is ElementDto.SpacerDto -> { BlankElement(element.elementType){

            }
            }
            is ElementDto.TextDto -> { BlankElement(element.elementType){

            }
            }
            is ElementDto.VideoDto -> { BlankElement(element.elementType){

            }
            }
        }
    }
}

@Composable
private fun BlankElement(name: String, onItemAdd: () -> Unit){
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().floatingButton{
            onItemAdd.invoke()
        }.padding(8.dp)) {
        Text(text = name)

        Icon( Icons.Default.Add, contentDescription = "Add")
    }
}
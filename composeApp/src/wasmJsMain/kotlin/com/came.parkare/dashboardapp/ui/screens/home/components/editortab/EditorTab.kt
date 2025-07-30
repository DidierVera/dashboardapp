package com.came.parkare.dashboardapp.ui.screens.home.components.editortab

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.ui.components.AppLabel
import com.came.parkare.dashboardapp.ui.screens.home.components.AccordionField
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.text_size_scale_label

@Composable
fun EditorTab(modifier: Modifier = Modifier, element: ElementModel? = null){
    Box(modifier = modifier.floatingButton()){
        Column(verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.verticalScroll(ScrollState(0))) {
            SpecificFields(element)
            CommonFields(element)
            AdvanceConfig(element)
        }
    }
}

@Composable
fun AdvanceConfig(element: ElementModel?) {
    AccordionField("Common properties"){
        when(element){
            is ElementModel.SpacerModel -> TODO()
            else -> {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextField(//text size scale
                        value = "",
                        onValueChange ={ },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        singleLine = true,
                        label = { AppLabel(Res.string.text_size_scale_label) }
                    )
                }
            }
        }
    }
}

@Composable
fun CommonFields(element: ElementModel?) {
    AccordionField("Common properties"){
        when(element){
            is ElementModel.BoxModel -> {

            }
            is ElementModel.ColumnModel -> {

            }
            is ElementModel.ImageModel -> {

            }
            is ElementModel.RowModel -> {

            }
            is ElementModel.SpacerModel -> {}
            is ElementModel.TextModel -> {}
            is ElementModel.VideoModel -> {}
            null -> {
                Text("Common properties text child")
                Text("Common properties text child")
            }
        }
    }
}


@Composable
fun SpecificFields(element: ElementModel?) {
    AccordionField("Specific properties"){
        when(element){
            is ElementModel.BoxModel -> {

            }
            is ElementModel.ColumnModel -> {

            }
            is ElementModel.ImageModel -> {

            }
            is ElementModel.RowModel -> {

            }
            is ElementModel.SpacerModel -> {}
            is ElementModel.TextModel -> {}
            is ElementModel.VideoModel -> {}
            null -> {
                Text("Specific properties text child")
            }

        }
    }
}

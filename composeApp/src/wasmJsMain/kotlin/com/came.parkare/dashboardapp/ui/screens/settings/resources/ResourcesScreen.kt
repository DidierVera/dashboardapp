@file:OptIn(KoinExperimentalAPI::class)

package com.came.parkare.dashboardapp.ui.screens.settings.resources

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.came.parkare.dashboardapp.ui.components.AppButton
import com.came.parkare.dashboardapp.ui.components.AppLabel
import com.came.parkare.dashboardapp.ui.components.Base64Image
import com.came.parkare.dashboardapp.ui.screens.settings.components.filepicker.DialogPickerDialog
import com.came.parkare.dashboardapp.ui.screens.settings.components.filepicker.FilePickerDialogState
import com.came.parkare.dashboardapp.ui.theme.CameBlueColor
import com.came.parkare.dashboardapp.ui.theme.LightGrayColor
import com.came.parkare.dashboardapp.ui.theme.WhiteColor
import com.came.parkare.dashboardapp.ui.theme.style.floatingButton
import com.came.parkare.dashboardapp.ui.theme.style.shadowContainer
import com.came.parkare.dashboardapp.ui.utils.FontLoader
import dashboardapp.composeapp.generated.resources.Res
import dashboardapp.composeapp.generated.resources.font_resources_label
import dashboardapp.composeapp.generated.resources.ic_close
import dashboardapp.composeapp.generated.resources.ic_download
import dashboardapp.composeapp.generated.resources.ic_import_export
import dashboardapp.composeapp.generated.resources.image_resources_label
import dashboardapp.composeapp.generated.resources.save_button
import dashboardapp.composeapp.generated.resources.upload_file_button
import kotlinx.coroutines.CoroutineStart
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.skia.Typeface
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Composable
fun ResourcesScreen() {
    val viewModel: ResourcesViewModel = koinViewModel()
    viewModel.initTab()
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isCompact = maxWidth < 600.dp

        if (isCompact) {
            ResourcesTabLayout()
        } else {
            ResourcesColumnLayout()
        }
    }
}

@Composable
private fun ResourcesColumnLayout() {
    Column(modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {

        Row(modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ImagesPanel(modifier = Modifier.weight(1f))
            VerticalDivider()
            FontsPanel(modifier = Modifier.weight(1f))   // <-- nuevo
        }
    }
}

@Composable
private fun ResourcesTabLayout() {
    var selectedTab by remember { mutableStateOf(0) }
    val imagesLabel = stringResource(Res.string.image_resources_label)
    val fontsLabel = stringResource(Res.string.font_resources_label)
    val tabs = listOf(imagesLabel, fontsLabel)

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) })
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            when (selectedTab) {
                0 -> ImagesPanel()
                1 -> FontsPanel()
            }
        }
    }
}

@Composable
fun ImagesPanel(modifier: Modifier = Modifier) {
    val viewModel: ResourcesViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    Column(
        modifier = modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Header con label y botón de subida
        AppLabel(Res.string.image_resources_label)
        DialogPickerDialog(
            buttonText = Res.string.upload_file_button,
            multipleFiles = true,
            clearFiles = state.clearSelectedFiles,
            onFilesSelected = { items -> viewModel.setImages(items) }
        )

        // Grid de imágenes
        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier.weight(1f)   // <-- ocupa el espacio restante del Column
        ) {
            items(items = state.imagesResources) { image ->
                ImageGridItem(image = image)
            }
        }
        SaveButton(modifier = Modifier.align(Alignment.End).padding(8.dp))
    }
}

@Composable
private fun FontsPanel(modifier: Modifier = Modifier){
    val viewModel: ResourcesViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    Column(
        modifier = modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppLabel(Res.string.font_resources_label)

        DialogPickerDialog(
            buttonText = Res.string.upload_file_button,
            multipleFiles = true,
            fileExtensions = listOf("ttf", "otf", "ttc"),
            clearFiles = state.clearSelectedFiles,
            onFilesSelected = { files -> viewModel.setFont(files) }
        )

        LazyColumn {
            items(items = state.fontResources){ item ->
                FontRowItem(item)
            }
        }
    }
}

@OptIn(ExperimentalEncodingApi::class)
@Composable
private fun FontRowItem(font: FilePickerDialogState) {
    val fontLoader: FontLoader = koinInject()

    LaunchedEffect(font.fileContentsRaw) {
        val bytes = Base64.decode(font.fileContentsRaw)
        val typeface = fontLoader.loadTypeface(font.fileNames, bytes)
    }

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth().padding(8.dp).shadowContainer().padding(4.dp)) {

        Text(text = font.fileNames, style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f))
    }
}

@Composable
private fun SaveButton(modifier: Modifier = Modifier) {
    val viewModel: ResourcesViewModel = koinViewModel()
    // Save button
    AppButton(
        text = stringResource(Res.string.save_button),
        onClick = {
            viewModel.saveChanges()
        },
        buttonColors = ButtonDefaults.buttonColors(
            containerColor = CameBlueColor,
            contentColor = WhiteColor
        ), modifier = modifier
    )
}


@Composable
private fun ImageGridItem(image: FilePickerDialogState) {
    val viewModel: ResourcesViewModel = koinViewModel()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(100.dp)) {
            Base64Image(
                base64String = image.fileContentsRaw,
                modifier = Modifier
                    .fillMaxSize()
                    .floatingButton(background = Color.LightGray),
                contentScale = ContentScale.FillBounds
            )
            // Botón eliminar — top end (igual que antes)
            DeleteButton(modifier = Modifier.size(24.dp).align(Alignment.TopEnd)) {
                viewModel.removeImage(image)
            }
            // Botón descargar — bottom end
            DownloadButton(
                modifier = Modifier.size(24.dp).align(Alignment.BottomEnd)) {
                viewModel.downloadImage(image)
            }
        }
        Text(
            text = image.fileNames,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .widthIn(max = 80.dp)
        )
    }
}
@Composable
private fun DownloadButton(modifier: Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = { onClick.invoke() },
        modifier = modifier.fillMaxSize(0.82f)
    ) {
        Icon(painter = painterResource(Res.drawable.ic_download),
            contentDescription = null, modifier = Modifier.size(40.dp).background(Color.LightGray))
    }
}

@Composable
private fun DeleteButton(modifier: Modifier, onClick: () -> Unit) {
    IconButton(onClick = { onClick.invoke() },
        modifier = modifier.fillMaxSize(0.82F)) {
        Image(painter = painterResource(Res.drawable.ic_close),
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = modifier.background(Color.LightGray).fillMaxSize())
    }
}
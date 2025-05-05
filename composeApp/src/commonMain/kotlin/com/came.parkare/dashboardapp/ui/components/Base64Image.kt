@file:OptIn(ExperimentalEncodingApi::class)

package com.came.parkare.dashboardapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


@Composable
fun Base64Image(
    base64String: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.FillBounds
) {
    Box(modifier = modifier) {
        Image(
            bitmap = base64String.decodeBase64ToByteArray().toImageBitmap(),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier.align(Alignment.Center)
        )
    }

}

@OptIn(ExperimentalResourceApi::class)
private fun ByteArray.toImageBitmap(): ImageBitmap {
    return this.decodeToImageBitmap ()
}

fun String.isBase64(): Boolean {
    // Handle data URI scheme (e.g., "data:image/png;base64,...")
    val pureBase64 = this.substringAfterLast(",")

    // Base64 regex pattern
    val pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$"
    return pureBase64.matches(pattern.toRegex())
}

private fun String.decodeBase64ToByteArray(): ByteArray {
    val byteArray = encodeToByteArray()
    return Base64.decode(byteArray, 0, byteArray.size)
}
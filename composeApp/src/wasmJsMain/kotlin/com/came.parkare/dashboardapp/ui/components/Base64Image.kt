@file:OptIn(ExperimentalEncodingApi::class)

package com.came.parkare.dashboardapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
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

private fun ByteArray.toImageBitmap() =
    org.jetbrains.skia.Image.makeFromEncoded(this).toComposeImageBitmap()

private fun String.decodeBase64ToByteArray(): ByteArray {
    val byteArray = encodeToByteArray()
    return Base64.decode(byteArray, 0, byteArray.size)
}
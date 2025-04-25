package com.came.parkare.dashboardapp.ui.utils

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.url.URL
import org.w3c.files.Blob
import org.w3c.files.BlobPropertyBag

object FileDownloader {
    fun downloadFile(filename: String, content: String) {
        // Convert Kotlin String to JS compatible format
        val jsArray = JsArray<JsAny?>()
        jsArray[0] = content.toJsString()

        // Create blob with proper typing
        val blob = Blob(jsArray,
            BlobPropertyBag(type = "text/plain"))

        val url = URL.createObjectURL(blob)

        val link = document.createElement("a").unsafeCast<HTMLAnchorElement>()
        link.href = url
        link.download = filename
        link.style.display = "none"

        document.body?.appendChild(link)
        link.click()

        // Cleanup
        window.setTimeout({
            URL.revokeObjectURL(url)
            document.body?.removeChild(link)
        }, 100)
    }
}
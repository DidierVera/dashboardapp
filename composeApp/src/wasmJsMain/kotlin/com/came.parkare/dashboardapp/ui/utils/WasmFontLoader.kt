package com.came.parkare.dashboardapp.ui.utils

import com.came.parkare.dashboardapp.ui.components.toBase64

class WasmFontLoader : FontLoader {
    override suspend fun loadTypeface(fileName: String, bytes: ByteArray): String? {
        return try {
            // Devuelve una URL blob para inyectarla en CSS
            registerFontInBrowser(fileName, bytes.toBase64())
            fileName  // se usa como font-family name en CSS
        } catch (e: Exception) {
            null
        }
    }
}

@JsFun("""
    function(fontName, base64) {
        const style = document.createElement('style');
        style.textContent = '@font-face { font-family: "' + fontName + '"; src: url("data:font/ttf;base64,' + base64 + '"); }';
        document.head.appendChild(style);
    }
""")
external fun registerFontInBrowser(fontName: String, base64: String)
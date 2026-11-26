package com.came.parkare.dashboardapp.domain.models

/**
 * Position of the content of a screen inside the area left by its margins.
 * [key] is the value of "content-alignment" in the screens JSON.
 */
enum class ScreenAlignment(val key: String, val vertical: Vertical, val horizontal: Horizontal) {
    TOP_START("top-start", Vertical.TOP, Horizontal.START),
    TOP_CENTER("top-center", Vertical.TOP, Horizontal.CENTER),
    TOP_END("top-end", Vertical.TOP, Horizontal.END),
    CENTER_START("center-start", Vertical.CENTER, Horizontal.START),
    CENTER("center", Vertical.CENTER, Horizontal.CENTER),
    CENTER_END("center-end", Vertical.CENTER, Horizontal.END),
    BOTTOM_START("bottom-start", Vertical.BOTTOM, Horizontal.START),
    BOTTOM_CENTER("bottom-center", Vertical.BOTTOM, Horizontal.CENTER),
    BOTTOM_END("bottom-end", Vertical.BOTTOM, Horizontal.END);

    enum class Vertical { TOP, CENTER, BOTTOM }
    enum class Horizontal { START, CENTER, END }

    companion object {
        /** Used by the screens that do not define "content-alignment" */
        val DEFAULT = CENTER

        fun fromKey(key: String?): ScreenAlignment =
            entries.firstOrNull { it.key.equals(key?.trim(), ignoreCase = true) } ?: DEFAULT

        /**
         * The default is not written to the JSON, so the screens that keep it can still be
         * shared with monitors whose version does not know the key.
         */
        fun toKeyOrNull(alignment: ScreenAlignment): String? =
            if (alignment == DEFAULT) null else alignment.key
    }
}

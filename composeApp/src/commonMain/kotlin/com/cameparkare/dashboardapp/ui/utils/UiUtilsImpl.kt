package com.cameparkare.dashboardapp.ui.utils

import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.domain.usecases.GetCardClassTranslations

class UiUtilsImpl(
    private val getCardClassTranslations: GetCardClassTranslations,
    private val appLogger: AppLogger,
    private val filesUtils: FilesUtils
) : UiUtils {
    override suspend fun buildDashboardItem(
        items: List<ElementModel>,
        dits: Map<String, String>?,
        lang: String
    ): List<ElementModel> {
        val resultList: MutableList<ElementModel> = mutableListOf()
        for (item in items) {
            when (item) {
                is ElementModel.TextModel -> resultList.add(processTextElement(item, lang, dits))
                is ElementModel.ImageModel -> resultList.add(processImageElement(item, dits))
                is ElementModel.VideoModel -> resultList.add(processVideoElement(item, dits))
                is ElementModel.BoxModel -> {
                    resultList.add(item.copy(data = item.data.copy(content = buildDashboardItem(item.data.content, dits, lang))))
                }
                is ElementModel.ColumnModel -> {
                    resultList.add(item.copy(data = item.data.copy(content = buildDashboardItem(item.data.content, dits, lang))))
                }
                is ElementModel.RowModel -> {
                    resultList.add(item.copy(data = item.data.copy(content = buildDashboardItem(item.data.content, dits, lang))))
                }
                else -> resultList.add(item)
            }
        }
        return resultList
    }

    private fun processVideoElement(videoModel: ElementModel.VideoModel, dits: Map<String, String>?): ElementModel {
        val dataKey = videoModel.data.dataKey
        val defaultFile = videoModel.data.fileName?.takeIf { it.isNotEmpty() }?.let {
            filesUtils.getVideoFromDirectory("/Dashboard", it)
        }

        return if (!dataKey.isNullOrEmpty()) {
            val ditValue = getValueFromDit(dataKey, dits, defaultFile.orEmpty())
            videoModel.copy(data = videoModel.data.copy(folderPath = validateViaTItem(ditValue, defaultFile)))
        } else {
            defaultFile?.let { videoModel.copy(data = videoModel.data.copy(folderPath = it)) } ?: videoModel
        }
    }

    private suspend fun processTextElement(textModel: ElementModel.TextModel, lang: String, dits: Map<String, String>?): ElementModel {
        val dataKey = textModel.data.dataKey
        val defaultText = textModel.data.defaultText

        return if (!dataKey.isNullOrEmpty()) {
            val ditValue = getValueFromDit(dataKey, dits, defaultText)
            val newText = validateEspecialItems(textModel.data.dashboardItemId, ditValue, lang)
            textModel.copy(data = textModel.data.copy(defaultText = newText ?: defaultText))
        } else {
            val translations = textModel.data.translations?.get(lang)
            textModel.copy(data = textModel.data.copy(defaultText = translations ?: defaultText))
        }
    }

    private fun processImageElement(imageModel: ElementModel.ImageModel, dits: Map<String, String>?): ElementModel {
        val dataKey = imageModel.data.dataKey
        val defaultFile = imageModel.data.fileName?.takeIf { it.isNotEmpty() }?.let {
            filesUtils.getImageFromDirectory("/Dashboard", it)
        }

        return if (!dataKey.isNullOrEmpty()) {
            val ditValue = getValueFromDit(dataKey, dits, defaultFile.orEmpty())
            imageModel.copy(data = imageModel.data.copy(folderPath = validateViaTItem(ditValue, defaultFile)))
        } else {
            defaultFile?.let { imageModel.copy(data = imageModel.data.copy(folderPath = it)) } ?: imageModel
        }
    }

    private fun getValueFromDit(dataKey: String, dits: Map<String, String>?, defaultValue: String): String {
        return dits?.get(dataKey) ?: defaultValue
    }

    private fun validateViaTItem(ditValue: String, defaultIcon: String?): String? {
        appLogger.trackLog("VIAT-LOGO ITEM", ditValue)
        return if (ditValue == "4") {
            defaultIcon ?: "0" // Mostrar icono por defecto en cada plataforma
        } else null
    }

    private suspend fun validateEspecialItems(lineId: String, text: String?, lang: String): String? {
        return when (lineId) {
            "entry-type-value" -> {
                appLogger.trackLog("CARD_CLASS ITEM", "$lineId: $text")
                val code = text?.toIntOrNull() ?: return null
                when (val cardClassText = getCardClassTranslations.invoke(code, lang)) {
                    is ServiceResult.Error -> text
                    is ServiceResult.Success -> cardClassText.data?.translation
                }
            }
            "license-plate-value" -> {
                appLogger.trackLog("LICENSE-PLATE", "$lineId: $text")
                insertSpaces(text)
            }
            else -> text
        }
    }

    private fun insertSpaces(input: String?): String? {
        val result = StringBuilder()

        if (input.isNullOrBlank()) return input

        for (i in input.indices) {
            result.append(input[i])
            if (i < input.length - 1) {
                val currentChar = input[i]
                val nextChar = input[i + 1]

                if (currentChar.isDigit() && nextChar.isLetter() || currentChar.isLetter() && nextChar.isDigit()) {
                    result.append(' ')
                }
            }
        }

        return result.toString()
    }
}

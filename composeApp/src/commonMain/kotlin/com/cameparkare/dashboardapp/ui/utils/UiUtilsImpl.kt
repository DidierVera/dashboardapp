package com.cameparkare.dashboardapp.ui.utils

import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.domain.models.terminal.DitResponseModel
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive

class UiUtilsImpl(
    private val appLogger: AppLogger,
    private val filesUtils: FilesUtils
) : UiUtils {
    override suspend fun buildDashboardItem(items: List<ElementModel>, dits: List<DitResponseModel>?, lang: String): List<ElementModel> {
        val resultList: MutableList<ElementModel> = mutableListOf()
        for (item in items) {
            when (item) {
                is ElementModel.TextModel -> {
                    val condition = item.data.dataKey
                    val validValue = item.data.validValue
                    val ditTypeCode = item.data.ditTypeCode
                    if (shouldRenderElement(condition, ditTypeCode, validValue, dits)) {
                        resultList.add(processTextElement(item, lang, dits))
                    }
                }
                is ElementModel.ImageModel -> {
                    val condition = item.data.dataKey
                    val validValue = item.data.validValue
                    val ditTypeCode = item.data.ditTypeCode
                    if (shouldRenderElement(condition, ditTypeCode, validValue, dits)) {
                        resultList.add(processImageElement(item, dits))
                    }
                }
                is ElementModel.VideoModel -> {
                    val condition = item.data.dataKey
                    val validValue = item.data.validValue
                    val ditTypeCode = item.data.ditTypeCode
                    if (shouldRenderElement(condition, ditTypeCode, validValue, dits)) {
                        resultList.add(processVideoElement(item, dits))
                    }
                }
                is ElementModel.BoxModel -> {
                    val condition = item.data.dataKey
                    val validValue = item.data.validValue
                    val ditTypeCode = item.data.ditTypeCode
                    if (shouldRenderElement(condition, ditTypeCode, validValue, dits)) {
                        resultList.add(item.copy(data = item.data.copy(content = buildDashboardItem(item.data.content, dits, lang))))
                    }
                }
                is ElementModel.ColumnModel -> {
                    val condition = item.data.dataKey
                    val validValue = item.data.validValue
                    val ditTypeCode = item.data.ditTypeCode
                    if (shouldRenderElement(condition, ditTypeCode, validValue, dits)) {
                        resultList.add(item.copy(data = item.data.copy(content = buildDashboardItem(item.data.content, dits, lang))))
                    }
                }
                is ElementModel.RowModel ->  {
                    val condition = item.data.dataKey
                    val validValue = item.data.validValue
                    val ditTypeCode = item.data.ditTypeCode
                    if (shouldRenderElement(condition, ditTypeCode, validValue, dits)) {
                        resultList.add(item.copy(data = item.data.copy(content = buildDashboardItem(item.data.content, dits, lang))))
                    }
                }
                else -> resultList.add(item)
            }
        }
        return resultList
    }

    private fun shouldRenderElement(condition: String?, ditTypeCode: Int?, validValue: Int?, dits: List<DitResponseModel>?): Boolean {
        if (condition != null && validValue != null) {
            val ditValue = getValueFromDit(condition, ditTypeCode, dits, "------")
            return ditValue == validValue.toString()
        }
        return true
    }

    private fun processVideoElement(videoModel: ElementModel.VideoModel, dits: List<DitResponseModel>?): ElementModel {
        val dataKey = videoModel.data.dataKey
        val ditTypeCode = videoModel.data.ditTypeCode
        val defaultFile = videoModel.data.fileName?.takeIf { it.isNotEmpty() }?.let {
            filesUtils.getVideoFromDirectory("/Dashboard", it)
        }

        return if (!dataKey.isNullOrEmpty()) {
            val ditValue = getValueFromDit(dataKey, ditTypeCode, dits, defaultFile.orEmpty())
            videoModel.copy(data = videoModel.data.copy(folderPath = validateViaTItem(ditValue, defaultFile)))
        } else {
            defaultFile?.let { videoModel.copy(data = videoModel.data.copy(folderPath = it)) } ?: videoModel
        }
    }

    private fun processTextElement(textModel: ElementModel.TextModel, lang: String, dits: List<DitResponseModel>?): ElementModel {
        val dataKey = textModel.data.dataKey
        val defaultText = textModel.data.defaultText
        val ditTypeCode = textModel.data.ditTypeCode

        // If dataKey is null or empty, use translations or defaultText
        if (dataKey.isNullOrEmpty()) {
            val translations = textModel.data.translations?.get(lang)
            return textModel.copy(data = textModel.data.copy(defaultText = translations ?: defaultText))
        }

        // Get the value from Dits
        val ditValue = getValueFromDit(dataKey, ditTypeCode, dits, defaultText)

        // Check if the ditValue matches the validValue (if validValue is set)
        val isValidValue = textModel.data.validValue?.let { ditValue == it.toString() } ?: true

        // Determine the text to display
        val newText = when {
            // Use translations if validValue matches and translations exist
            isValidValue && textModel.data.translations != null -> textModel.data.translations[lang]
            // Otherwise, validate special items
            else -> validateEspecialItems(textModel.data.dashboardItemId, ditValue)
        }

        // Return the updated TextModel
        return textModel.copy(data = textModel.data.copy(defaultText = newText ?: defaultText))
    }

    private fun processImageElement(imageModel: ElementModel.ImageModel, dits: List<DitResponseModel>?): ElementModel {
        val dataKey = imageModel.data.dataKey
        val ditTypeCode = imageModel.data.ditTypeCode
        val defaultFile = if(imageModel.data.fileName.isNullOrEmpty()) null else {
            filesUtils.getImageFromDirectory("/Dashboard",
                imageModel.data.fileName)
        }

        return if (!dataKey.isNullOrEmpty()) {
            val ditValue = getValueFromDit(dataKey, ditTypeCode, dits, defaultFile.orEmpty())
            imageModel.copy(data = imageModel.data.copy(
                folderPath = validateViaTItem(ditValue = ditValue, defaultIcon = defaultFile)))
        } else {
            if (!defaultFile.isNullOrEmpty())
                imageModel.copy(data = imageModel.data.copy(folderPath = defaultFile))
            else
                imageModel
        }
    }

    private fun getValueFromDit(dataKey: String, ditTypeCode: Int?, dits: List<DitResponseModel>?, defaultValue: String): String {
        var dataResult = defaultValue
        if(dits.isNullOrEmpty()) return defaultValue

        dits.forEach { dit ->
            //check the dit type first
            if(ditTypeCode == dit.ditType.ditTypeCode){
                val ditObj = dit.additionalProperties

                if (ditObj != null) {
                    val value = ditObj[dataKey]
                    if (value != null && value !is JsonNull) {
                        // Decode the JSON value to a raw string
                        dataResult = when (value) {
                            is JsonPrimitive -> value.content // Extract the raw content
                            else -> Json.encodeToString(value) // Fallback for other JSON types
                        }
                        println("Result dits obj === $dataResult")
                    }
                }
            }
        }
        return dataResult
    }

    private fun validateViaTItem(ditValue: String, defaultIcon: String?): String?{
        return if(ditValue == "4"){
            defaultIcon ?: "default_via-t_icon"
        } else null
    }

    private fun validateEspecialItems(lineId: String, text: String?): String? {
        return when(lineId){
            "license-plate-value" -> {
                insertSpaces(text)
            }
            else -> {
                text
            }
        }
    }

    private fun insertSpaces(input: String?): String? {
        val result = StringBuilder()

        if(input.isNullOrBlank())return input

        for (i in input.indices) {
            result.append(input[i])
            if (i < input.length - 1) {
                val currentChar = input[i]
                val nextChar = input[i + 1]

                // Check if the current char is a digit and the next is a letter, or vice versa
                if (currentChar.isDigit() && nextChar.isLetter() || currentChar.isLetter() && nextChar.isDigit()) {
                    result.append(' ')
                }
            }
        }

        return result.toString()
    }
}

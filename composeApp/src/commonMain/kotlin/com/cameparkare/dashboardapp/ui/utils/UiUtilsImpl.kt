package com.cameparkare.dashboardapp.ui.utils

import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.domain.usecases.GetCardClassTranslations
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonNull
import kotlinx.serialization.json.jsonObject


class UiUtilsImpl(
    private val getCardClassTranslations: GetCardClassTranslations,
    private val appLogger: AppLogger,
    private val filesUtils: FilesUtils
): UiUtils {
    override suspend fun buildDashboardItem(
        items: List<ElementModel>,
        dits: JsonArray?,
        lang: String
    ): List<ElementModel> {
        val resultList: MutableList<ElementModel> = mutableListOf()
        for (item in items){
            when(item){
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

    private fun processVideoElement(videoModel: ElementModel.VideoModel, dits: JsonArray?): ElementModel {
        val dataKey = videoModel.data.dataKey
        val defaultFile = if(videoModel.data.fileName.isNullOrEmpty()) null else {
            filesUtils.getVideoFromDirectory("/Dashboard",
                videoModel.data.fileName)
        }

        return if (!dataKey.isNullOrEmpty()) {
            val ditValue = getValueFromDit(dataKey, dits, defaultFile.orEmpty())
            videoModel.copy(data = videoModel.data.copy(
                folderPath = validateViaTItem(ditValue = ditValue, defaultIcon = defaultFile)))
        } else {
            if (!defaultFile.isNullOrEmpty())
                videoModel.copy(data = videoModel.data.copy(folderPath = defaultFile))
            else
                videoModel
        }
    }

    private suspend fun processTextElement(textModel: ElementModel.TextModel, lang: String, dits: JsonArray?): ElementModel {
        val dataKey = textModel.data.dataKey
        val defaultText = textModel.data.defaultText

        return if (!dataKey.isNullOrEmpty()) {
            val ditValue = getValueFromDit(dataKey, dits, defaultText)
            val newText = validateEspecialItems(textModel.data.dashboardItemId, ditValue, lang)
            textModel.copy(data = textModel.data.copy(defaultText = newText ?: defaultText)
            )
        } else {
            val translations = textModel.data.translations?.get(lang)
            textModel.copy(data = textModel.data.copy(defaultText = translations ?: defaultText))
        }
    }

    private fun processImageElement(imageModel: ElementModel.ImageModel, dits: JsonArray?): ElementModel {
        val dataKey = imageModel.data.dataKey
        val defaultFile = if(imageModel.data.fileName.isNullOrEmpty()) null else {
            filesUtils.getImageFromDirectory("/Dashboard",
                imageModel.data.fileName)
        }

        return if (!dataKey.isNullOrEmpty()) {
            val ditValue = getValueFromDit(dataKey, dits, defaultFile.orEmpty())
            imageModel.copy(data = imageModel.data.copy(
                folderPath = validateViaTItem(ditValue = ditValue, defaultIcon = defaultFile)))
        } else {
            if (!defaultFile.isNullOrEmpty())
                imageModel.copy(data = imageModel.data.copy(folderPath = defaultFile))
            else
                imageModel
        }
    }
    private fun getValueFromDit(dataKey: String, dits: JsonArray?, defaultValue: String): String {
        var dataResult = defaultValue
        dits?.forEach { dit ->
            val ditObj = dit.jsonObject

            if (ditObj[dataKey] != null && ditObj[dataKey]?.jsonNull != null){
                dataResult = ditObj[dataKey].toString()
            }
        }
        return dataResult
    }

    private fun validateViaTItem(ditValue: String, defaultIcon: String?): String?{
        appLogger.trackLog("VIAT-LOGO ITEM", Json.encodeToString(ditValue))
        return if(ditValue == "4"){
            defaultIcon ?: "0" //Validate to show the respective default icon in each platform
        } else null
    }

    private suspend fun validateEspecialItems(lineId: String, text: String?, lang: String): String? {
        return when(lineId){
            "entry-type-value" -> {
                appLogger.trackLog("CARD_CLASS ITEM", "$lineId: $text")
                val code = try {
                    text?.toInt() ?: 0
                }catch (e: Exception){
                    appLogger.trackError(e)
                    return null
                }
                when(val cardClassText = getCardClassTranslations.invoke(code, lang)){
                    is ServiceResult.Error -> text
                    is ServiceResult.Success -> cardClassText.data?.translation
                }
            }
            "license-plate-value" -> {
                appLogger.trackLog("LICENSE-PLATE", "$lineId: $text")
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
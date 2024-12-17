package com.cameparkare.dashboardapp.ui.utils

import android.os.Environment
import com.cameparkare.dashboardapp.R
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.domain.usecases.GetCardClassTranslations
import com.cameparkare.dashboardapp.ui.interfaces.FilesUtils
import com.cameparkare.dashboardapp.ui.interfaces.UiUtils
import com.cameparkare.dashboardapp.ui.models.UiElementModel
import com.google.gson.Gson
import com.google.gson.JsonArray

class UiUtils @Inject constructor(
    private val getCardClassTranslations: GetCardClassTranslations,
    private val appLogger: AppLogger,
    private val filesUtils: FilesUtils
): UiUtils {
    override suspend fun buildDashboardItem(
        items: List<UiElementModel>,
        dits: JsonArray?,
        lang: String
    ): List<UiElementModel> {
        val resultList: MutableList<UiElementModel> = mutableListOf()
        for (item in items){
            when(item){
                is UiElementModel.TextDto -> resultList.add(processTextElement(item, lang, dits))
                is UiElementModel.ImageDto -> resultList.add(processImageElement(item, dits))
                is UiElementModel.BoxDto -> {
                    resultList.add(item.copy(data = item.data.copy(content = buildDashboardItem(item.data.content, dits, lang))))
                }
                is UiElementModel.ColumnDto -> {
                    resultList.add(item.copy(data = item.data.copy(content = buildDashboardItem(item.data.content, dits, lang))))
                }
                is UiElementModel.RowDto -> {
                    resultList.add(item.copy(data = item.data.copy(content = buildDashboardItem(item.data.content, dits, lang))))
                }
                else -> resultList.add(item)
            }
        }
        return resultList
    }
    private suspend fun processTextElement(textDto: UiElementModel.TextDto, lang: String, dits: JsonArray?): UiElementModel {
        val dataKey = textDto.data.dataKey
        val defaultText = textDto.data.defaultText

        return if (!dataKey.isNullOrEmpty()) {
            val ditValue = getValueFromDit(dataKey, dits, defaultText)
            val newText = validateEspecialItems(textDto.data.dashboardItemId, ditValue, lang)
            textDto.copy(data = textDto.data.copy(defaultText = newText ?: defaultText)
            )
        } else {
            val translations = textDto.data.translations?.get(lang)
            textDto.copy(data = textDto.data.copy(defaultText = translations ?: defaultText))
        }
    }

    private fun processImageElement(imageDto: UiElementModel.ImageDto, dits: JsonArray?): UiElementModel {
        val dataKey = imageDto.data.dataKey
        val defaultFile = if(imageDto.data.fileName.isNullOrEmpty()) null else {
            filesUtils.getImageFromDirectory("${Environment.DIRECTORY_PICTURES}/Dashboard",
                imageDto.data.fileName)
        }

        return if (!dataKey.isNullOrEmpty()) {
            val ditValue = getValueFromDit(dataKey, dits, defaultFile.orEmpty())
            imageDto.copy(data = imageDto.data.copy(
                localFilePath = validateViaTItem(ditValue = ditValue, defaultIcon = defaultFile)))
        } else {
            if (!defaultFile.isNullOrEmpty())
                imageDto.copy(data = imageDto.data.copy(localFilePath = defaultFile))
            else
                imageDto
        }
    }
    private fun getValueFromDit(dataKey: String, dits: JsonArray?, defaultValue: String): String {
        var dataResult = defaultValue
        dits?.forEach { dit ->
            val ditObj = dit.asJsonObject

            if (ditObj != null){
                if (ditObj.get(dataKey) != null && !ditObj.get(dataKey).isJsonNull){
                    dataResult = ditObj.get(dataKey).asString
                }
            }
        }
        return dataResult
    }

    private fun validateViaTItem(ditValue: String, defaultIcon: String?): String?{
        appLogger.trackLog("VIAT-LOGO ITEM", Gson().toJson(ditValue))
        return if(ditValue == "4"){
            defaultIcon ?: "${R.drawable.viat_logo_retina}"
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
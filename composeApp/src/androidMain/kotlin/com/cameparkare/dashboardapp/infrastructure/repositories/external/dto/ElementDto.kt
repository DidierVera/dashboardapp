package com.cameparkare.dashboardapp.infrastructure.repositories.external.dto
import com.cameparkare.dashboardapp.domain.models.components.BoxDataModel
import com.cameparkare.dashboardapp.domain.models.components.CommonStyleModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements.BoxDataDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements.ColumnDataDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements.ImageDataDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements.RowDataDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements.SpacerDataDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements.TextDataDto
import com.cameparkare.dashboardapp.infrastructure.repositories.external.dto.elements.VideoDataDto
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable
@JsonClassDiscriminator("element-type")
sealed class ElementDto {
    abstract val elementType: String

    @Serializable
    @SerialName("box")
    data class BoxDto(
        override val elementType: String,
        val data: BoxDataDto
    ) : ElementDto()

    @Serializable
    @SerialName("spacer")
    data class SpacerDto(
        override val elementType: String,
        val data: SpacerDataDto
    ) : ElementDto()

    @Serializable
    @SerialName("column")
    data class ColumnDto(
        override val elementType: String,
        val data: ColumnDataDto
    ) : ElementDto()

    @Serializable
    @SerialName("text")
    data class TextDto(
        override val elementType: String,
        val data: TextDataDto
    ) : ElementDto()

    @Serializable
    @SerialName("row")
    data class RowDto(
        override val elementType: String,
        val data: RowDataDto
    ) : ElementDto()

    @Serializable
    @SerialName("image")
    data class ImageDto(
        override val elementType: String,
        val data: ImageDataDto
    ) : ElementDto()

    @Serializable
    @SerialName("video")
    data class VideoDto(
        override val elementType: String,
        val data: VideoDataDto
    ) : ElementDto()
}
object ElementSerializer : JsonContentPolymorphicSerializer<ElementDto>(ElementDto::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out ElementDto> {
        return when (val type = element.jsonObject["element-type"]?.jsonPrimitive?.content) {
            "box" -> ElementDto.BoxDto.serializer()
            "spacer" -> ElementDto.SpacerDto.serializer()
            "column" -> ElementDto.ColumnDto.serializer()
            "text" -> ElementDto.TextDto.serializer()
            "row" -> ElementDto.RowDto.serializer()
            "image" -> ElementDto.ImageDto.serializer()
            "video" -> ElementDto.VideoDto.serializer()
            else -> throw IllegalArgumentException("Unknown element type: $type")
        }
    }
}
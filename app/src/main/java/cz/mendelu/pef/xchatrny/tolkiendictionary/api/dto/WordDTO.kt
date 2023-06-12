package cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word
import java.util.UUID

@JsonClass(generateAdapter = true)
data class WordDTO(
    @Json(name = "id")
    var id: String,

    @Json(name = "czechMeaning")
    var czechMeaning: String,

    @Json(name = "translation")
    var translation: String,

    @Json(name = "tengwar")
    var tengwar: String?,

    @Json(name = "createdAt")
    var createdAt: Long,

    @Json(name = "languageId")
    var languageId: String,

    @Json(name = "sourceId")
    var sourceId: String?
) {
    fun toWord(): Word {
        return Word(
            id = UUID.fromString(id),
            czechMeaning = czechMeaning,
            translation = translation,
            tengwar = tengwar,
            addedByAdmin = true,
            creationDate = createdAt,
            idLanguage = UUID.fromString(languageId),
            idSource = UUID.fromString(sourceId)
        )
    }
}
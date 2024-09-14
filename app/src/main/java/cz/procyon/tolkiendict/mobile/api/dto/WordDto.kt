package cz.procyon.tolkiendict.mobile.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import cz.procyon.tolkiendict.mobile.model.Word
import java.time.LocalDateTime
import java.util.UUID

@JsonClass(generateAdapter = true)
data class WordDto(
    @Json(name = "id")
    var id: String,

    @Json(name = "czechMeaning")
    var czechMeaning: String,

    @Json(name = "translation")
    var translation: String,

    @Json(name = "tengwar")
    var tengwar: String?,

//    @Json(name = "createdAt")
//    var createdAt: Long,

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
            creationDate = LocalDateTime.now().second.toLong(),
            idLanguage = UUID.fromString(languageId),
            idSource = UUID.fromString(sourceId)
        )
    }
}
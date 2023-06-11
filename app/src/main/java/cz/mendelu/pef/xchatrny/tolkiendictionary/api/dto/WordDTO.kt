package cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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
    var createdAt: String,

    @Json(name = "languageId")
    var languageId: String,

    @Json(name = "sourceId")
    var sourceId: String?
)
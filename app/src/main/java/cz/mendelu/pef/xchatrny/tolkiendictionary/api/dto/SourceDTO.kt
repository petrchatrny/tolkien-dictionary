package cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SourceDTO(
    @Json(name = "id")
    var id: String,

    @Json(name = "name")
    var name: String,

    @Json(name = "url")
    var url: String,

    @Json(name = "createdAt")
    var createdAt: String
) {
}

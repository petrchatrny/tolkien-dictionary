package cz.procyon.tolkiendict.mobile.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import cz.procyon.tolkiendict.mobile.model.Language
import java.util.UUID

@JsonClass(generateAdapter = true)
data class LanguageDto(
    @Json(name = "id")
    var id: String,

    @Json(name = "name")
    var name: String,

    @Json(name = "icon")
    var description: String?,
) {

    fun toLanguage(): Language {
        return Language(id = UUID.fromString(id), name = name)
    }
}

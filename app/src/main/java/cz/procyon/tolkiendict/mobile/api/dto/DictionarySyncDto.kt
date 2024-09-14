package cz.procyon.tolkiendict.mobile.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DictionarySyncDto(

    @Json(name = "created")
    var created: DictionaryDtoEntities,

    @Json(name = "updated")
    var updated: DictionaryDtoEntities,

    @Json(name = "deleted")
    var deleted: DictionaryDtoReferences
)
package cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DictionarySyncDTO(
    @Json(name = "created")
    var created: DictionaryDTO,

    @Json(name = "updated")
    var updated: DictionaryDTO,

    @Json(name = "deleted")
    var deleted: DictionarySyncDeletedDTO
)
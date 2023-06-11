package cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DictionaryDTO(
    @Json(name = "words")
    var words: List<WordDTO>,

    @Json(name = "sources")
    var sources: List<SourceDTO>,

    @Json(name = "languages")
    var languages: List<LanguageDTO>,
)

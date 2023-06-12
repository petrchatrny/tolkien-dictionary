package cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto

import com.squareup.moshi.Json

data class DictionarySyncDeletedDTO (
    @Json(name="words")
    var words: List<String>,

    @Json(name="languages")
    var languages: List<String>,

    @Json(name="sources")
    var sources: List<String>
)
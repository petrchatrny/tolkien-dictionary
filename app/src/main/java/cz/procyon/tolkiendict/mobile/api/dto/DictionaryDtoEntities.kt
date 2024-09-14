package cz.procyon.tolkiendict.mobile.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DictionaryDtoEntities(

    @Json(name = "words")
    var words: List<WordDto>,

    @Json(name = "sources")
    var sources: List<SourceDto>,

    @Json(name = "languages")
    var languages: List<LanguageDto>,
)

package cz.procyon.tolkiendict.mobile.api.dto

import com.squareup.moshi.Json

data class DictionaryDtoReferences (
    @Json(name="words")
    var words: List<String>,

    @Json(name="languages")
    var languages: List<String>,

    @Json(name="sources")
    var sources: List<String>
)
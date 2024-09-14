package cz.procyon.tolkiendict.mobile.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import cz.procyon.tolkiendict.mobile.model.Source
import java.util.UUID

@JsonClass(generateAdapter = true)
data class SourceDto(
    @Json(name = "id")
    var id: String,

    @Json(name = "name")
    var name: String,

    @Json(name = "url")
    var url: String,
) {
    fun toSource(): Source {
        return Source(
            id = UUID.fromString(id),
            name = name,
            url = url
        )
    }
}

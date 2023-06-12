package cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Source
import java.util.UUID

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
    fun toSource(): Source {
        return Source(
            id = UUID.fromString(id),
            name = name,
            url = url
        )
    }
}

package cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Language
import java.util.UUID

@JsonClass(generateAdapter = true)
data class LanguageDTO(
    @Json(name = "id")
    var id: String,

    @Json(name = "name")
    var name: String,

    @Json(name = "icon")
    var icon: ByteArray?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LanguageDTO

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    fun toLanguage(): Language {
        return Language(id = UUID.fromString(id), name = name, icon = null)
    }
}

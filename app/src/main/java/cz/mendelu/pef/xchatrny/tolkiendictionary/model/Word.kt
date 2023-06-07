package cz.mendelu.pef.xchatrny.tolkiendictionary.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "words")
data class Word(
    @PrimaryKey
    @ColumnInfo("id_word")
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "czech_meaning")
    var czechMeaning: String,

    @ColumnInfo(name = "translation")
    var translation: String,

    @ColumnInfo(name = "tengwar")
    var tengwar: String? = null,

    @ColumnInfo(name = "added_by_admin")
    val addedByAdmin: Boolean = false,

    @ColumnInfo(name = "is_bookmarked")
    var isBookmarked: Boolean = false,

    @ColumnInfo(name = "creation_date")
    val creationDate: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "id_language")
    var idLanguage: UUID? = null,

    @ColumnInfo(name = "id_source")
    val idSource: UUID? = null
)

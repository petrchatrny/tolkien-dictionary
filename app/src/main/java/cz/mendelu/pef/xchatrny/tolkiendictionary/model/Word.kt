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
    val czechMeaning: String,

    @ColumnInfo(name = "translation")
    val translation: String,

    @ColumnInfo(name = "tengwar")
    val tengwar: String,

    @ColumnInfo(name = "added_by_admin")
    val addedByAdmin: Boolean,

    @ColumnInfo(name = "is_favourite")
    val isFavourite: Boolean,

    @ColumnInfo(name = "creation_date")
    val creationDate: Long,

    @ColumnInfo(name = "id_language")
    val idLanguage: UUID,

    @ColumnInfo(name = "id_source")
    val idSource: UUID? = null
)

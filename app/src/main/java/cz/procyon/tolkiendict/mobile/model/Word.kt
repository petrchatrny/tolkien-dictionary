package cz.procyon.tolkiendict.mobile.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "words")
data class Word(
    @PrimaryKey
    @ColumnInfo(name = "id_word")
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "czech_meaning", collate = ColumnInfo.NOCASE)
    var czechMeaning: String,

    @ColumnInfo(name = "translation", collate = ColumnInfo.NOCASE)
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
    var idSource: UUID? = null
)

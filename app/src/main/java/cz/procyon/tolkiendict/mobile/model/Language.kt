package cz.procyon.tolkiendict.mobile.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "languages")
data class Language(
    @PrimaryKey
    @ColumnInfo("id_language")
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "name")
    var name: String,
)

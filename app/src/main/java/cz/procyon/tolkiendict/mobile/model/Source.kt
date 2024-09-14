package cz.procyon.tolkiendict.mobile.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "sources")
data class Source(
    @PrimaryKey
    @ColumnInfo("id_source")
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "url")
    var url: String?,

    @ColumnInfo(name = "added_by_admin")
    val addedByAdmin: Boolean = false,
)
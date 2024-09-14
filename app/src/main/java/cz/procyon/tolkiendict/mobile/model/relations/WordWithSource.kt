package cz.procyon.tolkiendict.mobile.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import cz.procyon.tolkiendict.mobile.model.Source
import cz.procyon.tolkiendict.mobile.model.Word

data class WordWithSource(
    @Embedded
    val word: Word,
    @Relation(
        parentColumn = "id_source",
        entityColumn = "id_source"
    )
    val source: Source?
)
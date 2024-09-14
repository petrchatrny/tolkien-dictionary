package cz.procyon.tolkiendict.mobile.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import cz.procyon.tolkiendict.mobile.model.Language
import cz.procyon.tolkiendict.mobile.model.Source
import cz.procyon.tolkiendict.mobile.model.Word

data class WordWithLanguageAndSource(
    @Embedded
    val word: Word,
    @Relation(
        parentColumn = "id_language",
        entityColumn = "id_language"
    )
    val language: Language,
    @Relation(
        parentColumn = "id_source",
        entityColumn = "id_source"
    )
    val source: Source?
)
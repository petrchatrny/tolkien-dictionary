package cz.procyon.tolkiendict.mobile.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import cz.procyon.tolkiendict.mobile.model.Language
import cz.procyon.tolkiendict.mobile.model.Word

data class LanguageWithWords(
    @Embedded val language: Language,
    @Relation(
        parentColumn = "id_language",
        entityColumn = "id_language"
    )
    val words: List<Word>
)

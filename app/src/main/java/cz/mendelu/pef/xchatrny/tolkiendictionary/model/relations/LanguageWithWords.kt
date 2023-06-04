package cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Language
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word

data class LanguageWithWords(
    @Embedded val language: Language,
    @Relation(
        parentColumn = "id_language",
        entityColumn = "id_language"
    )
    val words: List<Word>
)

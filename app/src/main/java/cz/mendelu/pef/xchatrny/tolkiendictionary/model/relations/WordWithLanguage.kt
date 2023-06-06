package cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Language
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word

data class WordWithLanguage(
    @Embedded val word: Word,
    @Relation(
        parentColumn = "id_language",
        entityColumn = "id_language"
    )
    val language: Language
)
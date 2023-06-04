package cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Source
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word

data class WordWithSource(
    @Embedded
    val word: Word,
    @Relation(
        parentColumn = "id_source",
        entityColumn = "id_source"
    )
    val source: Source
)
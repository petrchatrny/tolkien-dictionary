package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words

import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations.WordWithSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface IWordsRepository {
    fun getAll(): Flow<List<Word>>

    fun getWordWithSource(id: UUID): Flow<WordWithSource>

    fun getFavouriteWords(): Flow<List<Word>>

    suspend fun insert(word: Word)

    suspend fun update(word: Word)

    suspend fun delete(word: Word)
}
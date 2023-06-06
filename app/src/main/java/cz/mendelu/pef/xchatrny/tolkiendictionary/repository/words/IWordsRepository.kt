package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words

import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Language
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations.WordWithLanguage
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations.WordWithSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface IWordsRepository {
    fun getAll(): Flow<List<Word>>

    fun getAll(query: String, language: Language, criteria: SearchCriteria): Flow<List<Word>>

    suspend fun getWordWithSourceById(id: UUID): WordWithSource

    fun getBookmarkedWords(): Flow<List<WordWithLanguage>>

    suspend fun insert(word: Word)

    suspend fun update(word: Word)

    suspend fun delete(word: Word)
}
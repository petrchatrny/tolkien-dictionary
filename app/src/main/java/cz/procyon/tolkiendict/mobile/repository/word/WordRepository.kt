package cz.procyon.tolkiendict.mobile.repository.word

import cz.procyon.tolkiendict.mobile.model.Language
import cz.procyon.tolkiendict.mobile.model.Word
import cz.procyon.tolkiendict.mobile.model.relations.WordWithLanguage
import cz.procyon.tolkiendict.mobile.model.relations.WordWithLanguageAndSource
import cz.procyon.tolkiendict.mobile.model.relations.WordWithSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface WordRepository {
    fun getAll(): Flow<List<Word>>

    fun getAll(query: String, language: Language, criteria: SearchCriteria): Flow<List<Word>>

    suspend fun getOne(id: UUID): Word?

    suspend fun getWordWithSourceById(id: UUID): WordWithSource?

    fun getWordWithLanguageAndSourceById(id: UUID): Flow<WordWithLanguageAndSource?>

    fun getBookmarkedWords(): Flow<List<WordWithLanguage>>

    suspend fun insert(word: Word)

    suspend fun update(word: Word)

    suspend fun delete(word: Word)

    suspend fun insertAll(words: List<Word>)

    suspend fun deleteDownloaded()

    suspend fun deleteMultiple(idList: List<UUID>)
}
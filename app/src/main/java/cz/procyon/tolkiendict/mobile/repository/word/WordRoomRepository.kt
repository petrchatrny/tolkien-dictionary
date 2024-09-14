package cz.procyon.tolkiendict.mobile.repository.word

import cz.procyon.tolkiendict.mobile.database.dao.WordDao
import cz.procyon.tolkiendict.mobile.model.Language
import cz.procyon.tolkiendict.mobile.model.Word
import cz.procyon.tolkiendict.mobile.model.relations.WordWithLanguage
import cz.procyon.tolkiendict.mobile.model.relations.WordWithLanguageAndSource
import cz.procyon.tolkiendict.mobile.model.relations.WordWithSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class WordRoomRepository(private val dao: WordDao) : WordRepository {
    override fun getAll(): Flow<List<Word>> {
        return dao.getAll()
    }

    override fun getAll(
        query: String,
        language: Language,
        criteria: SearchCriteria
    ): Flow<List<Word>> {
        return when (criteria) {
            SearchCriteria.CZECH_MEANING -> {
                dao.getAllByCzechMeaning(query, language.id)
            }

            SearchCriteria.TRANSLATION -> {
                dao.getAllByTranslation(query, language.id)
            }
        }
    }

    override suspend fun getOne(id: UUID): Word? {
        return dao.getOne(id)
    }

    override suspend fun getWordWithSourceById(id: UUID): WordWithSource? {
        return dao.getWordWithSourceById(id = id)
    }

    override fun getWordWithLanguageAndSourceById(id: UUID): Flow<WordWithLanguageAndSource?> {
        return dao.getWordWithLanguageAndSourceById(id = id)
    }

    override fun getBookmarkedWords(): Flow<List<WordWithLanguage>> {
        return dao.getBookmarkedWords()
    }

    override suspend fun insert(word: Word) {
        dao.insert(word = word)
    }

    override suspend fun update(word: Word) {
        dao.update(word = word)
    }

    override suspend fun delete(word: Word) {
        dao.delete(word = word)
    }

    override suspend fun insertAll(words: List<Word>) {
        dao.insertAll(words = words)
    }

    override suspend fun deleteDownloaded() {
        dao.deleteDownloaded()
    }

    override suspend fun deleteMultiple(idList: List<UUID>) {
        dao.deleteMultiple(idList = idList)
    }
}
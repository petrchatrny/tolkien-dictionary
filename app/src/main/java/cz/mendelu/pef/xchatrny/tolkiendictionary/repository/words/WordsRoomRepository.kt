package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words

import cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao.WordsDao
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Language
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations.WordWithLanguage
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations.WordWithLanguageAndSource
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations.WordWithSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class WordsRoomRepository(private val dao: WordsDao) : IWordsRepository {
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

    override suspend fun getWordWithSourceById(id: UUID): WordWithSource {
        return dao.getWordWithSourceById(id = id)
    }

    override fun getWordWithLanguageAndSourceById(id: UUID): Flow<WordWithLanguageAndSource> {
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
        dao.insertAll(words)
    }

    override suspend fun deleteDownloaded() {
        dao.deleteDownloaded()
    }
}
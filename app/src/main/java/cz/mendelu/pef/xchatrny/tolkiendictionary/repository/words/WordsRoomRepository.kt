package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words

import cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao.WordsDao
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations.WordWithSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class WordsRoomRepository(private val dao: WordsDao) : IWordsRepository {
    override fun getAll(): Flow<List<Word>> {
        return dao.getAll()
    }

    override fun getWordWithSource(id: UUID): Flow<WordWithSource> {
        return dao.getWordWithSource(id = id)
    }

    override fun getFavouriteWords(): Flow<List<Word>> {
        return dao.getFavouriteWords()
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
}
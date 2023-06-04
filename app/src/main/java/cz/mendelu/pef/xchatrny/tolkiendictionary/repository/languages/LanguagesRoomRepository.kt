package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.languages

import cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao.LanguagesDao
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Language
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations.LanguageWithWords
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class LanguagesRoomRepository(private val dao: LanguagesDao) : ILanguagesRepository {
    override fun getAll(): Flow<List<Language>> {
        return dao.getAll()
    }

    override fun getLanguageWithWords(id: UUID): Flow<List<LanguageWithWords>> {
        return dao.getLanguageWithWords(id = id)
    }

    override suspend fun insert(language: Language) {
        dao.insert(language = language)
    }

    override suspend fun update(language: Language) {
        dao.update(language = language)
    }

    override suspend fun delete(language: Language) {
        dao.delete(language = language)
    }
}
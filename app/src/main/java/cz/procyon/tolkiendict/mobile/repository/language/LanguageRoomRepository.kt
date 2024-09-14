package cz.procyon.tolkiendict.mobile.repository.language

import cz.procyon.tolkiendict.mobile.database.dao.LanguageDao
import cz.procyon.tolkiendict.mobile.model.Language
import cz.procyon.tolkiendict.mobile.model.relations.LanguageWithWords
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class LanguageRoomRepository(private val dao: LanguageDao) : LanguageRepository {
    override suspend fun getAll(): List<Language> {
        return dao.getAll()
    }

    override suspend fun getOne(id: UUID): Language? {
        return dao.getOne(id = id)
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

    override suspend fun insertAll(languages: List<Language>) {
        dao.insertAll(languages = languages)
    }

    override suspend fun deleteDownloaded() {
        dao.deleteDownloaded()
    }

    override suspend fun deleteMultiple(idList: List<UUID>) {
        dao.deleteMultiple(idList = idList)
    }
}
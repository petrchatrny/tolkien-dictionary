package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.dictionaries

import cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto.DictionaryDTO
import cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto.DictionarySyncDTO
import cz.mendelu.pef.xchatrny.tolkiendictionary.api.endpoints.DictionaryApi

class DictionaryTolkienApiRepository(
    private val api: DictionaryApi
) : IDictionaryRepository {

    override suspend fun downloadDictionary(): DictionaryDTO {
        return api.downloadDictionary()
    }

    override suspend fun syncDictionary(lastSyncDate: Long?): DictionarySyncDTO {
        return api.syncDictionary(lastSyncDate)
    }
}
package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.dictionaries

import cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto.DictionaryDTO
import cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto.DictionarySyncDTO

interface IDictionaryRepository {
    suspend fun downloadDictionary(): DictionaryDTO

    suspend fun syncDictionary(lastSyncDate: Long?): DictionarySyncDTO
}
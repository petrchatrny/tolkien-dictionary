package cz.procyon.tolkiendict.mobile.repository.dictionary

import cz.procyon.tolkiendict.mobile.api.dto.DictionaryDtoEntities
import cz.procyon.tolkiendict.mobile.api.dto.DictionarySyncDto

interface DictionaryRepository {
    suspend fun downloadDictionary(): DictionaryDtoEntities
    suspend fun syncDictionary(lastSyncDate: Long?): DictionarySyncDto
    suspend fun getLastSyncDateTime(): Long?
    suspend fun updateLastSyncDateTime()
}
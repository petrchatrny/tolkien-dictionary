package cz.procyon.tolkiendict.mobile.repository.dictionary

import android.content.Context
import androidx.datastore.preferences.core.edit
import cz.procyon.tolkiendict.mobile.datastore.store.SyncDataStore
import cz.procyon.tolkiendict.mobile.datastore.dataStore
import cz.procyon.tolkiendict.mobile.util.DateUtils
import cz.procyon.tolkiendict.mobile.api.dto.DictionaryDtoEntities
import cz.procyon.tolkiendict.mobile.api.dto.DictionarySyncDto
import cz.procyon.tolkiendict.mobile.api.endpoint.DictionaryApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first

class DictionaryRestRepository(
    private val api: DictionaryApi,
    private val context: Context
) : DictionaryRepository {

    override suspend fun downloadDictionary(): DictionaryDtoEntities {
        return api.downloadDictionary()
    }

    override suspend fun syncDictionary(lastSyncDate: Long?): DictionarySyncDto {
        return api.syncDictionary(lastSyncDate)
    }

    override suspend fun getLastSyncDateTime(): Long? {
        val key = SyncDataStore.LAST_DICTIONARY_SYNC_KEY

        return context.dataStore.data
            .catch { throw it }
            .first()[key]
    }

    override suspend fun updateLastSyncDateTime() {
        val key = SyncDataStore.LAST_DICTIONARY_SYNC_KEY
        context.dataStore.edit { prefs ->
            prefs[key] = DateUtils.getCurrentUnixTime()
        }
    }
}
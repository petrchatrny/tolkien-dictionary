package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.session

import android.content.Context
import androidx.datastore.preferences.core.edit
import cz.mendelu.pef.xchatrny.tolkiendictionary.datastore.SessionDataStore
import cz.mendelu.pef.xchatrny.tolkiendictionary.datastore.dataStore
import cz.mendelu.pef.xchatrny.tolkiendictionary.util.DateUtils
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first

class SessionDataStoreRepository(private val context: Context) : ISessionRepository {

    override suspend fun getLastSyncDateTime(): Long? {
        val key = SessionDataStore.LAST_SYNC_KEY

        return context.dataStore.data
            .catch { throw it }
            .first()[key]
    }

    override suspend fun updateLastSyncDateTime() {
        val key = SessionDataStore.LAST_SYNC_KEY
        context.dataStore.edit { prefs ->
            prefs[key] = DateUtils.getCurrentUnixTime()
        }
    }

}
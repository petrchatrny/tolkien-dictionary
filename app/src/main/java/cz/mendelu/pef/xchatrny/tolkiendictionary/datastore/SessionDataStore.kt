package cz.mendelu.pef.xchatrny.tolkiendictionary.datastore

import androidx.datastore.preferences.core.longPreferencesKey

sealed class SessionDataStore {
    companion object {
        val LAST_SYNC_KEY = longPreferencesKey("last_sync")
    }
}
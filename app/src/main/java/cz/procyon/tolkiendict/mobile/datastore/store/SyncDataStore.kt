package cz.procyon.tolkiendict.mobile.datastore.store

import androidx.datastore.preferences.core.longPreferencesKey

sealed class SyncDataStore {
    companion object {
        val LAST_DICTIONARY_SYNC_KEY = longPreferencesKey("last_dictionary_sync")
    }
}
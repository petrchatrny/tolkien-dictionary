package cz.mendelu.pef.xchatrny.tolkiendictionary.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

sealed class SettingsDataStore {
    companion object {
        val IS_AUTO_UPDATE_ON_KEY = booleanPreferencesKey("is_auto_update_on")
        val TENGWAR_FONT_KEY = stringPreferencesKey("tengwar_font")
        val FONT_SIZE_KEY = stringPreferencesKey("font_size")
        val IS_IN_DARK_MODE_KEY = booleanPreferencesKey("is_in_dark_mode")
    }
}
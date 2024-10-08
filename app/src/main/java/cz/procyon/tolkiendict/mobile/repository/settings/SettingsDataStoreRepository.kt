package cz.procyon.tolkiendict.mobile.repository.settings

import android.content.Context
import androidx.datastore.preferences.core.edit
import cz.procyon.tolkiendict.mobile.datastore.store.SettingsDataStore
import cz.procyon.tolkiendict.mobile.datastore.dataStore
import cz.procyon.tolkiendict.mobile.datastore.entity.FontSize
import cz.procyon.tolkiendict.mobile.datastore.entity.TengwarFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class SettingsDataStoreRepository(private val context: Context) : SettingsRepository {

    override fun getIsAutoUpdateOn(): Flow<Boolean> {
        val key = SettingsDataStore.IS_AUTO_UPDATE_ON_KEY

        return context.dataStore.data
            .catch { throw it }
            .map { prefs ->
                val isAutoUpdateOn = prefs[key] ?: true
                isAutoUpdateOn
            }
    }

    override suspend fun setIsAutoUpdateOn(isAutoUpdateOn: Boolean) {
        val key = SettingsDataStore.IS_AUTO_UPDATE_ON_KEY
        context.dataStore.edit { prefs ->
            prefs[key] = isAutoUpdateOn
        }
    }

    override fun getTengwarFont(): Flow<TengwarFontFamily> {
        val key = SettingsDataStore.TENGWAR_FONT_KEY

        return context.dataStore.data
            .catch { throw it }
            .map { prefs ->
                val fontFamily =
                    TengwarFontFamily.valueOf(prefs[key] ?: TengwarFontFamily.ANNATAR.name)
                fontFamily
            }
    }

    override suspend fun setTengwarFont(tengwarFont: TengwarFontFamily) {
        val key = SettingsDataStore.TENGWAR_FONT_KEY
        context.dataStore.edit { prefs ->
            prefs[key] = tengwarFont.name
        }
    }

    override fun getFontSize(): Flow<FontSize> {
        val key = SettingsDataStore.FONT_SIZE_KEY

        return context.dataStore.data
            .catch { throw it }
            .map { prefs ->
                val fontSize = FontSize.valueOf(prefs[key] ?: FontSize.SMALL.name)
                fontSize
            }
    }

    override suspend fun setFontSize(fontSize: FontSize) {
        val key = SettingsDataStore.FONT_SIZE_KEY
        context.dataStore.edit { prefs ->
            prefs[key] = fontSize.name
        }
    }

    override fun getIsInDarkMode(): Flow<Boolean> {
        val key = SettingsDataStore.IS_IN_DARK_MODE_KEY

        return context.dataStore.data
            .catch { throw it }
            .map { prefs ->
                val isInDarkMode = prefs[key] ?: true
                isInDarkMode
            }
    }

    override suspend fun setIsInDarkMode(isInDarkMode: Boolean) {
        val key = SettingsDataStore.IS_IN_DARK_MODE_KEY
        context.dataStore.edit { prefs ->
            prefs[key] = isInDarkMode
        }
    }
}
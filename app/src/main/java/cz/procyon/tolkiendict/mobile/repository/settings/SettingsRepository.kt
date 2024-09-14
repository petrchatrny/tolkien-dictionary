package cz.procyon.tolkiendict.mobile.repository.settings

import cz.procyon.tolkiendict.mobile.datastore.entity.FontSize
import cz.procyon.tolkiendict.mobile.datastore.entity.TengwarFontFamily
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getIsAutoUpdateOn(): Flow<Boolean>
    suspend fun setIsAutoUpdateOn(isAutoUpdateOn: Boolean)

    fun getTengwarFont(): Flow<TengwarFontFamily>
    suspend fun setTengwarFont(tengwarFont: TengwarFontFamily)

    fun getFontSize(): Flow<FontSize>
    suspend fun setFontSize(fontSize: FontSize)

    fun getIsInDarkMode(): Flow<Boolean>
    suspend fun setIsInDarkMode(isInDarkMode: Boolean)
}
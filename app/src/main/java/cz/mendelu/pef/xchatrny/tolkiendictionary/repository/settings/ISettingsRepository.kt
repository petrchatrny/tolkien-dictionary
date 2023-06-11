package cz.mendelu.pef.xchatrny.tolkiendictionary.repository.settings

import cz.mendelu.pef.xchatrny.tolkiendictionary.datastore.entities.FontSize
import cz.mendelu.pef.xchatrny.tolkiendictionary.datastore.entities.TengwarFontFamily
import kotlinx.coroutines.flow.Flow

interface ISettingsRepository {
    fun getIsAutoUpdateOn(): Flow<Boolean>
    suspend fun setIsAutoUpdateOn(isAutoUpdateOn: Boolean)

    fun getTengwarFont(): Flow<TengwarFontFamily>
    suspend fun setTengwarFont(tengwarFont: TengwarFontFamily)

    fun getFontSize(): Flow<FontSize>
    suspend fun setFontSize(fontSize: FontSize)

    fun getIsInDarkMode(): Flow<Boolean>
    suspend fun setIsInDarkMode(isInDarkMode: Boolean)
}
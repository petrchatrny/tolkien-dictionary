package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.settings

import cz.mendelu.pef.xchatrny.tolkiendictionary.datastore.entities.FontSize
import cz.mendelu.pef.xchatrny.tolkiendictionary.datastore.entities.TengwarFontFamily

interface SettingsActions {
    fun onIsAutoUpdateChanged(autoUpdate: Boolean)
    fun onTengwarFontFamilyChanged(tengwarFontFamily: TengwarFontFamily)
    fun onFontSizeChanged(fontSize: FontSize)
    fun onIsInDarkModeChanged(isInDarkMode: Boolean)
}
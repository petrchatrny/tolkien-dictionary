package cz.procyon.tolkiendict.mobile.ui.screen.settings

import cz.procyon.tolkiendict.mobile.datastore.entity.FontSize
import cz.procyon.tolkiendict.mobile.datastore.entity.TengwarFontFamily

interface SettingsActions {
    fun onIsAutoUpdateChanged(autoUpdate: Boolean)
    fun onTengwarFontFamilyChanged(tengwarFontFamily: TengwarFontFamily)
    fun onFontSizeChanged(fontSize: FontSize)
    fun onIsInDarkModeChanged(isInDarkMode: Boolean)
}
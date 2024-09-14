package cz.procyon.tolkiendict.mobile.ui.screen.settings

import cz.procyon.tolkiendict.mobile.datastore.entity.FontSize
import cz.procyon.tolkiendict.mobile.datastore.entity.TengwarFontFamily

class SettingsData {
    var tengwarFontFamilyList = TengwarFontFamily.values()
    var fontSizeList = FontSize.values()

    var isAutoUpdateOn = false
    var tengwarFontFamily = TengwarFontFamily.ANNATAR
    var fontSize: FontSize = FontSize.MIDDLE
    var isInDarkMode = true
}
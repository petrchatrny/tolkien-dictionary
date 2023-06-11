package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.settings

import cz.mendelu.pef.xchatrny.tolkiendictionary.datastore.entities.FontSize
import cz.mendelu.pef.xchatrny.tolkiendictionary.datastore.entities.TengwarFontFamily

class SettingsData {
    var tengwarFontFamilyList = TengwarFontFamily.values()
    var fontSizeList = FontSize.values()

    var isAutoUpdateOn = false
    var tengwarFontFamily = TengwarFontFamily.ANNATAR
    var fontSize: FontSize = FontSize.MIDDLE
    var isInDarkMode = true
}
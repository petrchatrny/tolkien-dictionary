package cz.mendelu.pef.xchatrny.tolkiendictionary.datastore.entities

import androidx.compose.ui.text.font.FontFamily
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.theme.annatarFont
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.theme.eldamarFont
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.theme.parmaiteFont

enum class TengwarFontFamily(val fontFamilyName: String, val fontFamily: FontFamily) {
    ANNATAR("Annatar", annatarFont),
    ELDAMAR("Eldamar", eldamarFont),
    PARMAITE("Parmaite", parmaiteFont)
}
package cz.procyon.tolkiendict.mobile.datastore.entity

import androidx.compose.ui.text.font.FontFamily
import cz.procyon.tolkiendict.mobile.ui.theme.annatarFont
import cz.procyon.tolkiendict.mobile.ui.theme.eldamarFont
import cz.procyon.tolkiendict.mobile.ui.theme.parmaiteFont

enum class TengwarFontFamily(val fontFamilyName: String, val fontFamily: FontFamily) {
    ANNATAR("Annatar", annatarFont),
    ELDAMAR("Eldamar", eldamarFont),
    PARMAITE("Parmaite", parmaiteFont)
}
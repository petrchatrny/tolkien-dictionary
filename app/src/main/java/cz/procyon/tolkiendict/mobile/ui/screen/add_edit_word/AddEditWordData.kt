package cz.procyon.tolkiendict.mobile.ui.screen.add_edit_word

import cz.procyon.tolkiendict.mobile.model.Language
import cz.procyon.tolkiendict.mobile.model.Source
import cz.procyon.tolkiendict.mobile.model.Word
import cz.procyon.tolkiendict.mobile.ui.component.fields.SelectFieldItem

class AddEditWordData {
    var word: Word = Word(
        czechMeaning = "",
        translation = "",
        tengwar = "",
    )

    var selectableSources: MutableList<SelectFieldItem<Source>> = mutableListOf()
    var selectedSource: SelectFieldItem<Source>? = null

    var selectableLanguages: List<SelectFieldItem<Language>> = listOf()
    var selectedLanguage: SelectFieldItem<Language>? = null

    var doTranscription: Boolean = true

    var errorCzechMeaning: Int? = null
    var errorTranslation: Int? = null
    var errorLanguage: Int? = null
}
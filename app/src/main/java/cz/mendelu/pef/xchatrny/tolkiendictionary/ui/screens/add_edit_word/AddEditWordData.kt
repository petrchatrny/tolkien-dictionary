package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.add_edit_word

import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Language
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Source
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.fields.SelectFieldItem

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
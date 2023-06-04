package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.add_edit_word

import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Source
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.fields.SelectFieldItem
import java.util.UUID

class AddEditWordData {
    var word: Word = Word(
        czechMeaning = "",
        translation = "",
        tengwar = "",
    )
    var source: Source? = null

    var selectableLanguages: List<SelectFieldItem<UUID>> = listOf()
    var selectedLanguage: SelectFieldItem<UUID>? = null

    var errorCzechMeaning: Int? = null
    var errorTranslation: Int? = null
    var errorLanguage: Int? = null
}
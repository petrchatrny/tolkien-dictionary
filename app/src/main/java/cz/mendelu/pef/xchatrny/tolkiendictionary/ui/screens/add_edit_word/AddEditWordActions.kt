package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.add_edit_word

import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Source
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.fields.SelectFieldItem
import java.util.UUID

interface AddEditWordActions {
    fun onWordChange(word: Word)
    fun onSourceChange(source: Source?)
    fun onLanguageChange(language: SelectFieldItem<UUID>?)
    fun saveWord(update: Boolean)
    fun deleteWord()
}
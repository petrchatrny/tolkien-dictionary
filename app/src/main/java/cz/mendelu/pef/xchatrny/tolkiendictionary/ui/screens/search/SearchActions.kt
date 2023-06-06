package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.search

import cz.mendelu.pef.xchatrny.tolkiendictionary.model.DictionaryType
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word

interface SearchActions {
    fun search()
    fun toggleWordBookmark(word: Word)
    fun onSearchQueryChange(newQuery: String)
    fun onSelectedDictionaryTypeChange(newDictionaryType: DictionaryType)
}
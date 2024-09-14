package cz.procyon.tolkiendict.mobile.ui.screen.search

import cz.procyon.tolkiendict.mobile.model.DictionaryType
import cz.procyon.tolkiendict.mobile.model.Word

interface SearchActions {
    fun search()
    fun toggleWordBookmark(word: Word)
    fun onSearchQueryChange(newQuery: String)
    fun onSelectedDictionaryTypeChange(newDictionaryType: DictionaryType)
    fun getWordCountLabel(count: Int): Int
}
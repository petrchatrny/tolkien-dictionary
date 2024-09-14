package cz.procyon.tolkiendict.mobile.ui.screen.word_search

import cz.procyon.tolkiendict.mobile.datastore.entity.FontSize
import cz.procyon.tolkiendict.mobile.model.DictionaryType

class WordSearchData {
    var searchQuery: String = ""
    var wordsCount: Int = 0
    var dictionaryTypes: MutableList<DictionaryType> = mutableListOf()
    var selectedDictionaryType: DictionaryType? = null
    var fontSize: FontSize = FontSize.SMALL
}
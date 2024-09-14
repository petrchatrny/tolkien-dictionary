package cz.procyon.tolkiendict.mobile.ui.screen.search

import cz.procyon.tolkiendict.mobile.datastore.entity.FontSize
import cz.procyon.tolkiendict.mobile.model.DictionaryType

class SearchData {
    var searchQuery: String = ""
    var wordsCount: Int = 0
    var dictionaryTypes: MutableList<DictionaryType> = mutableListOf()
    var selectedDictionaryType: DictionaryType? = null
    var fontSize: FontSize = FontSize.SMALL
}
package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.search

import cz.mendelu.pef.xchatrny.tolkiendictionary.datastore.entities.FontSize
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.DictionaryType

class SearchData {
    var searchQuery: String = ""
    var wordsCount: Int = 0
    var dictionaryTypes: MutableList<DictionaryType> = mutableListOf()
    var selectedDictionaryType: DictionaryType? = null
    var fontSize: FontSize = FontSize.SMALL
}
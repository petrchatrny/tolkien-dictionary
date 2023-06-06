package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.search

import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word

sealed class SearchUIState {
    object Init : SearchUIState()
    object Default : SearchUIState()
    class Success(val words: List<Word>) : SearchUIState()
    object Loading : SearchUIState()
    object QueryChange : SearchUIState()
    object SelectedDictionaryTypeChange : SearchUIState()
}


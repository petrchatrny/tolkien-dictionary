package cz.procyon.tolkiendict.mobile.ui.screen.search

import cz.procyon.tolkiendict.mobile.model.Word

sealed class SearchUIState {
    object Init : SearchUIState()
    object Default : SearchUIState()
    class Success(val words: List<Word>) : SearchUIState()
    object Loading : SearchUIState()
    object QueryChange : SearchUIState()
    object SelectedDictionaryTypeChange : SearchUIState()
}


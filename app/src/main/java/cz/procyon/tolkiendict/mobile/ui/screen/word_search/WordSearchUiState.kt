package cz.procyon.tolkiendict.mobile.ui.screen.word_search

import cz.procyon.tolkiendict.mobile.model.Word

sealed class WordSearchUiState {
    object Init : WordSearchUiState()
    object Default : WordSearchUiState()
    class Success(val words: List<Word>) : WordSearchUiState()
    object Loading : WordSearchUiState()
    object QueryChange : WordSearchUiState()
    object SelectedDictionaryTypeChange : WordSearchUiState()
}


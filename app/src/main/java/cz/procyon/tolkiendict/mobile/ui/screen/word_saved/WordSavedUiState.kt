package cz.procyon.tolkiendict.mobile.ui.screen.word_saved

import cz.procyon.tolkiendict.mobile.model.relations.WordWithLanguage

sealed class WordSavedUiState {
    object Default : WordSavedUiState()
    class Success(val words: List<WordWithLanguage>) : WordSavedUiState()
}
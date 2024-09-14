package cz.procyon.tolkiendict.mobile.ui.screen.word_form

sealed class WordFormUiState {
    object Loading: WordFormUiState()
    object Default: WordFormUiState()
    object Saving: WordFormUiState()

    object DataChanged: WordFormUiState()
    object ValidationError: WordFormUiState()

    object WordCreated: WordFormUiState()
    object WordUpdated: WordFormUiState()
    object WordDeleted: WordFormUiState()
}
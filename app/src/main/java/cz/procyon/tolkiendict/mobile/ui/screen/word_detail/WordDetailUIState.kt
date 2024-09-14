package cz.procyon.tolkiendict.mobile.ui.screen.word_detail

import cz.procyon.tolkiendict.mobile.model.relations.WordWithLanguageAndSource

sealed class WordDetailUIState {
    object Default: WordDetailUIState()
    object Loading: WordDetailUIState()
    class Success(val word: WordWithLanguageAndSource?): WordDetailUIState()
}
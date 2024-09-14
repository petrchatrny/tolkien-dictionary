package cz.procyon.tolkiendict.mobile.ui.screen.saved_words

import cz.procyon.tolkiendict.mobile.model.relations.WordWithLanguage

sealed class SavedWordsUIState {
    object Default : SavedWordsUIState()
    class Success(val words: List<WordWithLanguage>) : SavedWordsUIState()
}
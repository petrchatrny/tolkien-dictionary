package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.saved_words

import cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations.WordWithLanguage

sealed class SavedWordsUIState {
    object Default : SavedWordsUIState()
    class Success(val words: List<WordWithLanguage>) : SavedWordsUIState()
}
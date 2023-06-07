package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.word_detail

import cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations.WordWithLanguageAndSource

sealed class WordDetailUIState {
    object Default: WordDetailUIState()
    object Loading: WordDetailUIState()
    class Success(val word: WordWithLanguageAndSource): WordDetailUIState()
}
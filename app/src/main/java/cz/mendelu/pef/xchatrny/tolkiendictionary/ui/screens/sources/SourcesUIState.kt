package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.sources

import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Source

sealed class SourcesUIState {
    object Default : SourcesUIState()
    class Success(val adminSources: List<Source>, val userSources: List<Source>) : SourcesUIState()
}
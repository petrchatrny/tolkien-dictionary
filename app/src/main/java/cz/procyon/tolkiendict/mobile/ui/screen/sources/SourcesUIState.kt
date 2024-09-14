package cz.procyon.tolkiendict.mobile.ui.screen.sources

import cz.procyon.tolkiendict.mobile.model.Source

sealed class SourcesUIState {
    object Default : SourcesUIState()
    class Success(val adminSources: List<Source>, val userSources: List<Source>) : SourcesUIState()
}
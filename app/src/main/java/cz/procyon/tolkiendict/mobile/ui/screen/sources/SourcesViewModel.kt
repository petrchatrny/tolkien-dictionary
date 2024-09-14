package cz.procyon.tolkiendict.mobile.ui.screen.sources

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.procyon.tolkiendict.mobile.architecture.BaseViewModel
import cz.procyon.tolkiendict.mobile.repository.source.SourceRepository
import kotlinx.coroutines.launch

class SourcesViewModel(repository: SourceRepository) : BaseViewModel() {
    var uiState by mutableStateOf<SourcesUIState>(SourcesUIState.Default)

    init {
        launch {
            repository.getAll().collect { sources ->
                uiState = SourcesUIState.Success(
                    adminSources = sources.filter { it.addedByAdmin },
                    userSources = sources.filter { !it.addedByAdmin }
                )
            }
        }
    }
}
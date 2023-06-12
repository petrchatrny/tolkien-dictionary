package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.sources

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.mendelu.pef.xchatrny.tolkiendictionary.architecture.BaseViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.sources.ISourcesRepository
import kotlinx.coroutines.launch

class SourcesViewModel(repository: ISourcesRepository) : BaseViewModel() {
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
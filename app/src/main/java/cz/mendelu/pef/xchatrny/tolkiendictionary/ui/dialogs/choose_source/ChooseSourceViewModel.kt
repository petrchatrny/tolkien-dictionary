package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.dialogs.choose_source

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.mendelu.pef.xchatrny.tolkiendictionary.architecture.BaseViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.sources.ISourcesRepository
import kotlinx.coroutines.launch

class ChooseSourceViewModel(
    private val repository: ISourcesRepository
) : BaseViewModel(), ChooseSourceActions {
    var uiState by mutableStateOf<ChooseSourceUIState>(ChooseSourceUIState.Default)
    var data = ChooseSourceData()

    override fun onDataChange(data: ChooseSourceData) {
        this.data = data
        uiState = ChooseSourceUIState.DataChanged
    }

    override fun saveNewSource() {
        launch {
            repository.insert(data.newSource)
            uiState = ChooseSourceUIState.SourceSaved(data.newSource)
        }
    }
}
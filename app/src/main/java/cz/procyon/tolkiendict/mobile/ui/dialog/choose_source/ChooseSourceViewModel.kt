package cz.procyon.tolkiendict.mobile.ui.dialog.choose_source

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.procyon.tolkiendict.mobile.architecture.BaseViewModel
import cz.procyon.tolkiendict.mobile.repository.source.SourceRepository
import kotlinx.coroutines.launch

class ChooseSourceViewModel(
    private val repository: SourceRepository
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
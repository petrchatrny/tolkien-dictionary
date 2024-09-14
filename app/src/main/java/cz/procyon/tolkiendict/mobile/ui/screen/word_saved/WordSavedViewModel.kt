package cz.procyon.tolkiendict.mobile.ui.screen.word_saved

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.procyon.tolkiendict.mobile.architecture.BaseViewModel
import cz.procyon.tolkiendict.mobile.repository.word.WordRepository
import kotlinx.coroutines.launch

class WordSavedViewModel(
    private val repository: WordRepository
) : BaseViewModel() {
    var uiState by mutableStateOf<WordSavedUiState>(WordSavedUiState.Default)

    fun loadWords() {
        launch {
            repository.getBookmarkedWords().collect {
                uiState = WordSavedUiState.Success(it)
            }
        }
    }
}
package cz.procyon.tolkiendict.mobile.ui.screen.saved_words

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.procyon.tolkiendict.mobile.architecture.BaseViewModel
import cz.procyon.tolkiendict.mobile.repository.word.WordRepository
import kotlinx.coroutines.launch

class SavedWordsViewModel(
    private val repository: WordRepository
) : BaseViewModel() {
    var uiState by mutableStateOf<SavedWordsUIState>(SavedWordsUIState.Default)

    fun loadWords() {
        launch {
            repository.getBookmarkedWords().collect {
                uiState = SavedWordsUIState.Success(it)
            }
        }
    }
}
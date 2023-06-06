package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.saved_words

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.mendelu.pef.xchatrny.tolkiendictionary.architecture.BaseViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words.IWordsRepository
import kotlinx.coroutines.launch

class SavedWordsViewModel(
    private val repository: IWordsRepository
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
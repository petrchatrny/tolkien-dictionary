package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.word_detail

import android.content.ClipData
import android.content.ClipboardManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat.getSystemService
import cz.mendelu.pef.xchatrny.tolkiendictionary.TolkienDictionaryApp
import cz.mendelu.pef.xchatrny.tolkiendictionary.architecture.BaseViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations.WordWithLanguageAndSource
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words.IWordsRepository
import kotlinx.coroutines.launch
import java.util.UUID

class WordDetailViewModel(
    private val repository: IWordsRepository,
) : BaseViewModel(), WordDetailActions {
    var wordId: UUID? = null
    private var word: WordWithLanguageAndSource? = null

    var uiState by mutableStateOf<WordDetailUIState>(WordDetailUIState.Loading)

    fun initData() {
        wordId?.let {
            launch {
                repository.getWordWithLanguageAndSourceById(it).collect {
                    word = it
                    uiState = WordDetailUIState.Success(it)
                }
            }
        }
    }

    override fun copyToClipBoard() {
        val clipboard = getSystemService(
            TolkienDictionaryApp.appContext,
            ClipboardManager::class.java
        ) as ClipboardManager

        val clip: ClipData = ClipData.newPlainText("", word?.word?.translation)
        clipboard.setPrimaryClip(clip)
    }

    override fun textToSpeech() {
        // TODO problem of future Petr
    }

    override fun toggleBookmark() {
        word?.word?.let {
            it.isBookmarked = !it.isBookmarked
            launch { repository.update(it) }
        }
    }
}
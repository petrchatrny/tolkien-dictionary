package cz.procyon.tolkiendict.mobile.ui.screen.word_detail

import android.content.ClipData
import android.content.ClipboardManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat.getSystemService
import cz.procyon.tolkiendict.mobile.TolkienDictionaryApp
import cz.procyon.tolkiendict.mobile.architecture.BaseViewModel
import cz.procyon.tolkiendict.mobile.model.relations.WordWithLanguageAndSource
import cz.procyon.tolkiendict.mobile.repository.word.WordRepository
import kotlinx.coroutines.launch
import java.util.UUID

class WordDetailViewModel(
    private val repository: WordRepository,
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
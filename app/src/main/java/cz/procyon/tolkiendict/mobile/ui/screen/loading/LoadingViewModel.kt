package cz.procyon.tolkiendict.mobile.ui.screen.loading

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.procyon.tolkiendict.mobile.R
import cz.procyon.tolkiendict.mobile.api.dto.DictionaryDtoEntities
import cz.procyon.tolkiendict.mobile.architecture.BaseViewModel
import cz.procyon.tolkiendict.mobile.model.Language
import cz.procyon.tolkiendict.mobile.model.Source
import cz.procyon.tolkiendict.mobile.model.Word
import cz.procyon.tolkiendict.mobile.repository.dictionary.DictionaryRepository
import cz.procyon.tolkiendict.mobile.repository.language.LanguageRepository
import cz.procyon.tolkiendict.mobile.repository.source.SourceRepository
import cz.procyon.tolkiendict.mobile.repository.word.WordRepository
import kotlinx.coroutines.launch
import java.util.UUID

class LoadingViewModel(
    private val dictionaryRepository: DictionaryRepository,
    private val languageRepository: LanguageRepository,
    private val wordRepository: WordRepository,
    private val sourceRepository: SourceRepository,
) : BaseViewModel(), LoadingActions {
    var uiState by mutableStateOf<LoadingUIState>(LoadingUIState.Loading)
    var data = LoadingData()

    override fun tryToDownloadDictionaries() {
        launch {
            val lastSync = dictionaryRepository.getLastSyncDateTime()

            lastSync?.let {
                uiState = LoadingUIState.Downloaded
            } ?: run {
                downloadDictionary()
            }
        }
    }

    private fun downloadDictionary() {
        launch {
            // download new words
            val dictionary: DictionaryDtoEntities?

            try {
                dictionary = dictionaryRepository.downloadDictionary()
            } catch (e: Exception) {
                data.isError = true
                data.description = R.string.dictionaries_download_failed_check_connection
                uiState = LoadingUIState.NetworkError
                return@launch
            }

            // nullify errors
            data.isError = false
            data.description = R.string.downloading_dictionaries
            uiState = LoadingUIState.Downloading

            // drop old data if there are any
            wordRepository.deleteDownloaded()
            languageRepository.deleteDownloaded()
            sourceRepository.deleteDownloaded()

            // insert languages
            val languages = mutableListOf<Language>()
            dictionary.languages.forEach {
                languages.add(Language(UUID.fromString(it.id), it.name))
            }
            languageRepository.insertAll(languages)

            // insert sources
            val sources = mutableListOf<Source>()
            dictionary.sources.forEach {
                sources.add(Source(UUID.fromString(it.id), it.name, it.url, true))
            }
            sourceRepository.insertAll(sources)

            // insert words
            val words = mutableListOf<Word>()
            dictionary.words.forEach { wordDto ->
                words.add(wordDto.toWord())
            }
            wordRepository.insertAll(words)

            // update session
            dictionaryRepository.updateLastSyncDateTime()

            uiState = LoadingUIState.Downloaded
        }
    }
}
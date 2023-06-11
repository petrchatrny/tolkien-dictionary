package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.loading

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.mendelu.pef.xchatrny.tolkiendictionary.R
import cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto.DictionaryDTO
import cz.mendelu.pef.xchatrny.tolkiendictionary.architecture.BaseViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Language
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Source
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.dictionaries.IDictionaryRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.languages.ILanguagesRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.session.ISessionRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.sources.ISourcesRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words.IWordsRepository
import kotlinx.coroutines.launch
import java.util.UUID


class LoadingViewModel(
    private val dictionaryRepository: IDictionaryRepository,
    private val languageRepository: ILanguagesRepository,
    private val wordRepository: IWordsRepository,
    private val sourceRepository: ISourcesRepository,
    private val sessionRepository: ISessionRepository
) : BaseViewModel(), LoadingActions {
    var uiState by mutableStateOf<LoadingUIState>(LoadingUIState.Loading)
    var data = LoadingData()

    override fun tryToDownloadDictionaries() {
        launch {
            val lastSync = sessionRepository.getLastSyncDateTime()

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
            val dictionary: DictionaryDTO?

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
                languages.add(Language(UUID.fromString(it.id), it.name, null))
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
            dictionary.words.forEach {
                words.add(
                    Word(
                        id = UUID.fromString(it.id),
                        czechMeaning = it.czechMeaning,
                        translation = it.translation,
                        tengwar = it.tengwar,
                        addedByAdmin = true,
                        creationDate = 0,
                        idLanguage = UUID.fromString(it.languageId),
                        idSource = UUID.fromString(it.sourceId)
                    )
                )
            }
            wordRepository.insertAll(words)

            // update session
            sessionRepository.updateLastSyncDateTime()

            uiState = LoadingUIState.Downloaded
        }
    }
}
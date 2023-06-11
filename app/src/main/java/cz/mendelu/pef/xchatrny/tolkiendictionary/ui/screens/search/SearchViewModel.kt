package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.mendelu.pef.xchatrny.tolkiendictionary.architecture.BaseViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.DictionaryType
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.languages.ILanguagesRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.settings.ISettingsRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words.IWordsRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words.SearchCriteria
import kotlinx.coroutines.launch

class SearchViewModel(
    private val languagesRepository: ILanguagesRepository,
    private val wordsRepository: IWordsRepository,
    private val settingsRepository: ISettingsRepository
) : BaseViewModel(), SearchActions {
    var uiState by mutableStateOf<SearchUIState>(SearchUIState.Init)
    var data = SearchData()

    fun initData() {
        launch {
            // all dictionary types
            val languages = languagesRepository.getAll()
            data.dictionaryTypes.clear()
            languages.forEach {
                data.dictionaryTypes.add(DictionaryType(it, SearchCriteria.TRANSLATION))
                data.dictionaryTypes.add(DictionaryType(it, SearchCriteria.CZECH_MEANING))
            }

            // selected dictionary type
            if (data.dictionaryTypes.size > 0) {
                data.selectedDictionaryType = data.dictionaryTypes[0]
            }

            // load words
            uiState = SearchUIState.Loading
        }

        launch {
            settingsRepository.getFontSize().collect {
                data.fontSize = it
            }
        }
    }

    override fun search() {
        uiState = SearchUIState.Loading

        launch {
            data.selectedDictionaryType?.let { dict ->
                wordsRepository.getAll(data.searchQuery, dict.language, dict.criterium).collect {
                    data.wordsCount = it.size
                    uiState = SearchUIState.Success(it)
                }
            } ?: run {
                uiState = SearchUIState.Success(listOf())
            }
        }
    }

    override fun toggleWordBookmark(word: Word) {
        word.isBookmarked = !word.isBookmarked
        launch {
            wordsRepository.update(word)
        }
    }

    override fun onSearchQueryChange(newQuery: String) {
        data.searchQuery = newQuery
        uiState = SearchUIState.QueryChange
    }

    override fun onSelectedDictionaryTypeChange(newDictionaryType: DictionaryType) {
        data.selectedDictionaryType = newDictionaryType
        uiState = SearchUIState.SelectedDictionaryTypeChange
    }
}

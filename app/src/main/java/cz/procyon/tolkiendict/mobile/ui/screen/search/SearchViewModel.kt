package cz.procyon.tolkiendict.mobile.ui.screen.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.procyon.tolkiendict.mobile.R
import cz.procyon.tolkiendict.mobile.architecture.BaseViewModel
import cz.procyon.tolkiendict.mobile.model.DictionaryType
import cz.procyon.tolkiendict.mobile.model.Word
import cz.procyon.tolkiendict.mobile.repository.language.LanguageRepository
import cz.procyon.tolkiendict.mobile.repository.settings.SettingsRepository
import cz.procyon.tolkiendict.mobile.repository.word.WordRepository
import cz.procyon.tolkiendict.mobile.repository.word.SearchCriteria
import kotlinx.coroutines.launch

class SearchViewModel(
    private val languagesRepository: LanguageRepository,
    private val wordsRepository: WordRepository,
    private val settingsRepository: SettingsRepository
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

    override fun getWordCountLabel(count: Int): Int {
        return when (count) {
            0 -> {
                R.string.word_zero
            }
            1 -> {
                R.string.word_singular
            }
            in 1..4 -> {
                R.string.word_prular
            }
            else -> {
                R.string.word_prular_alternative
            }
        }
    }
}

package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.add_edit_word

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.mendelu.pef.xchatrny.tolkiendictionary.R
import cz.mendelu.pef.xchatrny.tolkiendictionary.architecture.BaseViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Source
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.languages.ILanguagesRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words.IWordsRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.fields.SelectFieldItem
import kotlinx.coroutines.launch
import java.util.UUID

class AddEditWordViewModel(
    private val wordsRepository: IWordsRepository,
    private val languagesRepository: ILanguagesRepository
) : BaseViewModel(), AddEditWordActions {
    var wordId: UUID? = null

    var uiState by mutableStateOf<AddEditWordUIState>(AddEditWordUIState.Loading)
    var data: AddEditWordData = AddEditWordData()

    fun initData() {
        launch {
            languagesRepository.getAll().collect {
                // load languages
                data.selectableLanguages = it.map { lang ->
                    SelectFieldItem(lang.name, lang.id)
                }

                // load word data
                wordId?.let { uuid ->
                    val wordWithSource = wordsRepository.getWordWithSourceById(id = uuid)
                    data.word = wordWithSource.word
                    data.selectedLanguage = getSelectableLanguage(wordWithSource.word.idLanguage)
                    data.source = wordWithSource.source
                }

                uiState = AddEditWordUIState.Default
            }
        }
    }

    override fun onWordChange(word: Word) {
        data.word = word
        uiState = AddEditWordUIState.DataChanged
    }

    override fun onSourceChange(source: Source?) {
        data.source = source
        uiState = AddEditWordUIState.DataChanged
    }

    override fun onLanguageChange(language: SelectFieldItem<UUID>?) {
        data.selectedLanguage = language
        uiState = AddEditWordUIState.DataChanged
    }

    override fun saveWord(update: Boolean) {
        val isValid = validateWord()

        if (isValid) {
            data.word.idLanguage = data.selectedLanguage?.value

            launch {
                uiState = if (update) {
                    wordsRepository.update(data.word)
                    AddEditWordUIState.WordUpdated
                } else {
                    wordsRepository.insert(data.word)
                    AddEditWordUIState.WordCreated
                }
            }
        } else {
            uiState = AddEditWordUIState.ValidationError
        }

    }

    override fun deleteWord() {
        launch {
            wordsRepository.delete(data.word)
            uiState = AddEditWordUIState.WordDeleted
        }
    }

    private fun validateWord(): Boolean {
        data.errorCzechMeaning = null
        data.errorTranslation = null
        data.errorLanguage = null
        var isValid = true

        if (data.word.czechMeaning.isEmpty()) {
            data.errorCzechMeaning = R.string.czechMeaningNotEmpty
            isValid = false
        }

        if (data.word.translation.isEmpty()) {
            data.errorTranslation = R.string.translationMeaningNotEmpty
            isValid = false
        }

        if (data.selectedLanguage == null) {
            data.errorLanguage = R.string.languageNotEmpty
            isValid = false
        }

        return isValid
    }

    private fun getSelectableLanguage(uuid: UUID?): SelectFieldItem<UUID>? {
        return data.selectableLanguages.find {
            it.value == uuid
        }
    }

}
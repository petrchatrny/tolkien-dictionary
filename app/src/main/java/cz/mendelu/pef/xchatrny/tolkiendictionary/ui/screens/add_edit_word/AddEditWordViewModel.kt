package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.add_edit_word

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.mendelu.pef.xchatrny.tolkiendictionary.R
import cz.mendelu.pef.xchatrny.tolkiendictionary.architecture.BaseViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.languages.ILanguagesRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.sources.ISourcesRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.tengwar.ITengwarRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words.IWordsRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.fields.SelectFieldItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class AddEditWordViewModel(
    private val wordsRepository: IWordsRepository,
    private val languagesRepository: ILanguagesRepository,
    private val sourcesRepository: ISourcesRepository,
    private val tengwarRepository: ITengwarRepository
) : BaseViewModel(), AddEditWordActions {
    var wordId: UUID? = null

    var uiState by mutableStateOf<AddEditWordUIState>(AddEditWordUIState.Loading)
    var data: AddEditWordData = AddEditWordData()

    init {
        launch {
            val languages = languagesRepository.getAll()
            data.selectableLanguages = languages.map { lang ->
                SelectFieldItem(lang.name, lang)
            }
        }

        launch {
            sourcesRepository.getAll().collect {
                data.selectableSources.clear()
                data.selectableSources.addAll(it.map { source ->
                    SelectFieldItem(source.name, source)
                })
            }
        }
    }

    fun initWord() {
        launch {
            wordId?.let { id ->
                wordsRepository
                    .getWordWithLanguageAndSourceById(id)
                    .collect { relation ->
                        relation?.let {
                            data.word = it.word
                            data.selectedLanguage =
                                SelectFieldItem(relation.language.name, relation.language)

                            relation.source?.let { source ->
                                data.selectedSource = SelectFieldItem(source.name, source)
                            }
                        }
                    }
            }
        }
    }
    override fun onDataChange(data: AddEditWordData) {
        this.data = data
        uiState = AddEditWordUIState.DataChanged
    }

    override fun saveWord(update: Boolean) {
        val isValid = validateWord()

        if (isValid) {
            uiState = AddEditWordUIState.Saving

            // set language (it is assumed that after validation the language cannot be null)
            data.word.idLanguage = data.selectedLanguage?.value?.id

            // set source
            data.word.idSource = data.selectedSource?.value?.id

            launch {
                // get tengwar from API and set it to word
                data.word.tengwar = null

                if (data.doTranscription) {
                    data.word.tengwar = withContext(Dispatchers.IO) {
                        try {
                            tengwarRepository.getTranscription(data.word.translation).first()
                        } catch (ignored: Exception) {
                            null
                        }
                    }
                }

                // save to db
                performSave(update)
            }
        } else {
            uiState = AddEditWordUIState.ValidationError
        }

    }

    private suspend fun performSave(update: Boolean) {
        uiState = if (update) {
            wordsRepository.update(data.word)
            AddEditWordUIState.WordUpdated
        } else {
            wordsRepository.insert(data.word)
            AddEditWordUIState.WordCreated
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
}
package cz.procyon.tolkiendict.mobile.ui.screen.word_form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.procyon.tolkiendict.mobile.R
import cz.procyon.tolkiendict.mobile.architecture.BaseViewModel
import cz.procyon.tolkiendict.mobile.repository.language.LanguageRepository
import cz.procyon.tolkiendict.mobile.repository.source.SourceRepository
import cz.procyon.tolkiendict.mobile.repository.tengwar.TengwarRepository
import cz.procyon.tolkiendict.mobile.repository.word.WordRepository
import cz.procyon.tolkiendict.mobile.ui.component.fields.SelectFieldItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class WordFormViewModel(
    private val wordsRepository: WordRepository,
    private val languagesRepository: LanguageRepository,
    private val sourcesRepository: SourceRepository,
    private val tengwarRepository: TengwarRepository
) : BaseViewModel(), WordFormActions {
    var wordId: UUID? = null

    var uiState by mutableStateOf<WordFormUiState>(WordFormUiState.Loading)
    var data: WordFormData = WordFormData()

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

    override fun onDataChange(data: WordFormData) {
        this.data = data
        uiState = WordFormUiState.DataChanged
    }

    override fun saveWord(update: Boolean) {
        val isValid = validateWord()

        if (isValid) {
            uiState = WordFormUiState.Saving

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
            uiState = WordFormUiState.ValidationError
        }

    }

    private suspend fun performSave(update: Boolean) {
        uiState = if (update) {
            wordsRepository.update(data.word)
            WordFormUiState.WordUpdated
        } else {
            wordsRepository.insert(data.word)
            WordFormUiState.WordCreated
        }
    }

    override fun deleteWord() {
        launch {
            wordsRepository.delete(data.word)
            uiState = WordFormUiState.WordDeleted
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
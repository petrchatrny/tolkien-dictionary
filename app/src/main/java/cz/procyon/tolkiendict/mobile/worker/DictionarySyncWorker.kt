package cz.procyon.tolkiendict.mobile.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import cz.procyon.tolkiendict.mobile.api.dto.DictionarySyncDto
import cz.procyon.tolkiendict.mobile.repository.dictionary.DictionaryRepository
import cz.procyon.tolkiendict.mobile.repository.language.LanguageRepository
import cz.procyon.tolkiendict.mobile.repository.settings.SettingsRepository
import cz.procyon.tolkiendict.mobile.repository.source.SourceRepository
import cz.procyon.tolkiendict.mobile.repository.word.WordRepository
import kotlinx.coroutines.flow.first
import java.util.UUID

class DictionarySyncWorker(
    private val dictionaryRepository: DictionaryRepository,

    private val languageRepository: LanguageRepository,
    private val wordsRepository: WordRepository,
    private val sourcesRepository: SourceRepository,

    private val settingsRepository: SettingsRepository,
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        // check sync is allowed in settings by user
        if (!settingsRepository.getIsAutoUpdateOn().first()) {
            return Result.success()
        }

        try {
            // do sync
            val res =
                dictionaryRepository.syncDictionary(dictionaryRepository.getLastSyncDateTime())

            // delete old entities
            performDelete(res)

            // insert new entities
            performInsert(res)

            // update existing entities
            updateLanguages(res)
            updateSources(res)
            updateWords(res)

            // update last sync date
            dictionaryRepository.updateLastSyncDateTime()

            // return worker's result
            return Result.success()

        } catch (e: Exception) {
            return Result.retry()
        }
    }

    private suspend fun performDelete(response: DictionarySyncDto) {
        wordsRepository.deleteMultiple(response.deleted.words.map { UUID.fromString(it) })
        languageRepository.deleteMultiple(response.deleted.languages.map { UUID.fromString(it) })
        sourcesRepository.deleteMultiple(response.deleted.sources.map { UUID.fromString(it) })
    }

    private suspend fun performInsert(response: DictionarySyncDto) {
        languageRepository.insertAll(response.created.languages.map { it.toLanguage() })
        sourcesRepository.insertAll(response.created.sources.map { it.toSource() })
        wordsRepository.insertAll(response.created.words.map { it.toWord() })
    }

    private suspend fun updateLanguages(response: DictionarySyncDto) {
        response.updated.languages.forEach { dto ->
            // get language
            val language = languageRepository.getOne(UUID.fromString(dto.id))

            // update language
            language?.let { lang ->
                lang.name = dto.name

                languageRepository.update(lang)
            }
        }
    }

    private suspend fun updateSources(response: DictionarySyncDto) {
        response.updated.sources.forEach { dto ->
            val source = sourcesRepository.getOne(UUID.fromString(dto.id))

            source?.let { src ->
                src.name = dto.name
                src.url = dto.url

                sourcesRepository.update(source)
            }
        }
    }

    private suspend fun updateWords(response: DictionarySyncDto) {
        response.updated.words.forEach { dto ->
            val word = wordsRepository.getOne(UUID.fromString(dto.id))

            word?.let {
                it.apply {
                    czechMeaning = dto.czechMeaning
                    translation = dto.translation
                    tengwar = dto.tengwar
                    idLanguage = UUID.fromString(dto.languageId)
                    idSource = UUID.fromString(dto.sourceId)
                }
                wordsRepository.update(word)
            }
        }
    }
}
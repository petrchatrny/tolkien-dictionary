package cz.mendelu.pef.xchatrny.tolkiendictionary.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import cz.mendelu.pef.xchatrny.tolkiendictionary.api.dto.DictionarySyncDTO
import cz.mendelu.pef.xchatrny.tolkiendictionary.extensions.TAG
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.dictionaries.IDictionaryRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.languages.ILanguagesRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.session.ISessionRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.settings.ISettingsRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.sources.ISourcesRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words.IWordsRepository
import kotlinx.coroutines.flow.first
import java.util.UUID

class DictionarySyncWorker(
    private val dictionaryRepository: IDictionaryRepository,

    private val languageRepository: ILanguagesRepository,
    private val wordsRepository: IWordsRepository,
    private val sourcesRepository: ISourcesRepository,

    private val settingsRepository: ISettingsRepository,
    private val sessionRepository: ISessionRepository,

    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        Log.d(TAG, "Starting sync")

        // check sync is allowed in settings by user
        if (!settingsRepository.getIsAutoUpdateOn().first()) {
            return Result.success()
        }

        try {
            // do sync
            val res = dictionaryRepository.syncDictionary(sessionRepository.getLastSyncDateTime())

            // delete old entities
            performDelete(res)

            // insert new entities
            performInsert(res)

            // update existing entities
            updateLanguages(res)
            updateSources(res)
            updateWords(res)

            // update last sync date
            sessionRepository.updateLastSyncDateTime()

            // return worker's result
            return Result.success()

        } catch (e: Exception) {
            Log.e(TAG, "Sync failed due to: " + e.message)
            return Result.retry()
        }
    }

    private suspend fun performDelete(response: DictionarySyncDTO) {
        wordsRepository.deleteMultiple(response.deleted.words.map { UUID.fromString(it) })
        languageRepository.deleteMultiple(response.deleted.languages.map { UUID.fromString(it) })
        sourcesRepository.deleteMultiple(response.deleted.sources.map { UUID.fromString(it) })
    }

    private suspend fun performInsert(response: DictionarySyncDTO) {
        languageRepository.insertAll(response.created.languages.map { it.toLanguage() })
        sourcesRepository.insertAll(response.created.sources.map { it.toSource() })
        wordsRepository.insertAll(response.created.words.map { it.toWord() })
    }

    private suspend fun updateLanguages(response: DictionarySyncDTO) {
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

    private suspend fun updateSources(response: DictionarySyncDTO) {
        response.updated.sources.forEach { dto ->
            val source = sourcesRepository.getOne(UUID.fromString(dto.id))

            source?.let { src ->
                src.name = dto.name
                src.url = dto.url

                sourcesRepository.update(source)
            }
        }
    }

    private suspend fun updateWords(response: DictionarySyncDTO) {
        response.updated.words.forEach { dto ->
            val word = wordsRepository.getOne(UUID.fromString(dto.id))

            word?.let { wrd ->
                wrd.apply {
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
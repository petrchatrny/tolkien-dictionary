package cz.mendelu.pef.xchatrny.tolkiendictionary.di

import android.content.Context
import androidx.work.WorkerParameters
import cz.mendelu.pef.xchatrny.tolkiendictionary.TolkienDictionaryApp
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.dictionaries.IDictionaryRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.languages.ILanguagesRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.session.ISessionRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.settings.ISettingsRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.sources.ISourcesRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words.IWordsRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.worker.DictionarySyncWorker
import org.koin.androidx.workmanager.dsl.worker
import org.koin.core.qualifier.named
import org.koin.dsl.module

val workerModule = module {
    fun provideDictionarySyncWorker(
        dictionaryRepository: IDictionaryRepository,
        languageRepository: ILanguagesRepository,
        wordsRepository: IWordsRepository,
        sourcesRepository: ISourcesRepository,
        settingsRepository: ISettingsRepository,
        sessionRepository: ISessionRepository,
        context: Context,
        params: WorkerParameters
    ): DictionarySyncWorker {
        return DictionarySyncWorker(
            dictionaryRepository,
            languageRepository,
            wordsRepository,
            sourcesRepository,
            settingsRepository,
            sessionRepository,
            context,
            params
        )
    }

    worker(named<DictionarySyncWorker>()) {
        provideDictionarySyncWorker(
            get(), get(), get(), get(), get(), get(),
            TolkienDictionaryApp.appContext, get()
        )
    }
}
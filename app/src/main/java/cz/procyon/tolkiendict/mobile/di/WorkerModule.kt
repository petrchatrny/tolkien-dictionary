package cz.procyon.tolkiendict.mobile.di

import android.content.Context
import androidx.work.WorkerParameters
import cz.procyon.tolkiendict.mobile.TolkienDictionaryApp
import cz.procyon.tolkiendict.mobile.repository.dictionary.DictionaryRepository
import cz.procyon.tolkiendict.mobile.repository.language.LanguageRepository
import cz.procyon.tolkiendict.mobile.repository.settings.SettingsRepository
import cz.procyon.tolkiendict.mobile.repository.source.SourceRepository
import cz.procyon.tolkiendict.mobile.repository.word.WordRepository
import cz.procyon.tolkiendict.mobile.worker.DictionarySyncWorker
import org.koin.androidx.workmanager.dsl.worker
import org.koin.core.qualifier.named
import org.koin.dsl.module

val workerModule = module {
    fun provideDictionarySyncWorker(
        dictionaryRepository: DictionaryRepository,
        languageRepository: LanguageRepository,
        wordsRepository: WordRepository,
        sourcesRepository: SourceRepository,
        settingsRepository: SettingsRepository,
        context: Context,
        params: WorkerParameters
    ): DictionarySyncWorker {
        return DictionarySyncWorker(
            dictionaryRepository,
            languageRepository,
            wordsRepository,
            sourcesRepository,
            settingsRepository,
            context,
            params
        )
    }

    worker(named<DictionarySyncWorker>()) {
        provideDictionarySyncWorker(
            get(), get(), get(), get(), get(),
            TolkienDictionaryApp.appContext, get()
        )
    }
}
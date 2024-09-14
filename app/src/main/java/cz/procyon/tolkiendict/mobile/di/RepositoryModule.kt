package cz.procyon.tolkiendict.mobile.di

import android.content.Context
import cz.procyon.tolkiendict.mobile.TolkienDictionaryApp
import cz.procyon.tolkiendict.mobile.api.endpoint.DictionaryApi
import cz.procyon.tolkiendict.mobile.api.endpoint.TengwarApi
import cz.procyon.tolkiendict.mobile.database.dao.LanguageDao
import cz.procyon.tolkiendict.mobile.database.dao.SourceDao
import cz.procyon.tolkiendict.mobile.database.dao.WordDao
import cz.procyon.tolkiendict.mobile.repository.dictionary.DictionaryRestRepository
import cz.procyon.tolkiendict.mobile.repository.dictionary.DictionaryRepository
import cz.procyon.tolkiendict.mobile.repository.language.LanguageRepository
import cz.procyon.tolkiendict.mobile.repository.language.LanguageRoomRepository
import cz.procyon.tolkiendict.mobile.repository.settings.SettingsRepository
import cz.procyon.tolkiendict.mobile.repository.settings.SettingsDataStoreRepository
import cz.procyon.tolkiendict.mobile.repository.source.SourceRepository
import cz.procyon.tolkiendict.mobile.repository.source.SourceRoomRepository
import cz.procyon.tolkiendict.mobile.repository.tengwar.TengwarRepository
import cz.procyon.tolkiendict.mobile.repository.tengwar.TengwarRestRepository
import cz.procyon.tolkiendict.mobile.repository.word.WordRepository
import cz.procyon.tolkiendict.mobile.repository.word.WordRoomRepository
import org.koin.dsl.module

val repositoryModule = module {

    fun provideWordsRepository(dao: WordDao): WordRepository {
        return WordRoomRepository(dao)
    }

    fun provideLanguagesRepository(dao: LanguageDao): LanguageRepository {
        return LanguageRoomRepository(dao)
    }

    fun provideSourcesRepository(dao: SourceDao): SourceRepository {
        return SourceRoomRepository(dao)
    }

    fun provideTengwarRepository(api: TengwarApi): TengwarRepository {
        return TengwarRestRepository(api)
    }

    fun provideDictionaryRepository(api: DictionaryApi, context: Context): DictionaryRepository {
        return DictionaryRestRepository(api, context)
    }

    fun provideSettingsRepository(context: Context): SettingsRepository {
        return SettingsDataStoreRepository(context)
    }

    single { provideWordsRepository(get()) }
    single { provideLanguagesRepository(get()) }
    single { provideSourcesRepository(get()) }
    single { provideTengwarRepository(get()) }
    single { provideDictionaryRepository(get(), TolkienDictionaryApp.appContext) }
    single { provideSettingsRepository(TolkienDictionaryApp.appContext) }
}

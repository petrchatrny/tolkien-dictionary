package cz.mendelu.pef.xchatrny.tolkiendictionary.di

import android.content.Context
import cz.mendelu.pef.xchatrny.tolkiendictionary.api.endpoints.DictionaryApi
import cz.mendelu.pef.xchatrny.tolkiendictionary.api.endpoints.TengwarApi
import cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao.LanguagesDao
import cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao.SourcesDao
import cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao.WordsDao
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.dictionaries.DictionaryTolkienApiRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.dictionaries.IDictionaryRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.languages.ILanguagesRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.languages.LanguagesRoomRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.session.ISessionRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.session.SessionDataStoreRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.sources.ISourcesRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.sources.SourcesRoomRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.tengwar.ITengwarRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.tengwar.TengwarTencendilRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words.IWordsRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words.WordsRoomRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    fun provideWordsRepository(dao: WordsDao): IWordsRepository {
        return WordsRoomRepository(dao)
    }

    fun provideLanguagesRepository(dao: LanguagesDao): ILanguagesRepository {
        return LanguagesRoomRepository(dao)
    }

    fun provideSourcesRepository(dao: SourcesDao): ISourcesRepository {
        return SourcesRoomRepository(dao)
    }

    fun provideTengwarRepository(api: TengwarApi): ITengwarRepository {
        return TengwarTencendilRepository(api)
    }

    fun provideDictionaryRepository(api: DictionaryApi): IDictionaryRepository {
        return DictionaryTolkienApiRepository(api)
    }

    fun provideSessionRepository(context: Context): ISessionRepository {
        return SessionDataStoreRepository(context)
    }

    single { provideWordsRepository(get()) }
    single { provideLanguagesRepository(get()) }
    single { provideSourcesRepository(get()) }
    single { provideTengwarRepository(get()) }
    single { provideDictionaryRepository(get()) }
    single { provideSessionRepository(androidContext()) }
}

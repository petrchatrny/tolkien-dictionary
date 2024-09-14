package cz.procyon.tolkiendict.mobile.di

import cz.procyon.tolkiendict.mobile.database.DictionaryDatabase
import cz.procyon.tolkiendict.mobile.database.dao.LanguageDao
import cz.procyon.tolkiendict.mobile.database.dao.SourceDao
import cz.procyon.tolkiendict.mobile.database.dao.WordDao
import org.koin.dsl.module

val daoModule = module {

    fun provideWordsDao(database: DictionaryDatabase): WordDao {
        return database.wordsDao
    }

    fun provideLanguagesDao(database: DictionaryDatabase): LanguageDao {
        return database.languagesDao
    }

    fun provideSourcesDao(database: DictionaryDatabase): SourceDao {
        return database.sourcesDao
    }

    single { provideWordsDao(get()) }
    single { provideLanguagesDao(get()) }
    single { provideSourcesDao(get()) }
}


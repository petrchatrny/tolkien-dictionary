package cz.procyon.tolkiendict.mobile.di

import cz.procyon.tolkiendict.mobile.database.DictionaryDatabase
import cz.procyon.tolkiendict.mobile.database.dao.LanguageDao
import cz.procyon.tolkiendict.mobile.database.dao.SourceDao
import cz.procyon.tolkiendict.mobile.database.dao.WordDao
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val daoModule = module {
    singleOf(::provideWordsDao)
    singleOf(::provideLanguagesDao)
    singleOf(::provideSourcesDao)
}

private fun provideWordsDao(database: DictionaryDatabase): WordDao {
    return database.wordsDao
}

private fun provideLanguagesDao(database: DictionaryDatabase): LanguageDao {
    return database.languagesDao
}

private fun provideSourcesDao(database: DictionaryDatabase): SourceDao {
    return database.sourcesDao
}
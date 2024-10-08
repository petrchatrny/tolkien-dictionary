package cz.procyon.tolkiendict.mobile.di

import cz.procyon.tolkiendict.mobile.TolkienDictionaryApp
import cz.procyon.tolkiendict.mobile.database.DictionaryDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    singleOf(::provideDictionaryDatabase)
}

private fun provideDictionaryDatabase(): DictionaryDatabase {
    return DictionaryDatabase.getDatabase(TolkienDictionaryApp.appContext)
}
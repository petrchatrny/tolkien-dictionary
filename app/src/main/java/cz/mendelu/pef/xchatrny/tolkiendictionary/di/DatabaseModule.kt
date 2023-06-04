package cz.mendelu.pef.xchatrny.tolkiendictionary.di

import cz.mendelu.pef.xchatrny.tolkiendictionary.TolkienDictionaryApp
import cz.mendelu.pef.xchatrny.tolkiendictionary.database.DictionaryDatabase
import org.koin.dsl.module

val databaseModule = module {

    fun provideDictionaryDatabase(): DictionaryDatabase {
        return DictionaryDatabase.getDatabase(TolkienDictionaryApp.appContext)
    }

    single { provideDictionaryDatabase() }
}


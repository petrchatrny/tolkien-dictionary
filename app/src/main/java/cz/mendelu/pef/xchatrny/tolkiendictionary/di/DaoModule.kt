package cz.mendelu.pef.xchatrny.tolkiendictionary.di

import cz.mendelu.pef.xchatrny.tolkiendictionary.database.DictionaryDatabase
import cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao.LanguagesDao
import cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao.SourcesDao
import cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao.WordsDao
import org.koin.dsl.module

val daoModule = module {

    fun provideWordsDao(database: DictionaryDatabase): WordsDao {
        return database.wordsDao
    }

    fun provideLanguagesDao(database: DictionaryDatabase): LanguagesDao {
        return database.languagesDao
    }

    fun provideSourcesDao(database: DictionaryDatabase): SourcesDao {
        return database.sourcesDao
    }

    single { provideWordsDao(get()) }
    single { provideLanguagesDao(get()) }
    single { provideSourcesDao(get()) }
}


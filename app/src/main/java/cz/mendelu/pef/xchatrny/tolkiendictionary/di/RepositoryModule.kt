package cz.mendelu.pef.xchatrny.tolkiendictionary.di

import cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao.LanguagesDao
import cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao.SourcesDao
import cz.mendelu.pef.xchatrny.tolkiendictionary.database.dao.WordsDao
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.languages.ILanguagesRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.languages.LanguagesRoomRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.sources.ISourcesRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.sources.SourcesRoomRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words.IWordsRepository
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words.WordsRoomRepository
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

    single {
        provideWordsRepository(get())
        provideLanguagesRepository(get())
        provideSourcesRepository(get())
    }

}

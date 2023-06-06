package cz.mendelu.pef.xchatrny.tolkiendictionary.di

import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.add_edit_word.AddEditWordViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { AddEditWordViewModel(get(), get()) }
    viewModel { SearchViewModel(get(), get()) }

}

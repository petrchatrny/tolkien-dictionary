package cz.mendelu.pef.xchatrny.tolkiendictionary.di

import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.dialogs.choose_source.ChooseSourceViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.add_edit_word.AddEditWordViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.loading.LoadingViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.saved_words.SavedWordsViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.search.SearchViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.settings.SettingsViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.software_libraries.SoftwareLibrariesViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.sources.SourcesViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.word_detail.WordDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { AddEditWordViewModel(get(), get(), get(), get()) }
    viewModel { SearchViewModel(get(), get(), get()) }
    viewModel { SavedWordsViewModel(get()) }
    viewModel { WordDetailViewModel(get()) }
    viewModel { LoadingViewModel(get(), get(), get(), get(), get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { SoftwareLibrariesViewModel() }
    viewModel { SourcesViewModel(get()) }
    viewModel { ChooseSourceViewModel(get()) }
}

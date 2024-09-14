package cz.procyon.tolkiendict.mobile.di

import cz.procyon.tolkiendict.mobile.ui.dialog.choose_source.ChooseSourceViewModel
import cz.procyon.tolkiendict.mobile.ui.screen.add_edit_word.AddEditWordViewModel
import cz.procyon.tolkiendict.mobile.ui.screen.loading.LoadingViewModel
import cz.procyon.tolkiendict.mobile.ui.screen.saved_words.SavedWordsViewModel
import cz.procyon.tolkiendict.mobile.ui.screen.search.SearchViewModel
import cz.procyon.tolkiendict.mobile.ui.screen.settings.SettingsViewModel
import cz.procyon.tolkiendict.mobile.ui.screen.software_libraries.SoftwareLibrariesViewModel
import cz.procyon.tolkiendict.mobile.ui.screen.sources.SourcesViewModel
import cz.procyon.tolkiendict.mobile.ui.screen.word_detail.WordDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { AddEditWordViewModel(get(), get(), get(), get()) }
    viewModel { SearchViewModel(get(), get(), get()) }
    viewModel { SavedWordsViewModel(get()) }
    viewModel { WordDetailViewModel(get()) }
    viewModel { LoadingViewModel(get(), get(), get(), get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { SoftwareLibrariesViewModel() }
    viewModel { SourcesViewModel(get()) }
    viewModel { ChooseSourceViewModel(get()) }
}

package cz.procyon.tolkiendict.mobile.di

import cz.procyon.tolkiendict.mobile.ui.dialog.choose_source.ChooseSourceViewModel
import cz.procyon.tolkiendict.mobile.ui.screen.word_form.WordFormViewModel
import cz.procyon.tolkiendict.mobile.ui.screen.loading.LoadingViewModel
import cz.procyon.tolkiendict.mobile.ui.screen.word_saved.WordSavedViewModel
import cz.procyon.tolkiendict.mobile.ui.screen.word_search.WordSearchViewModel
import cz.procyon.tolkiendict.mobile.ui.screen.settings.SettingsViewModel
import cz.procyon.tolkiendict.mobile.ui.screen.software_lib.SoftwareLibsViewModel
import cz.procyon.tolkiendict.mobile.ui.screen.sources.SourcesViewModel
import cz.procyon.tolkiendict.mobile.ui.screen.word_detail.WordDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::WordFormViewModel)
    viewModelOf(::WordSearchViewModel)
    viewModelOf(::WordSavedViewModel)
    viewModelOf(::WordDetailViewModel)
    viewModelOf(::LoadingViewModel)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::SoftwareLibsViewModel)
    viewModelOf(::SourcesViewModel)
    viewModelOf(::ChooseSourceViewModel)
}

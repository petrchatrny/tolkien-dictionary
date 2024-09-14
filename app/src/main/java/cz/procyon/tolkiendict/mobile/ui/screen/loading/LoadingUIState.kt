package cz.procyon.tolkiendict.mobile.ui.screen.loading

sealed class LoadingUIState {
    object Default : LoadingUIState()
    object Loading: LoadingUIState()
    object Downloading : LoadingUIState()
    object Downloaded : LoadingUIState()
    object NetworkError : LoadingUIState()
}


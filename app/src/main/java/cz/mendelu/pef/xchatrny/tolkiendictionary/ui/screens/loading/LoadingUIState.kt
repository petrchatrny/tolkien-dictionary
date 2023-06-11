package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.loading

sealed class LoadingUIState {
    object Default : LoadingUIState()
    object Loading: LoadingUIState()
    object Downloading : LoadingUIState()
    object Downloaded : LoadingUIState()
    object NetworkError : LoadingUIState()
}


package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.settings

sealed class SettingsUIState {
    object Default : SettingsUIState()
    object DataChanged : SettingsUIState()
}
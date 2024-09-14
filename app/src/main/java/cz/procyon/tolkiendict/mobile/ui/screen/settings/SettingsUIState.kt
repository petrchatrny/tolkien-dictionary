package cz.procyon.tolkiendict.mobile.ui.screen.settings

sealed class SettingsUIState {
    object Default : SettingsUIState()
    object DataChanged : SettingsUIState()
}
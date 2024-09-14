package cz.procyon.tolkiendict.mobile.ui.screen.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.procyon.tolkiendict.mobile.architecture.BaseViewModel
import cz.procyon.tolkiendict.mobile.datastore.entity.FontSize
import cz.procyon.tolkiendict.mobile.datastore.entity.TengwarFontFamily
import cz.procyon.tolkiendict.mobile.repository.settings.SettingsRepository
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: SettingsRepository
) : BaseViewModel(), SettingsActions {

    var uiState by mutableStateOf<SettingsUIState>(SettingsUIState.Default)
    var data = SettingsData()

    init {
        launch {
            repository.getIsAutoUpdateOn().collect {
                data.isAutoUpdateOn = it
                uiState = SettingsUIState.DataChanged
            }
        }

        launch {
            repository.getTengwarFont().collect {
                data.tengwarFontFamily = it
                uiState = SettingsUIState.DataChanged
            }
        }

        launch {
            repository.getFontSize().collect {
                data.fontSize = it
                uiState = SettingsUIState.DataChanged
            }
        }

        launch {
            repository.getIsInDarkMode().collect {
                data.isInDarkMode = it
                uiState = SettingsUIState.DataChanged
            }
        }

    }

    override fun onIsAutoUpdateChanged(autoUpdate: Boolean) {
        launch {
            repository.setIsAutoUpdateOn(autoUpdate)
            uiState = SettingsUIState.DataChanged
        }
    }

    override fun onTengwarFontFamilyChanged(tengwarFontFamily: TengwarFontFamily) {
        launch {
            repository.setTengwarFont(tengwarFontFamily)
            uiState = SettingsUIState.DataChanged
        }
    }

    override fun onFontSizeChanged(fontSize: FontSize) {
        launch {
            repository.setFontSize(fontSize)
            uiState = SettingsUIState.DataChanged
        }
    }

    override fun onIsInDarkModeChanged(isInDarkMode: Boolean) {
        launch {
            repository.setIsInDarkMode(isInDarkMode)
            uiState = SettingsUIState.DataChanged
        }
    }

}
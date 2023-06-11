package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cz.mendelu.pef.xchatrny.tolkiendictionary.architecture.BaseViewModel
import cz.mendelu.pef.xchatrny.tolkiendictionary.datastore.entities.FontSize
import cz.mendelu.pef.xchatrny.tolkiendictionary.datastore.entities.TengwarFontFamily
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.settings.ISettingsRepository
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: ISettingsRepository
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
package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cz.mendelu.pef.xchatrny.tolkiendictionary.BuildConfig
import cz.mendelu.pef.xchatrny.tolkiendictionary.R
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.INavigationRouter
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.BackArrowScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.preferences.PrefGroup
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.preferences.SpinnerPreference
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.preferences.TextPreference
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(
    paddingValues: PaddingValues,
    navigation: INavigationRouter,
    viewModel: SettingsViewModel = getViewModel()
) {
    var data by remember { mutableStateOf(viewModel.data) }

    viewModel.uiState.let {
        when (it) {
            SettingsUIState.Default -> {}
            SettingsUIState.DataChanged -> {
                data = viewModel.data
                viewModel.uiState = SettingsUIState.Default
            }
        }
    }

    BackArrowScreen(
        modifier = Modifier.padding(paddingValues),
        drawFullScreenContent = true,
        appBarTitle = stringResource(R.string.settings)
    ) {
        SettingsScreenContent(
            paddingValues = it,
            navigation = navigation,
            data = data,
            actions = viewModel
        )
    }
}

@Composable
fun SettingsScreenContent(
    paddingValues: PaddingValues,
    navigation: INavigationRouter,
    data: SettingsData,
    actions: SettingsActions
) {
    Column(modifier = Modifier.padding(paddingValues)) {
        TextPreference(
            title = stringResource(R.string.update_dictionaries),
            summary = stringResource(R.string.automatically_download_dictionaries),
            trailingContent = {
                Switch(
                    checked = data.isAutoUpdateOn,
                    onCheckedChange = { actions.onIsAutoUpdateChanged(it) }
                )
            }
        )

        PrefGroup(title = stringResource(R.string.appearance))

        SpinnerPreference(
            title = stringResource(R.string.tengwar_font),
            items = data.tengwarFontFamilyList.map { it.fontFamilyName },
            selectedItem = data.tengwarFontFamily.fontFamilyName,
            onSelectedItemChanged = {
                data.tengwarFontFamily = data.tengwarFontFamilyList[it]
                actions.onTengwarFontFamilyChanged(data.tengwarFontFamilyList[it])
            }
        )

        SpinnerPreference(
            title = stringResource(R.string.font_size),
            items = data.fontSizeList.map { stringResource(id = it.labelId) },
            selectedItem = stringResource(id = data.fontSize.labelId),
            onSelectedItemChanged = {
                data.fontSize = data.fontSizeList[it]
                actions.onFontSizeChanged(data.fontSizeList[it])
            }
        )

        TextPreference(
            title = stringResource(R.string.dark_mode),
            trailingContent = {
                Switch(
                    checked = data.isInDarkMode,
                    onCheckedChange = { actions.onIsInDarkModeChanged(it) }
                )
            }
        )

        PrefGroup(title = stringResource(R.string.about_app))

        TextPreference(
            title = stringResource(R.string.version),
            summary = BuildConfig.VERSION_NAME
        )

        TextPreference(
            title = stringResource(id = R.string.sources),
            trailingContent = {
                Icon(imageVector = Icons.Default.ArrowRight, contentDescription = null)
            },
            onClick = { navigation.navigateToSourcesScreen() },
            enabled = true
        )

        TextPreference(
            title = stringResource(R.string.used_software_libs),
            trailingContent = {
                Icon(imageVector = Icons.Default.ArrowRight, contentDescription = null)
            },
            onClick = { navigation.navigateToSoftwareLibrariesScreen() },
            enabled = true
        )
    }
}
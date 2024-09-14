package cz.procyon.tolkiendict.mobile.ui.screen.sources

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.procyon.tolkiendict.mobile.model.Source
import cz.procyon.tolkiendict.mobile.navigation.INavigationRouter
import cz.procyon.tolkiendict.mobile.ui.component.BackArrowScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.preferences.PrefGroup
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.preferences.TextPreference
import cz.procyon.tolkiendict.mobile.R
import org.koin.androidx.compose.getViewModel

@Composable
fun SourcesScreen(navigation: INavigationRouter, viewModel: SourcesViewModel = getViewModel()) {
    val adminSources = remember { mutableStateListOf<Source>() }
    val userSources = remember { mutableStateListOf<Source>() }

    viewModel.uiState.let {
        when (it) {
            SourcesUIState.Default -> {

            }

            is SourcesUIState.Success -> {
                adminSources.clear()
                adminSources.addAll(it.adminSources)

                userSources.clear()
                userSources.addAll(it.userSources)

                viewModel.uiState = SourcesUIState.Default
            }
        }
    }

    BackArrowScreen(
        drawFullScreenContent = true,
        appBarTitle = stringResource(id = R.string.sources),
        onBackClick = { navigation.navigateBack() }
    ) {
        SourcesScreenContent(
            paddingValues = it,
            adminSources = adminSources,
            userSources = userSources
        )
    }
}

@Composable
fun SourcesScreenContent(
    paddingValues: PaddingValues,
    adminSources: List<Source>,
    userSources: List<Source>
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
    ) {
        PrefGroup(title = stringResource(R.string.images))
        TextPreference(
            title = "worldvectorlogo.com",
            uri = "https://worldvectorlogo.com/logo/the-one-ring-2"
        )
        TextPreference(title = "Jessa Wolfcloud", uri = "https://jessawolfcloud.carrd.co/")
        Spacer(modifier = Modifier.height(16.dp))

        PrefGroup(title = stringResource(R.string.words))
        if (adminSources.isEmpty()) {
            TextPreference(title = stringResource(R.string.no_words_sources))

        } else {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                adminSources.forEach {
                    item {
                        TextPreference(title = it.name, uri = it.url)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        PrefGroup(title = stringResource(R.string.user_added_sources))
        if (userSources.isEmpty()) {
            TextPreference(title = stringResource(R.string.no_user_added_sources))
        } else {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                userSources.forEach {
                    item {
                        TextPreference(title = it.name, uri = it.url)
                    }
                }
            }
        }
    }
}
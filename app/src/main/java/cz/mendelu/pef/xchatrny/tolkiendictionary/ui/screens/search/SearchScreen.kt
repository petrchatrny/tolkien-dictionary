package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.xchatrny.tolkiendictionary.R
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.INavigationRouter
import cz.mendelu.pef.xchatrny.tolkiendictionary.repository.words.SearchCriteria
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.SearchTopBar
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.TextSpinner
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.lists.WordLT
import org.koin.androidx.compose.getViewModel


@Composable
fun SearchScreen(
    paddingValues: PaddingValues,
    navigation: INavigationRouter,
    viewModel: SearchViewModel = getViewModel()
) {
    val words = remember { mutableStateListOf<Word>() }

    viewModel.uiState.let {
        when (it) {
            SearchUIState.Init -> {
                viewModel.initData()
            }

            SearchUIState.Default -> {}

            SearchUIState.Loading -> {
                words.clear()
                viewModel.search()
            }

            is SearchUIState.Success -> {
                words.clear()
                words.addAll(it.words)
            }

            SearchUIState.QueryChange -> {
                viewModel.uiState = SearchUIState.Default
            }

            SearchUIState.SelectedDictionaryTypeChange -> {
                viewModel.uiState = SearchUIState.Loading
            }
        }
    }

    Scaffold(
        modifier = Modifier.padding(paddingValues),
        topBar = {
            SearchTopBar(
                title = stringResource(id = R.string.app_name),
                query = viewModel.data.searchQuery,
                onQueryChange = { viewModel.onSearchQueryChange(it) },
                onSubmit = {
                    viewModel.uiState = SearchUIState.Loading
                },
                onClear = {
                    viewModel.onSearchQueryChange("")
                    viewModel.uiState = SearchUIState.Loading
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigation.navigateToAddEditWordScreen(null) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }) {
        SearchScreenContent(
            paddingValues = it,
            navigation = navigation,
            actions = viewModel,
            words = words,
            data = viewModel.data,
            isLoading = viewModel.uiState == SearchUIState.Loading
        )
    }
}

@Composable
fun SearchScreenContent(
    paddingValues: PaddingValues,
    navigation: INavigationRouter,
    actions: SearchActions,
    words: List<Word>,
    data: SearchData,
    isLoading: Boolean
) {
    Column(modifier = Modifier.padding(paddingValues)) {
        Row(
            modifier = Modifier
                .padding(top = 4.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextSpinner(
                items = data.dictionaryTypes.map { it.getDisplayName(stringResource(R.string.czech)) },
                selectedItem = data.selectedDictionaryType?.getDisplayName(stringResource(R.string.czech)) ?: "",
                onSelectedItemChanged = {
                    actions.onSelectedDictionaryTypeChange(data.dictionaryTypes[it])
                }
            )

            ElevatedSuggestionChip(
                onClick = {},
                label = {
                    Text(
                        text = "${data.wordsCount} slov", // TODO singular vs plural
                        fontSize = MaterialTheme.typography.labelMedium.fontSize
                    )
                },
            )
        }

        if (words.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                words.forEach { word ->
                    item(key = word.id) {
                        WordLT(
                            word = word,
                            onClick = { navigation.navigateToWordDetailScreen(id = word.id) },
                            onSwipeLeftToRight = { actions.toggleWordBookmark(word) },
                            onSwipeRightToLeft = { navigation.navigateToAddEditWordScreen(word.id) },
                            inverse = data.selectedDictionaryType?.criterium == SearchCriteria.CZECH_MEANING
                        )
                        Divider(color = MaterialTheme.colorScheme.outlineVariant)
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = "Nebyla nalezena žádná slovíčka",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
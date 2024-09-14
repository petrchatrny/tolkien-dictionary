package cz.procyon.tolkiendict.mobile.ui.screen.word_search

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
import cz.procyon.tolkiendict.mobile.R
import cz.procyon.tolkiendict.mobile.model.Word
import cz.procyon.tolkiendict.mobile.navigation.NavigationRouter
import cz.procyon.tolkiendict.mobile.repository.word.SearchCriteria
import cz.procyon.tolkiendict.mobile.ui.component.SearchTopBar
import cz.procyon.tolkiendict.mobile.ui.component.TextSpinner
import cz.procyon.tolkiendict.mobile.ui.component.lists.WordLT
import org.koin.androidx.compose.getViewModel

@Composable
fun WordSearchScreen(
    paddingValues: PaddingValues,
    navigation: NavigationRouter,
    viewModel: WordSearchViewModel = getViewModel()
) {
    val words = remember { mutableStateListOf<Word>() }

    viewModel.uiState.let {
        when (it) {
            WordSearchUiState.Init -> {
                viewModel.initData()
            }

            WordSearchUiState.Default -> {}

            WordSearchUiState.Loading -> {
                words.clear()
                viewModel.search()
            }

            is WordSearchUiState.Success -> {
                words.clear()
                words.addAll(it.words)
            }

            WordSearchUiState.QueryChange -> {
                viewModel.uiState = WordSearchUiState.Default
            }

            WordSearchUiState.SelectedDictionaryTypeChange -> {
                viewModel.uiState = WordSearchUiState.Loading
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
                    viewModel.uiState = WordSearchUiState.Loading
                },
                onClear = {
                    viewModel.onSearchQueryChange("")
                    viewModel.uiState = WordSearchUiState.Loading
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigation.navigateToAddEditWordScreen(null) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }) {
        WordSearchContent(
            paddingValues = it,
            navigation = navigation,
            actions = viewModel,
            words = words,
            data = viewModel.data,
            isLoading = viewModel.uiState == WordSearchUiState.Loading
        )
    }
}

@Composable
private fun WordSearchContent(
    paddingValues: PaddingValues,
    navigation: NavigationRouter,
    actions: WordSearchActions,
    words: List<Word>,
    data: WordSearchData,
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
                selectedItem = data.selectedDictionaryType?.getDisplayName(stringResource(R.string.czech))
                    ?: "",
                onSelectedItemChanged = {
                    actions.onSelectedDictionaryTypeChange(data.dictionaryTypes[it])
                }
            )

            ElevatedSuggestionChip(
                onClick = {},
                label = {
                    Text(
                        text = "${data.wordsCount} ${
                            stringResource(
                                id = actions.getWordCountLabel(
                                    data.wordsCount
                                )
                            )
                        }",
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
                items(count = words.size, key = { words[it].id }) { index ->
                    val word = words[index]
                    WordLT(
                        word = word,
                        onClick = { navigation.navigateToWordDetailScreen(id = word.id) },
                        onSwipeLeftToRight = { actions.toggleWordBookmark(word) },
                        onSwipeRightToLeft = { navigation.navigateToAddEditWordScreen(word.id) },
                        inverse = data.selectedDictionaryType?.criterium == SearchCriteria.CZECH_MEANING,
                        fontSize = data.fontSize
                    )
                    Divider(color = MaterialTheme.colorScheme.outlineVariant)
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = stringResource(R.string.no_words_found),
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
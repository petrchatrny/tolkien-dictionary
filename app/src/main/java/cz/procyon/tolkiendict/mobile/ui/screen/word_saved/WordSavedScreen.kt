package cz.procyon.tolkiendict.mobile.ui.screen.word_saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import cz.procyon.tolkiendict.mobile.model.relations.WordWithLanguage
import cz.procyon.tolkiendict.mobile.navigation.NavigationRouter
import cz.procyon.tolkiendict.mobile.ui.component.BackArrowScreen
import cz.procyon.tolkiendict.mobile.ui.component.lists.SavedWordLT
import org.koin.androidx.compose.getViewModel

@Composable
fun WordSavedScreen(
    paddingValues: PaddingValues,
    navigation: NavigationRouter,
    viewModel: WordSavedViewModel = getViewModel()
) {
    val words = remember { mutableStateListOf<WordWithLanguage>() }

    viewModel.uiState.let {
        when (it) {
            WordSavedUiState.Default -> {
                viewModel.loadWords()
            }

            is WordSavedUiState.Success -> {
                words.clear()
                words.addAll(it.words)
            }
        }
    }

    BackArrowScreen(
        modifier = Modifier.padding(paddingValues),
        drawFullScreenContent = true,
        appBarTitle = stringResource(R.string.saved)
    ) {
        WordSavedContent(
            paddingValues = it,
            navigation = navigation,
            words = words
        )
    }
}

@Composable
private fun WordSavedContent(
    paddingValues: PaddingValues,
    navigation: NavigationRouter,
    words: List<WordWithLanguage>
) {
    if (words.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            words.forEach {
                item {
                    SavedWordLT(
                        word = it,
                        onClick = { navigation.navigateToWordDetailScreen(it.word.id) }
                    )
                    Divider(color = MaterialTheme.colorScheme.outlineVariant)
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.no_saved_words_yet),
                color = MaterialTheme.colorScheme.primary,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}
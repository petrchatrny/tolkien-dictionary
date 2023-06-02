package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.saved_words

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cz.mendelu.pef.xchatrny.tolkiendictionary.R
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.BackArrowScreen

@Composable
fun SavedWordsScreen(paddingValues: PaddingValues) {
    BackArrowScreen(
        modifier = Modifier.padding(paddingValues),
        drawFullScreenContent = true,
        appBarTitle = stringResource(R.string.saved)
    ) {
        SavedWordsScreenContent(it)
    }
}

@Composable
fun SavedWordsScreenContent(paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        // TODO list of words
    }
}
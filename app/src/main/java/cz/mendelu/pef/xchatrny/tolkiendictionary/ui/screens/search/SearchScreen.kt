package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cz.mendelu.pef.xchatrny.tolkiendictionary.R
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.INavigationRouter
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.SearchTopBar


@Composable
fun SearchScreen(paddingValues: PaddingValues, navigation: INavigationRouter) {
    var query by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.padding(paddingValues),
        topBar = {
            SearchTopBar(
                title = stringResource(id = R.string.app_name),
                query = query,
                onQueryChange = { query = it }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigation.navigateToAddEditWordScreen(null) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }) {
        SearchScreenContent(it)
    }
}

@Composable
fun SearchScreenContent(paddingValues: PaddingValues) {
    // TODO language switch

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        // TODO list of words
    }
}
package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.INavigationRouter

@Composable
fun SearchScreen(paddingValues: PaddingValues, navigation: INavigationRouter) {
    Text(modifier = Modifier.clickable {
        navigation.navigateToSourcesScreen()
    }, text = "SearchScreen")
}
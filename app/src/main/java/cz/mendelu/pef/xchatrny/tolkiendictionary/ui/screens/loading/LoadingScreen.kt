package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.loading

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.INavigationRouter

@Composable
fun LoadingScreen(navigation: INavigationRouter) {
    Text(modifier = Modifier.clickable {
        navigation.navigateToHomeGraph()
    }, text = "LoadingScreen")
}
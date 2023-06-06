package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.xchatrny.tolkiendictionary.R
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.INavigationRouter
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(navigation: INavigationRouter) {
    // TODO real download logic
    LaunchedEffect(key1 = true) {
        delay(3000)
        navigation.navigateToHomeGraph()
    }
    LoadingScreenContent()
}

@Composable
fun LoadingScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.app_name),
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            lineHeight = MaterialTheme.typography.headlineLarge.lineHeight
        )
        Image(
            modifier = Modifier.padding(top = 40.dp, bottom = 40.dp),
            painter = painterResource(id = R.drawable.bilbo),
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = stringResource(R.string.downloading_dictionaries)
        )

        LinearProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    }
}
package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.xchatrny.tolkiendictionary.R
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.INavigationRouter
import org.koin.androidx.compose.getViewModel

@Composable
fun LoadingScreen(navigation: INavigationRouter, viewModel: LoadingViewModel = getViewModel()) {
    var data by remember { mutableStateOf(viewModel.data) }

    viewModel.uiState.let {
        data = viewModel.data

        when (it) {
            LoadingUIState.Loading -> {
                LaunchedEffect(viewModel) {
                    viewModel.tryToDownloadDictionaries()
                }
            }

            LoadingUIState.Downloaded -> {
                LaunchedEffect(viewModel) {
                    navigation.navigateToHomeGraph()
                }
            }

            else -> {}
        }
    }

    LoadingScreenContent(
        data = data,
        state = viewModel.uiState,
        actions = viewModel
    )
}

@Composable
fun LoadingScreenContent(data: LoadingData, state: LoadingUIState, actions: LoadingActions) {
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

        if (state == LoadingUIState.NetworkError) {
            Icon(
                modifier = Modifier
                    .size(160.dp)
                    .padding(top = 40.dp, bottom = 40.dp),
                imageVector = Icons.Default.WifiOff,
                contentDescription = null,
            )
        } else {
            Image(
                modifier = Modifier.padding(top = 40.dp, bottom = 40.dp),
                painter = painterResource(id = R.drawable.bilbo),
                contentDescription = null
            )
        }

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = data.description),
            color = if (data.isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

        if (state == LoadingUIState.NetworkError) {
            Button(
                modifier = Modifier.padding(top = 32.dp),
                onClick = { actions.tryToDownloadDictionaries() }) {
                Text(text = stringResource(R.string.repeat_download))
            }
        } else {
            LinearProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        }
    }
}
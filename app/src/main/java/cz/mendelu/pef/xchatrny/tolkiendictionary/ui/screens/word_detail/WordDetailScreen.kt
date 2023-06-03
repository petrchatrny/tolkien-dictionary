package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.word_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.xchatrny.tolkiendictionary.R
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.INavigationRouter
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.BackArrowScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.lists.TableRow
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.theme.annatar

@Composable
fun WordDetailScreen(navigation: INavigationRouter) {
    BackArrowScreen(
        onBackClick = { navigation.navigateBack() },
        drawFullScreenContent = true
    ) {
        WordDetailScreenContent(it)
    }
}

@Composable
fun WordDetailScreenContent(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(color = MaterialTheme.colorScheme.surfaceColorAtElevation(6.dp))
                        .align(Alignment.TopCenter),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "ciryatur",
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "admirál",
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                        textAlign = TextAlign.Center
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(color = Color.Transparent)
                        .align(Alignment.BottomStart),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FloatingActionButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null)
                    }

                    FloatingActionButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.VolumeUp, contentDescription = null)
                    }

                    FloatingActionButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.BookmarkBorder, contentDescription = null)
                    }

                    FloatingActionButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = null)
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TableRow(title = stringResource(R.string.tengwar)) {
                    Text(
                        modifier = it,
                        text = "aT7ÍE1U6",
                        fontFamily = MaterialTheme.typography.annatar.fontFamily
                    )
                }

                TableRow(title = stringResource(R.string.added_by)) {
                    Text(
                        modifier = it,
                        text = "administrátor"
                    )
                }

                TableRow(title = stringResource(R.string.added_date)) {
                    Text(
                        modifier = it,
                        text = "18. 2. 2023"
                    )
                }

                TableRow(title = stringResource(R.string.source_is)) {
                    Text(
                        modifier = it,
                        text = "Agrenost, 2016"
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.ArrowLeft, contentDescription = null)
                Text(text = "sa")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "eques")
                Icon(imageVector = Icons.Default.ArrowRight, contentDescription = null)
            }
        }
    }

}
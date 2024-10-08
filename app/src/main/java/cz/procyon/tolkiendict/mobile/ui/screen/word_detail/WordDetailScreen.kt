package cz.procyon.tolkiendict.mobile.ui.screen.word_detail

import android.content.Intent
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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import cz.procyon.tolkiendict.mobile.R
import cz.procyon.tolkiendict.mobile.model.relations.WordWithLanguageAndSource
import cz.procyon.tolkiendict.mobile.navigation.NavigationRouter
import cz.procyon.tolkiendict.mobile.ui.component.BackArrowScreen
import cz.procyon.tolkiendict.mobile.ui.component.URILink
import cz.procyon.tolkiendict.mobile.ui.component.lists.TableRow
import cz.procyon.tolkiendict.mobile.ui.theme.tengwar
import cz.procyon.tolkiendict.mobile.util.DateUtils
import org.koin.androidx.compose.getViewModel
import java.util.UUID

@Composable
fun WordDetailScreen(
    navigation: NavigationRouter,
    id: UUID,
    viewModel: WordDetailViewModel = getViewModel()
) {
    var word: WordWithLanguageAndSource? by remember { mutableStateOf(null) }

    viewModel.wordId = id
    viewModel.uiState.let {
        when (it) {
            WordDetailUIState.Default -> {}

            WordDetailUIState.Loading -> {
                viewModel.initData()
            }

            is WordDetailUIState.Success -> {
                word = it.word
            }
        }
    }

    BackArrowScreen(
        onBackClick = { navigation.navigateBack() },
        drawFullScreenContent = true,
        floatingActionButton = {
            word?.let {
                if (!it.word.addedByAdmin) {
                    ExtendedFloatingActionButton(
                        onClick = { navigation.navigateToAddEditWordScreen(it.word.id) },
                        icon = { Icon(Icons.Default.Edit, null) },
                        text = { Text(text = stringResource(id = R.string.edit_word)) },
                    )
                }
            }
        }
    ) {
        WordDetailContent(it, word, viewModel)
    }
}

@Composable
private fun WordDetailContent(
    paddingValues: PaddingValues,
    detailedWord: WordWithLanguageAndSource?,
    actions: WordDetailActions
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        detailedWord?.word?.let { word ->
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
                            text = word.translation,
                            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            lineHeight = MaterialTheme.typography.headlineLarge.lineHeight
                        )

                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = word.czechMeaning,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
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
                        FloatingActionButton(onClick = { actions.copyToClipBoard() }) {
                            Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null)
                        }

                        FloatingActionButton(onClick = { actions.textToSpeech() }) {
                            Icon(imageVector = Icons.Default.VolumeUp, contentDescription = null)
                        }

                        FloatingActionButton(onClick = { actions.toggleBookmark() }) {
                            if (word.isBookmarked) {
                                Icon(
                                    imageVector = Icons.Default.Bookmark,
                                    contentDescription = null
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.BookmarkBorder,
                                    contentDescription = null
                                )
                            }
                        }

                        FloatingActionButton(onClick = {
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, word.translation)
                                type = "text/plain"
                            }

                            val shareIntent = Intent.createChooser(sendIntent, null)
                            startActivity(context, shareIntent, null)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = null
                            )
                        }
                    }
                }

                // TABLE
                Column(
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // LANGUAGE
                    TableRow(title = stringResource(R.string.language_with_colon)) {
                        Text(
                            modifier = it,
                            text = detailedWord.language.name
                        )
                    }

                    // TENGWAR
                    TableRow(title = stringResource(R.string.tengwar)) {
                        Text(
                            modifier = it,
                            text = word.tengwar ?: stringResource(R.string.unknown),
                            fontFamily = if (word.tengwar != null) tengwar.fontFamily
                                         else MaterialTheme.typography.bodyLarge.fontFamily,
                            fontSize = tengwar.fontSize,
                            fontWeight = tengwar.fontWeight,
                            fontStyle = tengwar.fontStyle
                        )
                    }

                    // IS ADDED BY ADMIN
                    TableRow(title = stringResource(R.string.added_by)) {
                        Text(
                            modifier = it,
                            text = if (word.addedByAdmin) stringResource(R.string.administrator)
                                   else stringResource(R.string.user)
                        )
                    }

                    // CREATION DATE
                    TableRow(title = stringResource(R.string.added_date)) {
                        Text(
                            modifier = it,
                            text = DateUtils.getDateString(word.creationDate)
                        )
                    }

                    // SOURCE
                    detailedWord.source?.let { source ->
                        TableRow(title = stringResource(R.string.source_is)) { modifier ->
                            if (source.url == null) {
                                Text(text = source.name)
                            } else {
                                source.url?.let {
                                    URILink(
                                        modifier = modifier,
                                        text = source.name,
                                        uri = it
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
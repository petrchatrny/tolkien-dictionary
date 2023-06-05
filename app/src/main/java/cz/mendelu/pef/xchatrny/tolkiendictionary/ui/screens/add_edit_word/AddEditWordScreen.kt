package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.add_edit_word

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.xchatrny.tolkiendictionary.R
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.INavigationRouter
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.BackArrowScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.fields.SelectField
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.fields.TextField
import org.koin.androidx.compose.getViewModel
import java.util.UUID

@Composable
fun AddEditWordScreen(
    navigation: INavigationRouter,
    id: UUID?,
    viewModel: AddEditWordViewModel = getViewModel()
) {
    viewModel.wordId = id
    var data: AddEditWordData by remember { mutableStateOf(viewModel.data) }

    viewModel.uiState.let {
        when (it) {
            AddEditWordUIState.Default -> {}

            AddEditWordUIState.Loading -> {
                viewModel.initData()
            }

            AddEditWordUIState.DataChanged -> {
                data = viewModel.data
                viewModel.uiState = AddEditWordUIState.Default
            }

            AddEditWordUIState.ValidationError -> {
                data = viewModel.data
                viewModel.uiState = AddEditWordUIState.Default
            }

            AddEditWordUIState.WordCreated -> {
                SubmitForm(
                    state = it,
                    context = LocalContext.current,
                    message = stringResource(R.string.word_created),
                    navigation = navigation
                )
            }

            AddEditWordUIState.WordUpdated -> {
                SubmitForm(
                    state = it,
                    context = LocalContext.current,
                    message = stringResource(R.string.word_updated),
                    navigation = navigation
                )
            }

            AddEditWordUIState.WordDeleted -> {
                SubmitForm(
                    state = it,
                    context = LocalContext.current,
                    message = stringResource(R.string.word_deleted),
                    navigation = navigation
                )
            }
        }
    }

    BackArrowScreen(
        drawFullScreenContent = true,
        appBarTitle = id?.let { stringResource(R.string.edit_word) }
            ?: stringResource(R.string.add_word),
        onBackClick = { navigation.navigateBack() }
    ) {
        AddEditWordContent(
            paddingValues = it,
            isEdit = id != null,
            data = data,
            actions = viewModel
        )
    }
}

@Composable
fun AddEditWordContent(
    paddingValues: PaddingValues,
    isEdit: Boolean,
    data: AddEditWordData,
    actions: AddEditWordActions
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
    ) {
        TextField(
            label = stringResource(R.string.czech_meaning),
            value = data.word.czechMeaning,
            onValueChange = {
                data.word.czechMeaning = it
                actions.onWordChange(data.word)
            },
            error = data.errorCzechMeaning
        )

        SelectField(
            modifier = Modifier.fillMaxWidth(),
            items = data.selectableLanguages,
            selectedItem = data.selectedLanguage,
            onSelectedItemChange = { actions.onLanguageChange(it) },
            label = stringResource(R.string.language),
            error = data.errorLanguage
        )

        TextField(
            label = stringResource(R.string.translation),
            value = data.word.translation,
            onValueChange = {
                data.word.translation = it
                actions.onWordChange(data.word)
            },
            error = data.errorTranslation
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.optional_source),
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        )

        // TODO picker for ChooseSourceDialog

        if (isEdit) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = { actions.deleteWord() }) {
                    Text(
                        text = stringResource(R.string.delete),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                OutlinedButton(
                    onClick = { actions.saveWord(update = true) }) {
                    Text(text = stringResource(R.string.edit))
                }
            }
        } else {
            OutlinedButton(
                modifier = Modifier.align(alignment = Alignment.End),
                onClick = { actions.saveWord(update = false) }) {
                Text(text = stringResource(R.string.add))
            }
        }

    }
}

@Composable
private fun SubmitForm(
    state: AddEditWordUIState,
    context: Context,
    message: String,
    navigation: INavigationRouter
) {
    LaunchedEffect(state) {
        Toast.makeText(
            context, message, Toast.LENGTH_SHORT
        ).show()

        navigation.navigateToHomeGraph()
    }
}
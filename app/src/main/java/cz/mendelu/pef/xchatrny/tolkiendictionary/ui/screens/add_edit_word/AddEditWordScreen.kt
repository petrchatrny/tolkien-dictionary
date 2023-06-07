package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.add_edit_word

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.xchatrny.tolkiendictionary.R
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.INavigationRouter
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.BackArrowScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.LoadingButton
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

            AddEditWordUIState.Saving -> {}

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
            isSaving = viewModel.uiState == AddEditWordUIState.Saving,
            data = data,
            actions = viewModel
        )
    }
}

@Composable
fun AddEditWordContent(
    paddingValues: PaddingValues,
    isEdit: Boolean,
    isSaving: Boolean,
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

        TextField(
            label = stringResource(R.string.translation),
            value = data.word.translation,
            onValueChange = {
                data.word.translation = it
                actions.onWordChange(data.word)
            },
            error = data.errorTranslation
        )

        SelectField(
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(R.string.language),
            items = data.selectableLanguages,
            selectedItem = data.selectedLanguage,
            onSelectedItemChange = { actions.onLanguageChange(it) },
            error = data.errorLanguage
        )

        // TODO picker for ChooseSourceDialog

        Row(
            modifier = Modifier
                .padding(bottom = 48.dp)
                .fillMaxWidth()
                .clickable {},
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Checkbox(
                checked = data.doTranscription,
                onCheckedChange = {
                    data.doTranscription = it
                    actions.onDataChange(data)
                }
            )
            Spacer(Modifier.width(8.dp))

            Text(text = "Provést transkripci překladu do Tengwaru")
        }

        if (isEdit) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LoadingButton(
                    onClick = { actions.deleteWord() },
                    text = stringResource(R.string.delete),
                    color = MaterialTheme.colorScheme.error
                )

                LoadingButton(
                    isLoading = isSaving,
                    onClick = { actions.saveWord(update = true) },
                    text = stringResource(R.string.edit)
                )
            }
        } else {
            LoadingButton(
                modifier = Modifier.align(alignment = Alignment.End),
                isLoading = isSaving,
                onClick = { actions.saveWord(update = false) },
                text = stringResource(R.string.add)
            )
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

        navigation.navigateBack()
    }
}
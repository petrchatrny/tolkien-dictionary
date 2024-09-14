package cz.procyon.tolkiendict.mobile.ui.screen.word_form

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
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
import cz.procyon.tolkiendict.mobile.R
import cz.procyon.tolkiendict.mobile.navigation.NavigationRouter
import cz.procyon.tolkiendict.mobile.ui.component.BackArrowScreen
import cz.procyon.tolkiendict.mobile.ui.component.LoadingButton
import cz.procyon.tolkiendict.mobile.ui.component.fields.PickerField
import cz.procyon.tolkiendict.mobile.ui.component.fields.SelectField
import cz.procyon.tolkiendict.mobile.ui.component.fields.TextField
import cz.procyon.tolkiendict.mobile.ui.dialog.choose_source.ChooseSourceDialog
import org.koin.androidx.compose.getViewModel
import java.util.UUID

@Composable
fun WordFormScreen(
    navigation: NavigationRouter,
    id: UUID?,
    viewModel: WordFormViewModel = getViewModel()
) {
    viewModel.wordId = id
    var data: WordFormData by remember { mutableStateOf(viewModel.data) }

    viewModel.uiState.let {
        when (it) {
            WordFormUiState.Default -> {}

            WordFormUiState.Loading -> {
                viewModel.initWord()
            }

            WordFormUiState.Saving -> {}

            WordFormUiState.DataChanged -> {
                data = viewModel.data
                viewModel.uiState = WordFormUiState.Default
            }

            WordFormUiState.ValidationError -> {
                data = viewModel.data
                viewModel.uiState = WordFormUiState.Default
            }

            WordFormUiState.WordCreated -> {
                SubmitForm(
                    state = it,
                    context = LocalContext.current,
                    message = stringResource(R.string.word_created),
                    navigation = navigation
                )
            }

            WordFormUiState.WordUpdated -> {
                SubmitForm(
                    state = it,
                    context = LocalContext.current,
                    message = stringResource(R.string.word_updated),
                    navigation = navigation
                )
            }

            WordFormUiState.WordDeleted -> {
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
        WordFormContent(
            paddingValues = it,
            isEdit = id != null,
            isSaving = viewModel.uiState == WordFormUiState.Saving,
            data = data,
            actions = viewModel
        )
    }
}

@Composable
private fun WordFormContent(
    paddingValues: PaddingValues,
    isEdit: Boolean,
    isSaving: Boolean,
    data: WordFormData,
    actions: WordFormActions
) {
    var isChooseSourceDialogVisible by remember { mutableStateOf(false) }

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
                actions.onDataChange(data)
            },
            error = data.errorCzechMeaning
        )

        TextField(
            label = stringResource(R.string.translation),
            value = data.word.translation,
            onValueChange = {
                data.word.translation = it
                actions.onDataChange(data)
            },
            error = data.errorTranslation
        )

        SelectField(
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(R.string.language),
            items = data.selectableLanguages,
            selectedItem = data.selectedLanguage,
            onSelectedItemChange = {
                data.selectedLanguage = it
                actions.onDataChange(data)
            },
            error = data.errorLanguage
        )

        PickerField(
            modifier = Modifier.padding(bottom = 16.dp),
            value = data.selectedSource?.value?.name ?: "",
            label = stringResource(R.string.source_optional),
            leadingIcon = Icons.Default.FormatQuote,
            onClick = { isChooseSourceDialogVisible = true },
            onClearClick = {
                data.selectedSource = null
                actions.onDataChange(data)
            }
        )

        Row(
            modifier = Modifier
                .padding(bottom = 48.dp)
                .fillMaxWidth(),
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

            Text(text = stringResource(R.string.do_tengwar_transcription))
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

        if (isChooseSourceDialogVisible) {
            ChooseSourceDialog(
                onSourceChosen = {
                    data.selectedSource = it
                    actions.onDataChange(data)
                },
                onDismiss = { isChooseSourceDialogVisible = false },
                existingSources = data.selectableSources
            )
        }
    }
}

@Composable
private fun SubmitForm(
    state: WordFormUiState,
    context: Context,
    message: String,
    navigation: NavigationRouter
) {
    LaunchedEffect(state) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        navigation.navigateBack()
    }
}
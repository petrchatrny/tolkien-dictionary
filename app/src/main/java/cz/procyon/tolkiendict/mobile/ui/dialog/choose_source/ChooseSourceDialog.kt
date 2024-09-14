package cz.procyon.tolkiendict.mobile.ui.dialog.choose_source

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import cz.procyon.tolkiendict.mobile.R
import cz.procyon.tolkiendict.mobile.model.Source
import cz.procyon.tolkiendict.mobile.ui.component.fields.SelectField
import cz.procyon.tolkiendict.mobile.ui.component.fields.SelectFieldItem
import cz.procyon.tolkiendict.mobile.ui.component.fields.TextField
import org.koin.androidx.compose.getViewModel

private enum class SourcesDialogTab(val stringId: Int) {
    EXISTING(R.string.existing),
    NEW(R.string.brandNew)
}

@Composable
fun ChooseSourceDialog(
    existingSources: List<SelectFieldItem<Source>>,
    onSourceChosen: (source: SelectFieldItem<Source>) -> Unit,
    onDismiss: () -> Unit,
    viewModel: ChooseSourceViewModel = getViewModel()
) {
    viewModel.data.selectableSources = existingSources

    var data by remember { mutableStateOf(viewModel.data) }

    viewModel.uiState.let {
        when (it) {
            ChooseSourceUIState.Default -> {}

            ChooseSourceUIState.DataChanged -> {
                data = viewModel.data
                viewModel.uiState = ChooseSourceUIState.Default
            }

            is ChooseSourceUIState.SourceSaved -> {
                viewModel.data.newSource = Source(name = "", url = "")

                onSourceChosen(SelectFieldItem(it.newSource.name, it.newSource))
                onDismiss()

                viewModel.uiState = ChooseSourceUIState.Default
            }
        }
    }

    ChooseSourceDialogContent(
        onSourceChosen = onSourceChosen,
        onDismiss = onDismiss,
        data = data,
        actions = viewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseSourceDialogContent(
    onSourceChosen: (source: SelectFieldItem<Source>) -> Unit,
    onDismiss: () -> Unit,
    data: ChooseSourceData,
    actions: ChooseSourceActions
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = SourcesDialogTab.values()

    AlertDialog(
        modifier = Modifier
            .padding(28.dp)
            .fillMaxWidth(),
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Column {
                    TabRow(selectedTabIndex = selectedTabIndex) {
                        tabs.forEachIndexed { index, tab ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index },
                                text = {
                                    Text(
                                        text = stringResource(id = tab.stringId),
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            )
                        }
                    }

                    when (tabs[selectedTabIndex]) {
                        SourcesDialogTab.EXISTING -> {
                            SelectField(
                                modifier = Modifier.padding(top = 16.dp),
                                items = data.selectableSources,
                                selectedItem = data.selectedExistingSource,
                                onSelectedItemChange = {
                                    data.selectedExistingSource = it
                                    actions.onDataChange(data)
                                }
                            )
                        }

                        SourcesDialogTab.NEW -> {
                            Column(
                                modifier = Modifier.padding(top = 16.dp),
                            ) {
                                TextField(
                                    label = stringResource(R.string.name),
                                    value = data.newSource.name,
                                    onValueChange = {
                                        data.newSource.name = it
                                        actions.onDataChange(data)
                                    }
                                )

                                TextField(
                                    label = stringResource(id = R.string.url),
                                    value = data.newSource.url,
                                    onValueChange = {
                                        data.newSource.url = it
                                        actions.onDataChange(data)
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    TextButton(
                        modifier = Modifier.align(Alignment.End),
                        onClick = {
                            when (tabs[selectedTabIndex]) {
                                SourcesDialogTab.EXISTING -> {
                                    data.selectedExistingSource?.let {
                                        onSourceChosen(it)
                                        onDismiss()
                                    }
                                }

                                SourcesDialogTab.NEW -> {
                                    actions.saveNewSource()
                                }
                            }
                            data.selectedExistingSource = null
                        },
                    ) {
                        Text("Potvrdit")
                    }
                }
            }
        }
    }
}
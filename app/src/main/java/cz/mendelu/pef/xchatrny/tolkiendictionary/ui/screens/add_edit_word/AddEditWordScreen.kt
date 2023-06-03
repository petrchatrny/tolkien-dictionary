package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.add_edit_word

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.xchatrny.tolkiendictionary.R
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.INavigationRouter
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.BackArrowScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.fields.SelectField
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.fields.SelectFieldItem
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.fields.TextField

@Composable
fun AddEditWordScreen(navigation: INavigationRouter) {
    BackArrowScreen(
        drawFullScreenContent = true,
        appBarTitle = "Přidat upravit slovo", // TODO title
        onBackClick = { navigation.navigateBack() }
    ) {
        AddEditWordContent(it)
    }
}

@Composable
fun AddEditWordContent(
    paddingValues: PaddingValues,
) {
    // TODO values
    val languages = arrayOf(
        SelectFieldItem("Quenyijština", 1),
        SelectFieldItem("Černá řeč", 2),
    )

    var selectedLanguage by remember { mutableStateOf<SelectFieldItem<Int>?>(null) }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
    ) {
        TextField(
            label = stringResource(R.string.czech_meaning),
            value = "",
            onValueChange = {}
        )

        SelectField(
            items = languages,
            selectedItem = selectedLanguage,
            onSelectedItemChange = {
                selectedLanguage = it
            },
            label = stringResource(R.string.language)
        )

        TextField(
            label = stringResource(R.string.translation),
            value = "",
            onValueChange = {}
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.optional_source),
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        )

        TextField(
            modifier = Modifier.padding(top = 8.dp),
            label = stringResource(R.string.source_name),
            value = "",
            onValueChange = {}
        )

        TextField(
            label = stringResource(R.string.url),
            value = "",
            onValueChange = {}
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(R.string.delete),
                    color = MaterialTheme.colorScheme.error
                )
            }

            OutlinedButton(
                onClick = { /*TODO*/ }) {
                Text(text = stringResource(R.string.edit))
            }
        }

    }
}
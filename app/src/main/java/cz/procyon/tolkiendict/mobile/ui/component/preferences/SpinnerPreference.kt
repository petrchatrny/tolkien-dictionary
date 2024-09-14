package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.preferences

import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinnerPreference(
    modifier: Modifier = Modifier,
    title: String,
    items: List<String>,
    selectedItem: String,
    onSelectedItemChanged: (index: Int) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }
    ) {

        TextPreference(
            modifier = modifier.menuAnchor(),
            title = title,
            summary = selectedItem
        )

        ExposedDropdownMenu(
            modifier = Modifier.width(164.dp),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            items.forEachIndexed { index, value ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = value,
                            softWrap = false
                        )
                    },
                    onClick = {
                        isExpanded = false
                        onSelectedItemChanged(index)
                    }
                )
            }
        }
    }
}
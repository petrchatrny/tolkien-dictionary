@file:OptIn(ExperimentalMaterial3Api::class)

package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.fields

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.toSize

class SelectFieldItem<T>(val label: String, val value: T)

@Composable
fun <T> SelectField(
    modifier: Modifier = Modifier,
    items: Array<SelectFieldItem<T>>,
    selectedItem: SelectFieldItem<T>? = null,
    onSelectedItemChange: (item: SelectFieldItem<T>) -> Unit,
    label: String,
    error: Int? = null
) {
    var isExpanded by remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    var sizeOfDropdownInRelationToTextField by remember { mutableStateOf(Size.Zero) }

    if (isPressed) {
        LaunchedEffect(isPressed) {
            isExpanded = !isExpanded
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                sizeOfDropdownInRelationToTextField = coordinates.size.toSize()
            }
    ) {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = selectedItem?.label ?: "",
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            interactionSource = interactionSource,
            isError = error != null,
            supportingText = {
                error?.let {
                    Text(text = stringResource(error))
                }
            },
        )

        DropdownMenu(
            modifier = Modifier
                .width(with(LocalDensity.current) {
                    sizeOfDropdownInRelationToTextField.width.toDp()
                }),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = !isExpanded },
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.label) },
                    onClick = {
                        onSelectedItemChange(item)
                        isExpanded = false
                    }
                )
            }
        }
    }
}

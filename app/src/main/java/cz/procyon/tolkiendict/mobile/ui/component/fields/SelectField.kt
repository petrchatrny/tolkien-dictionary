@file:OptIn(ExperimentalMaterial3Api::class)

package cz.procyon.tolkiendict.mobile.ui.component.fields

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

class SelectFieldItem<T>(val label: String, val value: T)

@Composable
fun <T> SelectField(
    modifier: Modifier = Modifier,
    items: List<SelectFieldItem<T>>,
    selectedItem: SelectFieldItem<T>? = null,
    onSelectedItemChange: (item: SelectFieldItem<T>) -> Unit,
    label: String = "",
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
            .onGloballyPositioned { sizeOfDropdownInRelationToTextField = it.size.toSize() }
            .padding(bottom = 16.dp)
    ) {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = selectedItem?.label ?: "",
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            interactionSource = interactionSource,
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            isError = error != null
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

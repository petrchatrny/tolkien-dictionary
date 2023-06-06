package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextSpinner(
    items: List<String>,
    selectedItem: String,
    onSelectedItemChanged: (index: Int) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }
    ) {
        Row(
            modifier = Modifier.menuAnchor()
        ) {
            Icon(
                modifier = Modifier
                    .rotate(if (isExpanded) 180f else 0f)
                    .size(16.dp),
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = selectedItem,
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }

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
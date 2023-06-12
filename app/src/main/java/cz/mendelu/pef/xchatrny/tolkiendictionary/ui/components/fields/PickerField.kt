package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.fields

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun PickerField(
    modifier: Modifier = Modifier,
    value: String?,
    label: String,
    leadingIcon: ImageVector? = null,
    onClick: () -> Unit,
    onClearClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    if (isPressed) {
        LaunchedEffect(isPressed) {
            onClick()
        }
    }

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value ?: "",
        onValueChange = {},
        label = { Text(text = label) },
        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        trailingIcon = {
            if (value != null && value != "") {
                IconButton(onClick = {
                    onClearClick()
                    focusManager.clearFocus()
                }) {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Outlined.Clear),
                        contentDescription = null
                    )
                }
            }
        },
        readOnly = true,
        interactionSource = interactionSource
    )
}

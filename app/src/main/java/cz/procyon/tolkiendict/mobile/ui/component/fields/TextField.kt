package cz.procyon.tolkiendict.mobile.ui.component.fields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource


@Composable
fun TextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String?,
    onValueChange: (newValue: String) -> Unit,
    error: Int? = null
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        value = value ?: "",
        onValueChange = onValueChange,
        isError = error != null,
        supportingText = {
            error?.let {
                Text(text = stringResource(error))
            }
        },
        trailingIcon = {
            error?.let {
                Icon(imageVector = Icons.Default.Error, contentDescription = null)
            }
        }
    )
}

package cz.procyon.tolkiendict.mobile.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoadingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isLoading: Boolean = false,
    text: String,
    color: Color = MaterialTheme.colorScheme.primary
) {
    OutlinedButton(
        modifier = modifier,
        onClick = {
            if (!isLoading) {
                onClick()
            }
        }
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp),
                strokeWidth = 3.dp
            )
        } else {
            Text(text = text, color = color)
        }
    }
}
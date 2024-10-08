package cz.procyon.tolkiendict.mobile.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler

@Composable
fun URILink(
    modifier: Modifier = Modifier,
    text: String,
    uri: String,
    keepDefaultColor: Boolean = false
) {
    val uriHandler = LocalUriHandler.current

    Text(
        modifier = modifier.clickable {
            try {
                uriHandler.openUri(uri)
            } catch (ignored: Exception) {

            }
        },
        text = text,
        color = if (!keepDefaultColor) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface
    )
}
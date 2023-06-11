package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components

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
    uri: String
) {
    val uriHandler = LocalUriHandler.current

    Text(
        modifier = modifier.clickable {
            uriHandler.openUri(uri)
        },
        text = text,
        color = MaterialTheme.colorScheme.secondary
    )
}
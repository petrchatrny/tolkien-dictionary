package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.software_libraries.SoftwareLibrariesViewModel

@Composable
fun SoftwareLibraryLT(
    library: SoftwareLibrariesViewModel.SoftwareLibrary
) {
    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface),
        shadowElevation = 0.dp,
        tonalElevation = 0.dp,
        headlineContent = { Text(text = library.name) },
        supportingContent = { Text(text = library.packageName) },
        trailingContent = {
            Text(
                text = library.licence.displayName,
                color = MaterialTheme.colorScheme.primary
            )
        }
    )
}
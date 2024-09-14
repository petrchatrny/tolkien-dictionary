package cz.procyon.tolkiendict.mobile.ui.component.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.procyon.tolkiendict.mobile.ui.screen.software_lib.SoftwareLibsViewModel

@Composable
fun SoftwareLibraryLT(
    library: SoftwareLibsViewModel.SoftwareLibrary
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
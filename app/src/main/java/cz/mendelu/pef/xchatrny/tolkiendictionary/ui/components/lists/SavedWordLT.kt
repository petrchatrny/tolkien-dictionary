package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.relations.WordWithLanguage

@Composable
fun SavedWordLT(
    word: WordWithLanguage, onClick: () -> Unit = {}
) {
    ListItem(modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.surface)
        .clickable { onClick() },
        shadowElevation = 0.dp,
        tonalElevation = 0.dp,
        headlineContent = {
            Text(
                text = word.word.translation,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight
            )
        },
        supportingContent = {
            Text(
                text = word.word.czechMeaning,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
            )
        },
        trailingContent = {
            ElevatedAssistChip(
                onClick = {},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Translate, contentDescription = null)
                },
                label = {
                    Text(text = word.language.name)
                }
            )
        }
    )
}
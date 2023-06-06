package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.lists


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.xchatrny.tolkiendictionary.model.Word
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun WordLT(
    word: Word,
    inverse: Boolean = false,
    onClick: () -> Unit = {},
    onSwipeLeftToRight: () -> Unit = {},
    onSwipeRightToLeft: () -> Unit = {}
) {
    val saveAction = SwipeAction(
        icon = rememberVectorPainter(image = Icons.Default.Bookmark),
        background = MaterialTheme.colorScheme.tertiary,
        onSwipe = { onSwipeLeftToRight() }
    )

    val editAction = SwipeAction(
        icon = rememberVectorPainter(image = Icons.Default.Edit),
        background = MaterialTheme.colorScheme.tertiary,
        onSwipe = { onSwipeRightToLeft() }
    )

    val endActions = if (word.addedByAdmin) listOf() else listOf(editAction)

    SwipeableActionsBox(
        backgroundUntilSwipeThreshold = MaterialTheme.colorScheme.outline,
        swipeThreshold = 60.dp,
        startActions = listOf(saveAction),
        endActions = endActions
    ) {
        ListItem(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surface)
                .clickable { onClick() },
            shadowElevation = 0.dp,
            tonalElevation = 0.dp,
            headlineContent = {
                Text(
                    text = if (inverse) word.czechMeaning else word.translation,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight
                )
            },
            supportingContent = {
                Text(
                    text = if (inverse) word.translation else word.czechMeaning,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
                )
            },
            trailingContent = {
                Row {
                    if (word.isBookmarked) {
                        Icon(
                            modifier = Modifier.padding(end = 8.dp),
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = null
                        )
                    }

                    if (!word.addedByAdmin) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = null)
                    }
                }
            }
        )
    }
}
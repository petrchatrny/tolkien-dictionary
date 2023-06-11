/*
 * Modified version of:
 *
 * Copyright (c) 2021 Jamal Mulla
 * MIT License
 * https://github.com/JamalMulla/ComposePrefs3/
 */

package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.preferences

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.URILink

@Composable
fun TextPreference(
    title: String,
    modifier: Modifier = Modifier,
    summary: String? = null,
    uri: String? = null,
    darkenOnDisable: Boolean = false,
    minimalHeight: Boolean = false,
    onClick: () -> Unit = {},
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    enabled: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null
) {
    PrefsListItem(
        text = {
            uri?.let {
                URILink(text = title, uri = it)
            } ?: run {
                Text(title)
            }
        },
        modifier = if (enabled) modifier.clickable { onClick() } else modifier,
        enabled = enabled,
        darkenOnDisable = darkenOnDisable,
        textColor = textColor,
        minimalHeight = minimalHeight,
        icon = leadingIcon,
        secondaryText = {
            summary?.let {
                Text(summary)
            }
        },
        trailing = trailingContent
    )
}
package cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackArrowScreen(
    modifier: Modifier = Modifier,
    appBarTitle: String = "",
    onBackClick: (() -> Unit)? = null,
    drawFullScreenContent: Boolean = false,
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = appBarTitle,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        lineHeight = MaterialTheme.typography.titleLarge.lineHeight,
                        fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                    )
                },
                navigationIcon = {
                    if (onBackClick != null) {
                        IconButton(onClick = { onBackClick() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(6.dp),
                    scrolledContainerColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) {
        if (!drawFullScreenContent) {
            LazyColumn(modifier = Modifier.padding(it)) {
                item {
                    content(it)
                }
            }
        } else {
            content(it)
        }
    }
}

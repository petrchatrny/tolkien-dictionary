package cz.procyon.tolkiendict.mobile.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import cz.procyon.tolkiendict.mobile.R
import java.util.UUID

const val UUID_ARGUMENT_KEY = "uuid"

sealed class Destination(val route: String) {
    object LoadingScreen : Destination("loading")

    object AddEditWordScreen : Destination("add_edit_word/{$UUID_ARGUMENT_KEY}") {
        fun passUUID(uuid: UUID?): String {
            return "add_edit_word/$uuid"
        }
    }

    object WordDetailScreen : Destination("word_detail/{$UUID_ARGUMENT_KEY}") {
        fun passUUID(uuid: UUID): String {
            return "word_detail/$uuid"
        }
    }

    object SourcesScreen : Destination("sources")

    object SoftwareLibrariesScreen : Destination("software_libraries")
}

sealed class BottomNavDestination(
    route: String,
    val title: Int,
    val icon: ImageVector
) : Destination(route) {
    object SearchScreen : BottomNavDestination(
        route = "search",
        title = R.string.search,
        icon = Icons.Outlined.Search
    )

    object SavedWordsScreen : BottomNavDestination(
        route = "saved_words",
        title = R.string.saved,
        icon = Icons.Outlined.Bookmark
    )

    object SettingsScreen : BottomNavDestination(
        route = "settings",
        title = R.string.settings,
        icon = Icons.Outlined.Settings
    )
}
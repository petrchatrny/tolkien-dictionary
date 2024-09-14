package cz.procyon.tolkiendict.mobile.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cz.procyon.tolkiendict.mobile.navigation.BottomNavDestination
import cz.procyon.tolkiendict.mobile.navigation.Destination
import cz.procyon.tolkiendict.mobile.navigation.NavigationRouter
import cz.procyon.tolkiendict.mobile.navigation.NavigationRouterImpl
import cz.procyon.tolkiendict.mobile.navigation.UUID_ARGUMENT_KEY
import cz.procyon.tolkiendict.mobile.ui.screen.word_form.WordFormScreen
import cz.procyon.tolkiendict.mobile.ui.screen.word_saved.WordSavedScreen
import cz.procyon.tolkiendict.mobile.ui.screen.word_search.WordSearchScreen
import cz.procyon.tolkiendict.mobile.ui.screen.settings.SettingsScreen
import cz.procyon.tolkiendict.mobile.ui.screen.software_lib.SoftwareLibsScreen
import cz.procyon.tolkiendict.mobile.ui.screen.sources.SourcesScreen
import cz.procyon.tolkiendict.mobile.ui.screen.word_detail.WordDetailScreen
import java.util.UUID

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    navigation: NavigationRouter = remember { NavigationRouterImpl(navController) },
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomNavDestination.SearchScreen.route
    ) {
        composable(route = BottomNavDestination.SearchScreen.route) {
            WordSearchScreen(paddingValues = paddingValues, navigation = navigation)
        }

        composable(route = BottomNavDestination.SavedWordsScreen.route) {
            WordSavedScreen(paddingValues = paddingValues, navigation = navigation)
        }

        composable(route = BottomNavDestination.SettingsScreen.route) {
            SettingsScreen(paddingValues = paddingValues, navigation = navigation)
        }

        composable(
            route = Destination.WordDetailScreen.route,
            arguments = listOf(
                navArgument(UUID_ARGUMENT_KEY) {
                    type = NavType.StringType
                    defaultValue = ""
                })
        ) {
            // parse UUID
            val uuidString = it.arguments?.getString(UUID_ARGUMENT_KEY)
            if (!uuidString.isNullOrEmpty()) {
                val uuid = UUID.fromString(uuidString)
                WordDetailScreen(navigation = navigation, id = uuid)
            }
        }

        composable(
            route = Destination.AddEditWordScreen.route,
            arguments = listOf(
                navArgument(UUID_ARGUMENT_KEY) {
                    type = NavType.StringType
                    defaultValue = ""
                })
        ) {
            // parse UUID
            val uuidString = it.arguments?.getString(UUID_ARGUMENT_KEY)
            if (!uuidString.isNullOrEmpty() && uuidString != "null") {
                val uuid = UUID.fromString(uuidString)
                WordFormScreen(navigation = navigation, id = uuid)
            } else {
                WordFormScreen(navigation = navigation, id = null)
            }
        }

        composable(Destination.SourcesScreen.route) {
            SourcesScreen(navigation = navigation)
        }

        composable(Destination.SoftwareLibrariesScreen.route) {
            SoftwareLibsScreen(navigation = navigation)
        }
    }
}
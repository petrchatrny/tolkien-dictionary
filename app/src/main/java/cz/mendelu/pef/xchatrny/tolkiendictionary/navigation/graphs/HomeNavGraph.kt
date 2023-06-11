package cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cz.mendelu.pef.xchatrny.tolkiendictionary.R
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.BottomNavDestination
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.Destination
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.INavigationRouter
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.NavigationRouterImpl
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.UUID_ARGUMENT_KEY
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.BackArrowScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.add_edit_word.AddEditWordScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.saved_words.SavedWordsScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.search.SearchScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.settings.SettingsScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.software_libraries.SoftwareLibrariesScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.sources.SourcesScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.word_detail.WordDetailScreen
import java.util.UUID

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    navigation: INavigationRouter = remember { NavigationRouterImpl(navController) },
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomNavDestination.SearchScreen.route
    ) {
        composable(route = BottomNavDestination.SearchScreen.route) {
            SearchScreen(paddingValues = paddingValues, navigation = navigation)
        }

        composable(route = BottomNavDestination.SavedWordsScreen.route) {
            SavedWordsScreen(paddingValues = paddingValues, navigation = navigation)
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
                AddEditWordScreen(navigation = navigation, id = uuid)
            } else {
                AddEditWordScreen(navigation = navigation, id = null)
            }
        }

        composable(Destination.SourcesScreen.route) {
            BackArrowScreen(
                appBarTitle = stringResource(R.string.sources),
                onBackClick = { navController.popBackStack() }) {
                SourcesScreen()
            }
        }

        composable(Destination.SoftwareLibrariesScreen.route) {
            BackArrowScreen(
                appBarTitle = stringResource(R.string.software_libraries),
                onBackClick = { navController.popBackStack() }) {
                SoftwareLibrariesScreen()
            }
        }
    }
}
package cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cz.mendelu.pef.xchatrny.tolkiendictionary.R
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.BottomNavDestination
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.Destination
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.INavigationRouter
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.NavigationRouterImpl
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.components.BackArrowScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.add_edit_word.AddEditWordScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.saved_words.SavedWordsScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.search.SearchScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.settings.SettingsScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.software_libraries.SoftwareLibrariesScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.sources.SourcesScreen
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.word_detail.WordDetailScreen

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
            SavedWordsScreen(paddingValues = paddingValues)
        }

        composable(route = BottomNavDestination.SettingsScreen.route) {
            SettingsScreen(paddingValues = paddingValues)
        }

        composable(Destination.WordDetailScreen.route) {
            WordDetailScreen(navigation = navigation)
        }

        composable(Destination.AddEditWordScreen.route) {
            AddEditWordScreen(navigation = navigation)
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
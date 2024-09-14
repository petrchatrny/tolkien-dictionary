package cz.procyon.tolkiendict.mobile.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.procyon.tolkiendict.mobile.navigation.BottomNavDestination
import cz.procyon.tolkiendict.mobile.navigation.NavigationRouterImpl
import cz.procyon.tolkiendict.mobile.ui.component.BottomNavScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.LOADING
    ) {

        loadingNavGraph(NavigationRouterImpl(navController))

        composable(route = Graph.HOME) {
            BottomNavScreen(
                destinations = listOf(
                    BottomNavDestination.SearchScreen,
                    BottomNavDestination.SavedWordsScreen,
                    BottomNavDestination.SettingsScreen
                )
            ) { controller, paddingValues ->
                run {
                    HomeNavGraph(
                        navController = controller,
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val LOADING = "loading_graph"
}
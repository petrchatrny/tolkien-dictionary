package cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.Destination
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.INavigationRouter
import cz.mendelu.pef.xchatrny.tolkiendictionary.ui.screens.loading.LoadingScreen


fun NavGraphBuilder.loadingNavGraph(
    navigation: INavigationRouter,
) {
    navigation(
        route = Graph.LOADING,
        startDestination = Destination.LoadingScreen.route
    ) {
        composable(route = Destination.LoadingScreen.route) {
            LoadingScreen(navigation = navigation)
        }
    }
}
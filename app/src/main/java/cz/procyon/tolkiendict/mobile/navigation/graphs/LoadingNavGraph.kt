package cz.procyon.tolkiendict.mobile.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import cz.procyon.tolkiendict.mobile.navigation.Destination
import cz.procyon.tolkiendict.mobile.navigation.NavigationRouter
import cz.procyon.tolkiendict.mobile.ui.screen.loading.LoadingScreen

fun NavGraphBuilder.loadingNavGraph(
    navigation: NavigationRouter,
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
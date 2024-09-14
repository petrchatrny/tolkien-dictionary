package cz.procyon.tolkiendict.mobile.navigation

import androidx.navigation.NavController
import cz.procyon.tolkiendict.mobile.navigation.graphs.Graph
import java.util.UUID

class NavigationRouterImpl(
    private val navController: NavController
) : NavigationRouter {

    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun navigateToHomeGraph() {
        navController.popBackStack()
        navController.navigate(Graph.HOME)
    }

    override fun navigateToAddEditWordScreen(id: UUID?) {
        navController.navigate(Destination.AddEditWordScreen.passUUID(id))
    }

    override fun navigateToWordDetailScreen(id: UUID) {
        navController.navigate(Destination.WordDetailScreen.passUUID(id))
    }

    override fun navigateToSourcesScreen() {
        navController.navigate(Destination.SourcesScreen.route)
    }

    override fun navigateToSoftwareLibrariesScreen() {
        navController.navigate(Destination.SoftwareLibrariesScreen.route)
    }

    override fun getNavController(): NavController {
        return navController
    }

}
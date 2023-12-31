package cz.mendelu.pef.xchatrny.tolkiendictionary.navigation

import androidx.navigation.NavController
import cz.mendelu.pef.xchatrny.tolkiendictionary.navigation.graphs.Graph
import java.util.UUID

class NavigationRouterImpl(
    private val navController: NavController
) : INavigationRouter {
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
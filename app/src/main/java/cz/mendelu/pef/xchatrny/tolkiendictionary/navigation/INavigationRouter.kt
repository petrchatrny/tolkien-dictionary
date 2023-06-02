package cz.mendelu.pef.xchatrny.tolkiendictionary.navigation

import androidx.navigation.NavController
import java.util.UUID

interface INavigationRouter {
    fun navigateBack()
    fun navigateToHomeGraph()
    fun navigateToAddEditWordScreen(id: UUID?)
    fun navigateToWordDetailScreen(id: UUID)
    fun navigateToSourcesScreen()
    fun navigateToSoftwareLibrariesScreen()
    fun getNavController(): NavController
}

package cz.procyon.tolkiendict.mobile.navigation

import androidx.navigation.NavController
import java.util.UUID

interface NavigationRouter {
    fun navigateBack()
    fun navigateToHomeGraph()
    fun navigateToAddEditWordScreen(id: UUID?)
    fun navigateToWordDetailScreen(id: UUID)
    fun navigateToSourcesScreen()
    fun navigateToSoftwareLibrariesScreen()
    fun getNavController(): NavController
}

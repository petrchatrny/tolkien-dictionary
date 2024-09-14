package cz.procyon.tolkiendict.mobile.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cz.procyon.tolkiendict.mobile.navigation.BottomNavDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavScreen(
    destinations: List<BottomNavDestination>,
    navGraph: @Composable (controller: NavHostController, paddingValues: PaddingValues) -> Unit
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                destinations = destinations
            )
        }
    ) {
        navGraph(
            controller = navController,
            paddingValues = it
        )
    }
}


@Composable
fun BottomBar(navController: NavHostController, destinations: List<BottomNavDestination>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // if true, nav will be displayed
    val bottomBarDestination = destinations.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            destinations.forEach { destination ->
                AddItem(
                    destination = destination,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}


@Composable
fun RowScope.AddItem(
    destination: BottomNavDestination,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = { Text(text = stringResource(id = destination.title)) },
        icon = { Icon(imageVector = destination.icon, contentDescription = null) },
        selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true,
        onClick = {
            navController.popBackStack()
            navController.navigate(destination.route)
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            selectedTextColor = MaterialTheme.colorScheme.onSurface,
            indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    )
}
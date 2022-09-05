package com.isa.githubx.page.home

import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.isa.githubx.uikit.theme.MaterialColors

@Composable
fun AppBottomNavigation(navController: NavController, items: List<HomeScreenItem>) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            val name = stringResource(id = screen.titleRes)
            BottomNavigationItem(
                selectedContentColor = MaterialColors.primary,
                unselectedContentColor = MaterialColors.onBackground,
                icon = {
                    Icon(painter = painterResource(id = screen.iconRes), contentDescription = name)
                },
                label = { Text(text = name, style = MaterialTheme.typography.overline, maxLines = 1, softWrap = false) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier.background(MaterialColors.background)
            )
        }
    }
}

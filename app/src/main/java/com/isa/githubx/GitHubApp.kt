package com.isa.githubx

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.isa.githubx.page.home.HomeScreen
import com.isa.githubx.page.home.HomeScreenItem
import com.isa.githubx.page.home.RouteName
import com.isa.githubx.page.login.LOGIN_ROUTE
import com.isa.githubx.page.login.LoginScreen
import com.isa.githubx.page.search.SearchScreen

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun gitHubApp() {
    val navController: NavHostController = rememberNavController()

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = HomeScreenItem.Home.route
        ) {
            //首页
            composable(route = RouteName.HOME) {
                HomeScreen()
            }

            composable(LOGIN_ROUTE) {
                LoginScreen(
                    navController = navController,
                    header = {},
                    footer = {}
                )
            }

            composable(HomeScreenItem.Search.route) { navBackStackEntry ->
//                NavigationItemWrapper(navBackStackEntry, navController, navigateToLogin) {
                SearchScreen(
                    navController = navController
                )
            }
        }
    }
}

internal const val LOGIN_KEY_IS_SIGNED_IN = "isSignedIn"

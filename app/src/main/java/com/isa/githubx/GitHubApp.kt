package com.isa.githubx

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.isa.githubx.page.home.HomeScreen
import com.isa.githubx.page.home.HomeScreenItem
import com.isa.githubx.page.home.RouteName
import com.isa.githubx.page.webview.KEY_URL
import com.isa.githubx.page.webview.WEBVIEW_ROUTE
import com.isa.githubx.page.webview.WebViewScreen
import com.isa.githubx.page.login.LOGIN_ROUTE
import com.isa.githubx.page.login.LoginScreen
import com.isa.githubx.page.search.SearchScreen
import com.isa.githubx.page.webview.webRoute

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
                HomeScreen(
                    toWebView = { url ->
                        navController.navigate(
                            route = webRoute(url = url)
                        )
                    }
                )
            }

            composable(LOGIN_ROUTE) {
                LoginScreen(
                    navController = navController,
                    header = {},
                    footer = {}
                )
            }

            composable(
                route = WEBVIEW_ROUTE,
                arguments = listOf(navArgument(KEY_URL) {
                    type = NavType.StringType
                }
                )
            ) { navBackStackEntry ->
                val url = navBackStackEntry.arguments?.getString(
                    KEY_URL
                )

                url?.let {
                    WebViewScreen(
                        navController = navController,
                        url = it
                    )
                }
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

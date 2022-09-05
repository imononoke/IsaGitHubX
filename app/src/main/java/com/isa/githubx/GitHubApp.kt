package com.isa.githubx

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.isa.githubx.page.login.LOGIN_ROUTE
import com.isa.githubx.page.login.LoginScreen
import com.isa.githubx.page.home.AppBottomNavigation
import com.isa.githubx.page.home.HomePage
import com.isa.githubx.page.home.HomeScreenItem
import com.isa.githubx.page.home.RouteName
import com.isa.githubx.page.home.repo.KEY_REPO_USERNAME
import com.isa.githubx.page.home.repo.REPO_DETAIL_ROUTE
import com.isa.githubx.page.home.repo.RepoPage
import com.isa.githubx.page.profile.ProfileScreen
import com.isa.githubx.page.search.SearchScreen

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun gitHubApp() {
    val navController: NavHostController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        bottomBar = {
            AppBottomNavigation(navController, HomeScreenItem.values().toList())
        },
        content = {
            NavHost(
                modifier = Modifier.background(MaterialTheme.colors.background),
                navController = navController,
                startDestination = HomeScreenItem.Home.route
            ) {
                //首页
                composable(route = RouteName.HOME) {
                    HomePage(
                        navController = navController,
                        scaffoldState = scaffoldState
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
                    route = REPO_DETAIL_ROUTE,
                    arguments = listOf(navArgument("username") {
                        type = NavType.StringType }
                    )
                ) { navBackStackEntry ->
                    val username = navBackStackEntry.arguments?.getString(KEY_REPO_USERNAME)
                    username?.let { it ->
                        RepoPage(
                            navController = navController,
                            username = it
                        )
                    }
                }

                composable(HomeScreenItem.Search.route) { navBackStackEntry ->
//                NavigationItemWrapper(navBackStackEntry, navController, navigateToLogin) {
                    SearchScreen(
                        navController = navController
                    )
                }

                composable(HomeScreenItem.Profile.route) { navBackStackEntry ->
//                NavigationItemWrapper(navBackStackEntry, navController, navigateToLogin) {
                    ProfileScreen(
                        toLogout = {
                        }
                    )
//                }
                }
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = scaffoldState.snackbarHostState
            ) { data ->
                println("actionLabel = ${data.actionLabel}")
//                AppSnackBar(data = data)
            }
        }
    )

//    MaterialTheme {
//        NavHost(
//            navController = navController,
//            startDestination = HomeScreenItem.Home.route
//        ) {
//            composable(
//                route = HomeScreenItem.Home.route
//            ) {
//                HomeScreen(
//                    navigateToLogin = {
//                        navController.navigate(LOGIN_ROUTE)
//                    },
//                    navigateToSearch = {
//                        navController.navigate(SEARCH_ROUTE)
//                    }
//                )
//            }
//
//            composable(LOGIN_ROUTE) {
//                LoginScreen(
//                    navController = navController,
//                    header = {},
//                    footer = {}
//                )
//            }
//
//            composable(SEARCH_ROUTE) {
//                SearchScreen(
//                    navController = navController
//                )
//            }
//        }
//    }
}

internal const val LOGIN_KEY_IS_SIGNED_IN = "isSignedIn"
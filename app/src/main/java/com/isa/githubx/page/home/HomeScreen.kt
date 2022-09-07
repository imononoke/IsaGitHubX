package com.isa.githubx.page.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.isa.githubx.page.home.repos.ReposScreen
import com.isa.githubx.page.home.users.UsersPage
import com.isa.githubx.page.profile.ProfileScreen
import com.isa.githubx.page.search.SearchScreen
import com.isa.githubx.page.webview.KEY_URL
import com.isa.githubx.page.webview.WEBVIEW_ROUTE
import com.isa.githubx.page.webview.WebViewScreen
import com.isa.githubx.page.webview.webRoute
import com.isa.githubx.uikit.widgets.SearchButton
import com.isa.githubx.uikit.widgets.TextTabBar
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomeScreen(
    toWebView: (url: String) -> Unit
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            AppBottomNavigation(navController, HomeScreenItem.values().toList())
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.background(MaterialTheme.colors.background),
            navController = navController,
            startDestination = HomeScreenItem.Home.route
        ) {
            //首页
            composable(route = RouteName.HOME) {
                HomePage(
                    navController = navController,
                    toSearch = {},
                    toWebView = toWebView
                )
            }

            composable(HomeScreenItem.Search.route) { navBackStackEntry ->
                SearchScreen(
                    navController = navController
                )
            }

            composable(HomeScreenItem.Profile.route) { navBackStackEntry ->
//                NavigationItemWrapper(navBackStackEntry, navController, navigateToLogin) {
                ProfileScreen(
                    toLogout = {}
                )
//                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun HomePage(
    navController: NavHostController,
    toSearch: () -> Unit,
    toWebView: (url: String) -> Unit
) {
    val scopeState = rememberCoroutineScope()
    val viewModel: HomeViewModel = hiltViewModel()
    val titles = viewModel.viewState.titles

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            onClick = toSearch,
        )

        val pagerState = rememberPagerState(
            initialPage = 0
        )

        TextTabBar(
            index = pagerState.currentPage,
            tabTexts = titles,
            onTabSelected = { index ->
                scopeState.launch {
                    pagerState.scrollToPage(index)
                }
            }
        )

        HorizontalPager(
            count = titles.size,
            state = pagerState,
            modifier = Modifier.padding(horizontal = 12.dp)
        ) { page ->
            when (page) {
                0 -> ReposScreen(
                    navController, toWebView
                )
                1 -> UsersPage(
                    navController, toWebView
                )
            }
        }
    }
}

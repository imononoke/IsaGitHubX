package com.isa.githubx.page.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.isa.githubx.LOGIN_KEY_IS_SIGNED_IN
import com.isa.githubx.page.home.repo.repoDetailRoute
import com.isa.githubx.page.home.users.UsersPage
import com.isa.githubx.page.profile.ProfileScreen
import com.isa.githubx.page.search.SearchScreen
import com.isa.githubx.uikit.widgets.TextTabBar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomeScreen(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    Scaffold(
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
                        scaffoldState = scaffoldState,
                    )
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
                        toLogout = {}
                    )
//                }
                }
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun HomePage(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
) {
    val scopeState = rememberCoroutineScope()
    val viewModel: HomeViewModel = hiltViewModel()
    val titles = viewModel.viewState.titles

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
                0 -> ReposPage(navController, scaffoldState)
                1 -> UsersPage(navController, scaffoldState)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
internal fun ReposPage(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
) {
    val viewModel: HomeViewModel = hiltViewModel()

    RepoContent(
        viewState = viewModel.viewState,
        onEvent = viewModel::onEvent
    )

    LaunchedEffect(viewModel.events) {
        viewModel.events.collect {
            with (it) {
                when (this) {
                    OneShotEvent.ToSearch -> {}
                    is OneShotEvent.ToRepoDetail -> {
                        navController.navigate(
                            route = repoDetailRoute(username)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NavigationItemWrapper(
    navBackStackEntry: NavBackStackEntry,
    navController: NavHostController,
    navigateToLogin: () -> Unit,
    item: @Composable () -> Unit,
) {
    val isSignedIn = navBackStackEntry.savedStateHandle
        .getLiveData<Boolean>(LOGIN_KEY_IS_SIGNED_IN).observeAsState()
//    val returnFromSignIn = rememberSaveable { mutableStateOf(false) }
    if (isSignedIn.value == true) {
        item()
    } else {
        navigateToLogin()

//        if (returnFromSignIn.value) {
//            navController.navigate(HomeScreenItem.Home.route)
//            returnFromSignIn.value = false
//        } else {
//            navigateToLogin()
//            returnFromSignIn.value = true
//        }
    }
}
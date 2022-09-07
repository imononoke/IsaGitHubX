package com.isa.githubx.page.home.repos

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.isa.githubx.page.home.HomeViewModel
import com.isa.githubx.page.home.OneShotEvent
import kotlinx.coroutines.flow.collect

@RequiresApi(Build.VERSION_CODES.N)
@Composable
internal fun ReposScreen(
    navController: NavHostController,
    toWebView: (url: String) -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()

    ReposContent(
        viewState = viewModel.viewState,
        onEvent = viewModel::onEvent
    )

    LaunchedEffect(viewModel.events) {
        viewModel.events.collect {
            with (it) {
                when (this) {
                    OneShotEvent.ToSearch -> {}
                    is OneShotEvent.ToShowDetail -> {
                        toWebView(url)
                    }
                }
            }
        }
    }
}

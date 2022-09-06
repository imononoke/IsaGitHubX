package com.isa.githubx.page.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
internal fun ContentLayout(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit,
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onRefresh,
        modifier = Modifier.fillMaxSize(),
        content = content
    )
}

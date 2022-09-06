package com.isa.githubx.page.home.users

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.isa.githubx.model.UserInfo
import com.isa.githubx.page.home.ContentLayout
import com.isa.githubx.uikit.theme.Dimens
import com.isa.githubx.uikit.theme.MaterialColors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

@RequiresApi(Build.VERSION_CODES.N)
@Composable
internal fun UsersPage(
    navController: NavHostController
) {
    val viewModel: UsersViewModel = hiltViewModel()

    UsersContent(
        viewState = viewModel.viewState,
        onEvent = viewModel::onEvent
    )

    LaunchedEffect(viewModel.events) {
        viewModel.events.collect {
            with (it) {
                when (this) {
                    OneShotEvent.ToSearch -> TODO()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
internal fun UsersContent(
    viewState: ViewState,
    onEvent: (ViewEvent) -> Unit,
) {
    ContentLayout(
        isRefreshing = viewState.isRefreshing,
        onRefresh = {
            onEvent(ViewEvent.Refresh)
        },
        content = {
            viewState.pagingData?.let {
                ContentItems(
                    modifier = Modifier.fillMaxSize(),
                    data = it
                )
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
private fun ContentItems(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    data: Flow<PagingData<UserInfo>>
) {
    val items = data.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = PaddingValues(
            horizontal = Dimens.ScreenEdgePadding,
            vertical = 20.dp
        )
    ) {
        itemsIndexed(items) { index, entity ->
            entity?.let {
                Item(it)
            }
        }
    }
}

@Composable
private fun Item(
    entity: UserInfo
) {
    Row(
        modifier = Modifier
            .placeholder(false)
            .fillMaxWidth()
            .background(color = MaterialColors.background)
            .padding(5.dp),
        verticalAlignment = Alignment.Top
    ) {
        entity.avatar_url?.let { avatarUrl ->
            val painter = rememberImagePainter(
                data = avatarUrl
            )

            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(32.dp)
                    .placeholder(
                        visible = false,
                        color = MaterialColors.onBackground
                    )
                    .clip(shape = MaterialTheme.shapes.medium),
                contentDescription = null,
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            entity.login?.let {
                Text(
                    text = it,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialColors.primary,
                    style = MaterialTheme.typography.h6
                )
            }
        }

        // divider
        Column {
            Spacer(modifier = Modifier.height(24.dp))
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

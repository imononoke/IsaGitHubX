package com.isa.githubx.page.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.isa.githubx.model.RepoEntity
import com.isa.githubx.uikit.Icons
import com.isa.githubx.uikit.theme.Dimens
import com.isa.githubx.uikit.theme.MaterialColors
import kotlinx.coroutines.flow.Flow

@RequiresApi(Build.VERSION_CODES.N)
@Composable
internal fun RepoContent(
    viewState: ViewState,
    onEvent: (ViewEvent) -> Unit,
) {
//    val data = viewState.pagingData?.collectAsLazyPagingItems()
//    val listState = if (data?.itemCount > 0) viewState.listState else LazyListState()

//    RefreshList(
//        lazyPagingItems = data,
//        listState = listState
//    ) {
//        itemsIndexed(data) { _, item ->
//            item?.let {
//                Item(
//                    entity = it,
//                    onEvent = onEvent
//                )
//            }
//        }
//    }

    ContentLayout(
        isRefreshing = viewState.isRefreshing,
        onRefresh = {
            onEvent(ViewEvent.Refresh)
        },
        content = {
            viewState.pagingData?.let {
                ContentItems(
                    modifier = Modifier.fillMaxSize(),
                    data = it,
                    onEvent = onEvent
                )
            }
        }
    )
}

@Composable
private fun ContentLayout(
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

@RequiresApi(Build.VERSION_CODES.N)
@Composable
private fun ContentItems(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    data: Flow<PagingData<RepoEntity>>,
    onEvent: (ViewEvent) -> Unit,
) {
    val items = data.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = PaddingValues(
            horizontal = Dimens.ScreenEdgePadding,
            vertical = 6.dp
        )
    ) {
        itemsIndexed(items) { _, entity ->
            entity?.let {
                Item(
                    entity = it,
                    onEvent = onEvent
                )
            }
        }
    }
}

@Composable
private fun Item(
    entity: RepoEntity,
    onEvent: (ViewEvent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .clickable {
                entity.owner.login?.let {
                    onEvent(ViewEvent.ToRepoDetail(it))
                }
            }
    ) {
        Row(
            modifier = Modifier
                .placeholder(false)
                .fillMaxWidth()
                .background(color = MaterialColors.background),
            verticalAlignment = Alignment.Top
        ) {
            entity.owner.avatar_url?.let { avatarUrl ->
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
                entity.full_name?.let {
                    Text(
                        text = it,
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialColors.primary,
                        style = MaterialTheme.typography.h6
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                entity.name?.let {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = it,
                        style = MaterialTheme.typography.subtitle1
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                entity.description?.let {
                    Text(
                        modifier = Modifier.fillMaxWidth()
                            .height(40.dp),
                        text = it,
                        style = MaterialTheme.typography.subtitle2
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(Icons.Star),
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = entity.stargazers_count.toString(),
                        style = MaterialTheme.typography.body1
                    )
                }
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

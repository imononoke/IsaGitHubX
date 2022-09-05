package com.isa.githubx.page.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.isa.githubx.common.simplePager
import com.isa.githubx.model.RepoEntity
import com.isa.githubx.network.ApiService
import com.isa.githubx.repository.SearchRepository
import com.isa.githubx.uikit.widgets.TabTitle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
//    private val apiService: ApiService
) : ViewModel() {

//    private val data by lazy {
//        simplePager {
//            apiService.searchRepos("Android", page = it, perPage = 20)
//        }.cachedIn(viewModelScope)
//    }

    var viewState by mutableStateOf(ViewState())
        private set

    private val _events = Channel<OneShotEvent>(Channel.BUFFERED)
    val events: Flow<OneShotEvent> = _events.receiveAsFlow()

    init {
        val data = getSearchPagingData("Android")

        updateState(
            pagingData = data,
            isRefreshing = false
        )

        viewState = viewState.copy(
            titles = listOf(
                TabTitle(101, "Repos"),
                TabTitle(102, "Users")
            )
        )
    }

    private fun getSearchPagingData(keywords: String) : Flow<PagingData<RepoEntity>> =
        searchRepository.getSearchPagingData(keywords)
            .cachedIn(viewModelScope)

    private fun updateState(
        titles: List<TabTitle> = viewState.titles,
        pagingData: Flow<PagingData<RepoEntity>>? = viewState.pagingData,
        isRefreshing: Boolean = viewState.isRefreshing,
    ) {
        viewState = ViewState(
            titles = titles,
            pagingData = pagingData,
            isRefreshing = isRefreshing
        )
    }

    fun onEvent(event: ViewEvent) = with(event) {
        when (this) {
            ViewEvent.ToSearch -> {
                emit(OneShotEvent.ToSearch)
            }

            ViewEvent.Refresh -> {
                viewModelScope.launch {
                    viewState = viewState.copy(isRefreshing = true)
                    // todo
                }
            }

            is ViewEvent.ToRepoDetail ->
                emit(OneShotEvent.ToRepoDetail(username))
        }
    }

    private fun emit(event: OneShotEvent) {
        viewModelScope.launch {
            _events.send(event)
        }
    }
}

internal data class ViewState(
    val titles: List<TabTitle> = emptyList(),
    val pagingData: Flow<PagingData<RepoEntity>>? = null,
    val isRefreshing: Boolean = false,
    val listState: LazyListState = LazyListState(),
)

internal sealed interface ViewEvent {
    object ToSearch : ViewEvent
    object Refresh : ViewEvent
    data class ToRepoDetail(val username: String) : ViewEvent
}

internal sealed interface OneShotEvent {
    object ToSearch : OneShotEvent
    data class ToRepoDetail(val username: String) : OneShotEvent
}

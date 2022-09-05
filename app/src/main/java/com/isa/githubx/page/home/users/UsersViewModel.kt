package com.isa.githubx.page.home.users

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.isa.githubx.model.UserInfo
import com.isa.githubx.repository.GetUsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class UsersViewModel @Inject constructor(
    private val getUsersRepository: GetUsersRepository
) : ViewModel() {

    var viewState by mutableStateOf(ViewState())
        private set

    private val _events = Channel<OneShotEvent>(Channel.BUFFERED)
    val events: Flow<OneShotEvent> = _events.receiveAsFlow()

    init {
        val data = getUsersRepository()
        updateState(
            pagingData = data,
            isRefreshing = false
        )
    }

    private fun getUsers() : Flow<PagingData<UserInfo>> =
        getUsersRepository().cachedIn(viewModelScope)

    private fun updateState(
        pagingData: Flow<PagingData<UserInfo>>? = viewState.pagingData,
        isRefreshing: Boolean = viewState.isRefreshing,
    ) {
        viewState = ViewState(
            pagingData = pagingData,
            isRefreshing = isRefreshing
        )
    }

    fun onEvent(event: ViewEvent) : Unit = with(event) {
        when (this) {
            ViewEvent.Refresh -> {}
            ViewEvent.ToSearch -> {}
        }
    }

    private fun emit(event: OneShotEvent) {
        viewModelScope.launch {
            _events.send(event)
        }
    }
}

internal data class ViewState(
    val pagingData : Flow<PagingData<UserInfo>>? = null,
    val isRefreshing: Boolean = false,
)

internal sealed interface ViewEvent {
    object ToSearch : ViewEvent
    object Refresh : ViewEvent
}

internal sealed interface OneShotEvent {
    object ToSearch : OneShotEvent
}

package com.isa.githubx.page.profile

import androidx.lifecycle.ViewModel
import com.isa.githubx.model.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    init {

    }

    fun onEvent(): Unit {

    }

    private fun emit() {

    }
}

data class ProfileViewState(
    val isLogged: Boolean = false,
    val userInfo: UserInfo? = null,
    val showLogout: Boolean = false
)

internal sealed interface ViewEvent {
    object LogOut : ViewEvent
}

internal sealed interface OneShotEvent {
    object LogOut : OneShotEvent
}

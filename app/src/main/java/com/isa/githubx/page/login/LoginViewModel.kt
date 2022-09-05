package com.isa.githubx.page.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    ) : ViewModel() {

}

data class LoginViewState(
    val account: String = "",
    val password: String = "",
    val isLogged: Boolean = false
)

internal sealed class LoginViewEvent {
    object OnBack : LoginViewEvent()
    data class ShowErrorMessage(val message: String) : LoginViewEvent()
}

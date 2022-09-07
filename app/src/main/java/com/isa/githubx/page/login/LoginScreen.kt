package com.isa.githubx.page.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.isa.githubx.uikit.widgets.Title
import com.isa.githubx.R

@Composable
fun LoginScreen(
    navController: NavHostController,
    header: @Composable () -> Unit,
    footer: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // header
        Box(modifier = Modifier.align(Alignment.End)) {
            header()
        }

        // title
        Title(
            text = stringResource(R.string.login_page_title),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

private const val LOGIN_ROUTE_PREFIX = "login"
internal const val KEY_USERNAME = "username"
const val LOGIN_ROUTE =
    "$LOGIN_ROUTE_PREFIX?$KEY_USERNAME={$KEY_USERNAME}"

fun loginRoute(
    username: String
) = "$LOGIN_ROUTE?$KEY_USERNAME=$username"

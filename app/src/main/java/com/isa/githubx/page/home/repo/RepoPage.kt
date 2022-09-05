package com.isa.githubx.page.home.repo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.isa.githubx.uikit.widgets.Title

@Composable
fun RepoPage(
    navController: NavHostController,
    username: String
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // title
        Title(
            text = username,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

private const val REPO_DETAIL_ROUTE_PREFIX = "repoDetail/"
internal const val KEY_REPO_USERNAME = "username"
const val REPO_DETAIL_ROUTE = "$REPO_DETAIL_ROUTE_PREFIX{$KEY_REPO_USERNAME}"

fun repoDetailRoute(
    username: String
) = "$REPO_DETAIL_ROUTE_PREFIX$username"

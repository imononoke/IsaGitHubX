package com.isa.githubx.page.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.isa.githubx.R

enum class HomeScreenItem(
    val route: String,
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
) {
    Home(HomeDestinations.Home, R.string.home, R.drawable.ic_navigation_home),
    Search(HomeDestinations.Search, R.string.search, R.drawable.ic_navigation_explore),
    Profile(HomeDestinations.Profile, R.string.profile, R.drawable.ic_navigation_profile)
}

object HomeDestinations {
    const val Home = "home"
    const val Search = "search"
    const val Profile = "profile"
}

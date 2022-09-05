package com.isa.githubx.repository

import com.isa.githubx.BuildConfig
import com.isa.githubx.model.UserInfo
import com.isa.githubx.network.ApiCall
import javax.inject.Inject

class LoginRepository @Inject constructor() {

    suspend fun login(): UserInfo {
        val auth = "token ${BuildConfig.USER_ACCESS_TOKEN}"
        return ApiCall.retrofit.fetchUserOwner(auth)
    }
}

package com.isa.githubx.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.isa.githubx.model.UserInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersRepository @Inject constructor() {

    private val pageSize = 25

    operator fun invoke() : Flow<PagingData<UserInfo>> {
        return Pager(
            config = PagingConfig(pageSize),
            pagingSourceFactory = {
                GetUsersPagingSource()
            }
        ).flow
    }
}
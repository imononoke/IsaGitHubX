package com.isa.githubx.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.isa.githubx.model.UserInfo
import com.isa.githubx.network.ApiCall

class GetUsersPagingSource(
): PagingSource<Int, UserInfo>() {
    override fun getRefreshKey(state: PagingState<Int, UserInfo>): Int? =
        null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserInfo> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val items = ApiCall.retrofit.getUsers(page, pageSize)
            val preKey = if (page > 1) page - 1 else null
            val nextKey = if (items.isNotEmpty()) page + 1 else null
            LoadResult.Page(items, preKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
package com.isa.githubx.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.isa.githubx.model.RepoEntity
import com.isa.githubx.network.ApiCall

class SearchRepoPagingSource(
    private val keywords: String
): PagingSource<Int, RepoEntity>() {
    override fun getRefreshKey(state: PagingState<Int, RepoEntity>): Int? =
        null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoEntity> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val rspRepository = ApiCall.retrofit.searchRepos(keywords, page, pageSize)
            val items = rspRepository.items
            val preKey = if (page > 1) page - 1 else null
            val nextKey = if (items.isNotEmpty()) page + 1 else null
            LoadResult.Page(items, preKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
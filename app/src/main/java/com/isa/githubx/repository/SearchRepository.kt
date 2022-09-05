package com.isa.githubx.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.isa.githubx.model.RepoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepository @Inject constructor() {

    private val pageSize = 25

    fun getSearchPagingData(keywords: String) : Flow<PagingData<RepoEntity>> {
        return Pager(
            config = PagingConfig(pageSize),
            pagingSourceFactory = {
                SearchRepoPagingSource(keywords)
            }
        ).flow
    }
}

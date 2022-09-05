package com.isa.githubx.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.isa.githubx.MyApp
import com.isa.githubx.model.BasicResp
import com.isa.githubx.model.HttpResult
import com.isa.githubx.model.ListWrapper
import com.isa.githubx.network.ApiCall
import com.isa.githubx.utils.NetworkUtils
import com.isa.githubx.utils.showToast
import kotlinx.coroutines.flow.Flow

fun <T : Any> ViewModel.simplePager(
    config: CommonPagingConfig = CommonPagingConfig(),
    callAction: suspend (page: Int) -> BasicResp<ListWrapper<T>>
): Flow<PagingData<T>> {
    return pager(config, 0) {
        val page = it.key ?: 0
        val response = try {
            HttpResult.Success(callAction.invoke(page))
        } catch (e: Exception) {
            val context = MyApp.context
            if (NetworkUtils.isNetworkAvailable(context).not()) {
                showToast("network_not_available")
            } else {
                showToast("network_not_available")
            }
            HttpResult.Error(e)
        }
        when (response) {
            is HttpResult.Success -> {
                val data = response.result.data
                if (data != null) {
                    val hasNotNext = (data.datas.size < it.loadSize) && (data.over)
                    PagingSource.LoadResult.Page(
                        data = response.result.data!!.datas,
                        prevKey = if (page - 1 > 0) page - 1 else null,
                        nextKey = if (hasNotNext) null else page + 1
                    )
                } else {
                    PagingSource.LoadResult.Page(
                        data = emptyList(),
                        prevKey = if (page - 1 > 0) page - 1 else null,
                        nextKey = null
                    )
                }
            }
            is HttpResult.Error -> {
                PagingSource.LoadResult.Error(response.exception)
            }
        }
    }
}

fun <K : Any, V : Any> ViewModel.pager(
    config: CommonPagingConfig = CommonPagingConfig(),
    initialKey: K? = null,
    loadData: suspend (PagingSource.LoadParams<K>) -> PagingSource.LoadResult<K, V>
): Flow<PagingData<V>> {
    val baseConfig = PagingConfig(
        config.pageSize,
        initialLoadSize = config.initialLoadSize,
        prefetchDistance = config.prefetchDistance,
        maxSize = config.maxSize,
        enablePlaceholders = config.enablePlaceholders
    )
    return Pager(
        config = baseConfig,
        initialKey = initialKey
    ) {
        object : PagingSource<K, V>() {
            override suspend fun load(params: LoadParams<K>): LoadResult<K, V> {
                return loadData.invoke(params)
            }

            override fun getRefreshKey(state: PagingState<K, V>): K? {
                return initialKey
            }

        }
    }.flow.cachedIn(viewModelScope)
}

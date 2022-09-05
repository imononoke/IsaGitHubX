package com.isa.githubx.model

import java.lang.Exception

data class BasicResp<T>(
    var data: T?,
    var errorCode: Int,
    var errorMsg: String
)

data class ListWrapper<T>(
    var curPage: Int,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int,
    var datas: ArrayList<T>
)

sealed class HttpResult<out T> {
    data class Success<T>(val result: T): HttpResult<T>()
    data class Error(val exception: Exception): HttpResult<Nothing>()
}


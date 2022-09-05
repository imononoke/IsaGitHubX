package com.isa.githubx.network

import com.isa.githubx.MyApp
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiCall {

    //设置缓存大小
    var cacheSize = 100 * 1024 * 1024

    private val logInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private const val DEFAULT_TIMEOUT = 30000
    private lateinit var SERVICE: ApiService

    //手动创建一个OkHttpClient并设置超时时间
    val retrofit: ApiService
        get() {
            if (!ApiCall::SERVICE.isInitialized) {
                SERVICE = Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(ApiService.BASE_URL)
                    .build()
                    .create(ApiService::class.java)
            }
            return SERVICE
        }

    // OkhttpClient
    val okHttpClient = OkHttpClient.Builder().run {
        connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
        readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
        writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
        callTimeout(10, TimeUnit.SECONDS)
        cache(Cache(MyApp.context.cacheDir, cacheSize.toLong()))
        addNetworkInterceptor(logInterceptor)
        build()
    }

    // Retrofit
//    private val retrofit: Retrofit = Retrofit.Builder()
//        .client(okHttpClient)
//        .baseUrl(ApiService.BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()

    // ApiService
//    val apiService: ApiService = retrofit.create(ApiService::class.java)

}

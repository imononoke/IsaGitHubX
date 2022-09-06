package com.isa.githubx.network

import com.isa.githubx.model.*
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    /**
     * Auth
     */
    @GET("user")
    suspend fun fetchUserOwner(
        @Header("Authorization") authorization: String
    ): UserInfo


    /**
     * search repos
     */
    @GET("search/repositories")
    suspend fun searchRepos(
        @Query(value="q", encoded = true) query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): SearchRepoResult

//    @GET("search/repositories")
//    suspend fun searchRepos(
//        @Query(value="q", encoded = true) query: String,
//        @Query("page") page: Int,
//        @Query("per_page") perPage: Int
//    ): BasicResp<ListWrapper<RepoEntity>>

    /**
     * search users
     */
//    @GET("search/users")
//    suspend fun searchUsers(
//        @Query(value="q", encoded = true) query: String,
//        @Query("page") page: Int,
//        @Query("per_page") perPage: Int
//    ): SearchUserResult

    /**
     * get users
     */
    @GET("/users")
    suspend fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): MutableList<UserInfo>

    /**
     * get users repos
     */
    @GET("/users/{username}/repos")
    suspend fun getPros(
        @Path("username") username: String
    ): MutableList<RepoEntity>

//    /**
//     * get users following
//     */
//    @GET("/users/{username}/following")
//    suspend fun getFollowing(
//        @Path("username") username: String
//    ): MutableList<FollowersEntity>
//
//    /**
//     * get users followers
//     */
//    @GET("/users/{username}/followers")
//    suspend fun getFollowers(
//        @Path("username") username: String
//    ): MutableList<FollowersEntity>
}

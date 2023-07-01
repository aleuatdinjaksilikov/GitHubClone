package com.example.githubclone.data.remote

import com.example.githubclone.data.models.*
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface GitHubApi {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("https://github.com/login/oauth/access_token")
    suspend fun getAccessToken(
        @Field("client_id")clientId:String,
        @Field("client_secret")clientSecret:String,
        @Field("code")code:String
    ):Response<LoginResponseData>

    @GET("/user")
    suspend fun getUserInfo():Response<GetUserProfileInfoResponseData>

    @GET("/user/repos")
    suspend fun getUserRepositories():Response<List<GetUserRepositoriesResponseDataItem>>

    @GET("/search/repositories")
    suspend fun getRepositoryByName(
        @Query("q") name:String
    ):Response<SearchRepoByNameResponseData>

    @GET("/search/users")
    suspend fun searchUsers(
        @Query("q") name:String
    ):Response<SearchUsersResponseData>
}
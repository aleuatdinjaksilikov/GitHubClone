package com.example.githubclone.domain.repository

import com.example.githubclone.data.models.*
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun login():Flow<ResultData<LoginResponseData>>

    fun getUserInfo():Flow<ResultData<GetUserProfileInfoResponseData>>

    fun getUserRepositories():Flow<ResultData<List<GetUserRepositoriesResponseDataItem>>>

    fun getRepositoryByName(name:String):Flow<ResultData<SearchRepoByNameResponseData>>

    fun searchUsers(name:String):Flow<ResultData<SearchUsersResponseData>>
}
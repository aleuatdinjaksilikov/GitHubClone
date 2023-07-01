package com.example.githubclone.domain.usecase

import com.example.githubclone.data.models.ResultData
import com.example.githubclone.data.models.SearchUsersResponseData
import kotlinx.coroutines.flow.Flow

interface SearchUserUseCase {
    fun execute(name:String): Flow<ResultData<SearchUsersResponseData>>
}
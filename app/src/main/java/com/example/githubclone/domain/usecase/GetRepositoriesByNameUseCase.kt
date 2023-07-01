package com.example.githubclone.domain.usecase

import com.example.githubclone.data.models.ResultData
import com.example.githubclone.data.models.SearchRepoByNameResponseData
import kotlinx.coroutines.flow.Flow

interface GetRepositoriesByNameUseCase {
    fun execute(name:String): Flow<ResultData<SearchRepoByNameResponseData>>
}
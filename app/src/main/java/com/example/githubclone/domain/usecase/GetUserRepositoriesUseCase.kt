package com.example.githubclone.domain.usecase

import com.example.githubclone.data.models.GetUserRepositoriesResponseDataItem
import com.example.githubclone.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetUserRepositoriesUseCase {
    fun execute():Flow<ResultData<List<GetUserRepositoriesResponseDataItem>>>
}
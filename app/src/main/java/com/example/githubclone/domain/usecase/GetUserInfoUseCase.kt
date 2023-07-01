package com.example.githubclone.domain.usecase

import com.example.githubclone.data.models.GetUserProfileInfoResponseData
import com.example.githubclone.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetUserInfoUseCase {
    fun execute():Flow<ResultData<GetUserProfileInfoResponseData>>
}
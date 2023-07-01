package com.example.githubclone.domain.usecase

import com.example.githubclone.data.models.LoginResponseData
import com.example.githubclone.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    fun execute(): Flow<ResultData<LoginResponseData>>
}
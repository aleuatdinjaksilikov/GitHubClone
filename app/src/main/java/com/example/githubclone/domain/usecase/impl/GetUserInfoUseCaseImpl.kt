package com.example.githubclone.domain.usecase.impl

import com.example.githubclone.data.models.GetUserProfileInfoResponseData
import com.example.githubclone.data.models.ResultData
import com.example.githubclone.domain.repository.MainRepository
import com.example.githubclone.domain.usecase.GetUserInfoUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserInfoUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):GetUserInfoUseCase {
    override fun execute(): Flow<ResultData<GetUserProfileInfoResponseData>> {
        return mainRepository.getUserInfo()
    }

}
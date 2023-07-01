package com.example.githubclone.domain.usecase.impl

import com.example.githubclone.data.models.LoginResponseData
import com.example.githubclone.data.models.ResultData
import com.example.githubclone.domain.repository.MainRepository
import com.example.githubclone.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):LoginUseCase {
    override fun execute(): Flow<ResultData<LoginResponseData>> {
        return mainRepository.login()
    }

}
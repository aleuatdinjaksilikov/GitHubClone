package com.example.githubclone.domain.usecase.impl

import com.example.githubclone.data.models.ResultData
import com.example.githubclone.data.models.SearchUsersResponseData
import com.example.githubclone.domain.repository.MainRepository
import com.example.githubclone.domain.usecase.SearchUserUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUserUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :SearchUserUseCase{
    override fun execute(name:String): Flow<ResultData<SearchUsersResponseData>> {
        return mainRepository.searchUsers(name = name)
    }

}
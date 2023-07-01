package com.example.githubclone.domain.usecase.impl

import com.example.githubclone.data.models.GetUserRepositoriesResponseDataItem
import com.example.githubclone.data.models.ResultData
import com.example.githubclone.domain.repository.MainRepository
import com.example.githubclone.domain.usecase.GetUserRepositoriesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserRepositoriesUseCaseImpl @Inject constructor(private val mainRepository: MainRepository):GetUserRepositoriesUseCase {
    override fun execute(): Flow<ResultData<List<GetUserRepositoriesResponseDataItem>>> {
        return mainRepository.getUserRepositories()
    }
}
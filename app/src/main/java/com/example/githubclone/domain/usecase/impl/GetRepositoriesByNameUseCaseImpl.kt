package com.example.githubclone.domain.usecase.impl

import com.example.githubclone.data.models.ResultData
import com.example.githubclone.data.models.SearchRepoByNameResponseData
import com.example.githubclone.domain.repository.MainRepository
import com.example.githubclone.domain.usecase.GetRepositoriesByNameUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoriesByNameUseCaseImpl @Inject constructor(
    private val mainRepository: MainRepository
):GetRepositoriesByNameUseCase {

    override fun execute(name:String): Flow<ResultData<SearchRepoByNameResponseData>> {
        return mainRepository.getRepositoryByName(name = name)
    }
}
package com.example.githubclone.domain.usecase.impl

import com.example.githubclone.data.models.HistoryData
import com.example.githubclone.domain.repository.LocalRepository
import com.example.githubclone.domain.usecase.GetAllTextUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTextUseCaseImpl @Inject constructor(private val localRepository: LocalRepository):GetAllTextUseCase {
    override fun execute(): Flow<List<HistoryData>> {
        return localRepository.getAllText()
    }
}
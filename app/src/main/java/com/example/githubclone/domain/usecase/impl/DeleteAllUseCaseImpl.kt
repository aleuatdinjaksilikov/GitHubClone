package com.example.githubclone.domain.usecase.impl

import com.example.githubclone.data.models.HistoryData
import com.example.githubclone.domain.repository.LocalRepository
import com.example.githubclone.domain.usecase.DeleteAllUseCase
import javax.inject.Inject

class DeleteAllUseCaseImpl @Inject constructor(private val localRepository: LocalRepository):DeleteAllUseCase {
    override suspend fun execute() {
        localRepository.deleteAll()
    }
}
package com.example.githubclone.domain.usecase.impl

import androidx.room.Insert
import com.example.githubclone.data.models.HistoryData
import com.example.githubclone.domain.repository.LocalRepository
import com.example.githubclone.domain.usecase.InsertTextUseCase
import javax.inject.Inject

class InsertTextUseCaseImpl @Inject constructor(private var localRepository: LocalRepository):InsertTextUseCase {
    override suspend fun execute(historyData: HistoryData) {
       localRepository.insertText(historyData = historyData)
    }
}
package com.example.githubclone.domain.usecase

import com.example.githubclone.data.models.HistoryData

interface InsertTextUseCase {
    suspend fun execute(historyData: HistoryData)
}
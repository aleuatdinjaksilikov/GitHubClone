package com.example.githubclone.domain.usecase

import com.example.githubclone.data.models.HistoryData
import kotlinx.coroutines.flow.Flow

interface GetAllTextUseCase {
    fun execute():Flow<List<HistoryData>>
}
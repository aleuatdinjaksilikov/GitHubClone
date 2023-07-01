package com.example.githubclone.domain.repository

import com.example.githubclone.data.models.HistoryData
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun insertText(historyData: HistoryData)
    suspend fun deleteAll()
    fun getAllText():Flow<List<HistoryData>>
}
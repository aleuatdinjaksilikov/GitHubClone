package com.example.githubclone.data.repository

import com.example.githubclone.data.locale.AppDao
import com.example.githubclone.data.models.HistoryData
import com.example.githubclone.domain.repository.LocalRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val appDao: AppDao):LocalRepository {

    override suspend fun insertText(historyData: HistoryData) {
        appDao.insertText(historyData = historyData)
    }

    override suspend fun deleteAll() {
        appDao.deleteAll()
    }

    override fun getAllText()= flow{
        emit(appDao.getAllText())
    }


}
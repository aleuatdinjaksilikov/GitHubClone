package com.example.githubclone.data.repository

import com.example.githubclone.data.models.ResultData
import com.example.githubclone.data.remote.GitHubApi
import com.example.githubclone.domain.repository.MainRepository
import com.example.githubclone.utils.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val gitHubApi: GitHubApi):MainRepository {

    override fun login()= flow {
        val response = gitHubApi.getAccessToken("8f3cf5f09bd0c93a0528","5447af3efb5afba3751aa6a0025e97affcf1a538",SharedPref.pref.getString("code","")?:"")
        if(response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }.flowOn(Dispatchers.IO)

    override fun getUserInfo()= flow {
        val response = gitHubApi.getUserInfo()
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }.flowOn(Dispatchers.IO)

    override fun getUserRepositories() = flow{
        val response = gitHubApi.getUserRepositories()
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }.flowOn(Dispatchers.IO)

    override fun getRepositoryByName(name: String) = flow {
        val response = gitHubApi.getRepositoryByName(name = name)
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }.flowOn(Dispatchers.IO)

    override fun searchUsers(name: String) = flow {
        val response = gitHubApi.searchUsers(name = name)
        if (response.isSuccessful){
            emit(ResultData.Success(response.body()!!))
        }else{
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }.flowOn(Dispatchers.IO)
}
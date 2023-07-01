package com.example.githubclone.ui.ProfileScreen.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclone.data.models.GetUserProfileInfoResponseData
import com.example.githubclone.data.models.GetUserRepositoriesResponseDataItem
import com.example.githubclone.data.models.ResultData
import com.example.githubclone.domain.usecase.GetUserInfoUseCase
import com.example.githubclone.domain.usecase.GetUserRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentVM @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserRepositoriesUseCase: GetUserRepositoriesUseCase
) :ViewModel(){

    private var _getUserInfoFlow = MutableSharedFlow<GetUserProfileInfoResponseData>()
    val getUserInfoFlow:SharedFlow<GetUserProfileInfoResponseData> get() = _getUserInfoFlow

    private var _getUserInfoMessage = MutableSharedFlow<String>()
    val getUserInfoMessage:SharedFlow<String> get() = _getUserInfoMessage

    private var _getUserInfoError = MutableSharedFlow<Throwable>()
    val getUserInfoError:SharedFlow<Throwable> get() = _getUserInfoError

    private var _getRepositoriesFlow = MutableSharedFlow<List<GetUserRepositoriesResponseDataItem>>()
    val getRepositoriesFlow :SharedFlow<List<GetUserRepositoriesResponseDataItem>> get() = _getRepositoriesFlow

    private var _getRepositoriesMessage = MutableSharedFlow<String>()
    val getRepositoriesMessage:SharedFlow<String> get() = _getRepositoriesMessage

    private var _getRepositoriesError = MutableSharedFlow<Throwable>()
    val getRepositoriesError:SharedFlow<Throwable> get() = _getRepositoriesError

    fun getUserInfo(){
        getUserInfoUseCase.execute().onEach{
            when(it){
                is ResultData.Success->{
                    _getUserInfoFlow.emit(it.data)
                }
                is ResultData.Message->{
                    _getUserInfoMessage.emit(it.message)
                }
                is ResultData.Error->{
                    _getUserInfoError.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getRepositories(){
        getUserRepositoriesUseCase.execute().onEach {
            when(it){
                is ResultData.Success->{
                    _getRepositoriesFlow.emit(it.data)
                }
                is ResultData.Message->{
                    _getRepositoriesMessage.emit(it.message)
                }
                is ResultData.Error->{
                    _getRepositoriesError.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}
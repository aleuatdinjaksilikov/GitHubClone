package com.example.githubclone.ui.RepositoryScreen.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclone.data.models.GetUserRepositoriesResponseDataItem
import com.example.githubclone.data.models.ResultData
import com.example.githubclone.domain.usecase.GetUserRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RepositoryFragmentVM @Inject constructor(
    private val getUserRepositoriesUseCase: GetUserRepositoriesUseCase
):ViewModel() {

    private var _getRepositoriesFlow = MutableSharedFlow<List<GetUserRepositoriesResponseDataItem>>()
    val getRepositoriesFlow : SharedFlow<List<GetUserRepositoriesResponseDataItem>> get() = _getRepositoriesFlow

    private var _getRepositoriesMessage = MutableSharedFlow<String>()
    val getRepositoriesMessage: SharedFlow<String> get() = _getRepositoriesMessage

    private var _getRepositoriesError = MutableSharedFlow<Throwable>()
    val getRepositoriesError: SharedFlow<Throwable> get() = _getRepositoriesError

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
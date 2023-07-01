package com.example.githubclone.ui.LoginScreen.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclone.data.models.LoginResponseData
import com.example.githubclone.data.models.ResultData
import com.example.githubclone.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginFragmentVM @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private var _loginFlow = MutableLiveData<LoginResponseData>()
    val loginFlow: LiveData<LoginResponseData> get() = _loginFlow

    private var _messageLoginFlow = MutableLiveData<String>()
    val messageLoginFlow: LiveData<String> get() = _messageLoginFlow

    private var _errorLoginFlow = MutableLiveData<Throwable>()
    val errorLoginFlow: LiveData<Throwable> get() = _errorLoginFlow

    fun login(){
        loginUseCase.execute().onEach {
            when(it){
                is ResultData.Success->{
                    _loginFlow.value = it.data!!
                }
                is ResultData.Message->{
                    _messageLoginFlow.value = it.message
                }
                is ResultData.Error->{
                    _errorLoginFlow.value = it.error
                }
            }
        }.launchIn(viewModelScope)
    }
}
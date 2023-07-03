package com.example.githubclone.ui.LoginScreen.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclone.data.models.LoginResponseData
import com.example.githubclone.data.models.ResultData
import com.example.githubclone.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginFragmentVM @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private var _loginLiveData = MutableLiveData<LoginResponseData>()
    val loginLiveData: LiveData<LoginResponseData> get() = _loginLiveData

    private var _messageLoginLiveData = MutableLiveData<String>()
    val messageLoginLiveData: LiveData<String> get() = _messageLoginLiveData

    private var _errorLoginLiveData = MutableLiveData<Throwable>()
    val errorLoginData: LiveData<Throwable> get() = _errorLoginLiveData

    fun login(){
        loginUseCase.execute().onEach {
            when(it){
                is ResultData.Success->{
                    _loginLiveData.value = it.data!!
                }
                is ResultData.Message->{
                    _messageLoginLiveData.value = it.message
                }
                is ResultData.Error->{
                    _errorLoginLiveData.value = it.error
                }
            }
        }.launchIn(viewModelScope)
    }
}
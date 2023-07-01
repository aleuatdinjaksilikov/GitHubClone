package com.example.githubclone.ui.SearchFragment.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclone.data.models.*
import com.example.githubclone.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchFragmentVM @Inject constructor(
    private val getRepositoriesByNameUseCase: GetRepositoriesByNameUseCase,
    private val searchUserUseCase: SearchUserUseCase,
    private val insertTextUseCase: InsertTextUseCase,
    private val deleteAllUseCase: DeleteAllUseCase,
    private val getAllTextUseCase: GetAllTextUseCase
):ViewModel() {

    private var _getRepositoriesByNameFlow = MutableSharedFlow<SearchRepoByNameResponseData>()
    val getRepositoriesByNameFlow : SharedFlow<SearchRepoByNameResponseData> get() = _getRepositoriesByNameFlow

    private var _getRepositoriesByNameMessage = MutableSharedFlow<String>()
    val getRepositoriesByNameMessage: SharedFlow<String> get() = _getRepositoriesByNameMessage

    private var _getRepositoriesByNameError = MutableSharedFlow<Throwable>()
    val getRepositoriesByNameError: SharedFlow<Throwable> get() = _getRepositoriesByNameError


    private var _getUsersFlow = MutableSharedFlow<SearchUsersResponseData>()
    val getUsersFlow : SharedFlow<SearchUsersResponseData> get() = _getUsersFlow

    private var _getUsersMessage = MutableSharedFlow<String>()
    val getUsersMessage: SharedFlow<String> get() = _getUsersMessage

    private var _getUsersError = MutableSharedFlow<Throwable>()
    val getUsersError: SharedFlow<Throwable> get() = _getUsersError

    private var _historyList = MutableSharedFlow<List<HistoryData>>()
    val historyList:SharedFlow<List<HistoryData>> get() = _historyList


    fun getRepositoriesByName(text:String){

        getRepositoriesByNameUseCase.execute(name = text).onEach {
            when(it){
                is ResultData.Success->{
                    _getRepositoriesByNameFlow.emit(it.data)
                }
                is ResultData.Message->{
                    _getRepositoriesByNameMessage.emit(it.message)
                }
                is ResultData.Error->{
                    _getRepositoriesByNameError.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getSearchUser(text:String){

        searchUserUseCase.execute(name = text).onEach {
            when(it){
                is ResultData.Success->{
                    _getUsersFlow.emit(it.data)
                }
                is ResultData.Message->{
                    _getUsersMessage.emit(it.message)
                }
                is ResultData.Error->{
                    _getUsersError.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun insertText(historyData: HistoryData){
        insertTextUseCase.execute(historyData = historyData)
    }

    fun getAllText(){
        getAllTextUseCase.execute().onEach {
            _historyList.emit(it)
        }.launchIn(viewModelScope)
    }

    suspend fun deleteAll(){
        deleteAllUseCase.execute()
    }
}
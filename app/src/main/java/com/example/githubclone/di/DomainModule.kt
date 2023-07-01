package com.example.githubclone.di

import com.example.githubclone.domain.repository.LocalRepository
import com.example.githubclone.domain.repository.MainRepository
import com.example.githubclone.domain.usecase.*
import com.example.githubclone.domain.usecase.impl.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun loginUseCase(mainRepository: MainRepository):LoginUseCase{
        return LoginUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getUserInfoUseCase(mainRepository: MainRepository):GetUserInfoUseCase{
        return GetUserInfoUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getUserRepositoriesUseCase(mainRepository: MainRepository):GetUserRepositoriesUseCase{
        return GetUserRepositoriesUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getRepositoriesByName(mainRepository: MainRepository):GetRepositoriesByNameUseCase{
        return GetRepositoriesByNameUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun searchUserUseCase(mainRepository: MainRepository):SearchUserUseCase{
        return SearchUserUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun insertTextUseCase(localRepository: LocalRepository):InsertTextUseCase{
        return InsertTextUseCaseImpl(localRepository = localRepository)
    }

    @Provides
    fun deleteAllUseCase(localRepository: LocalRepository):DeleteAllUseCase{
        return DeleteAllUseCaseImpl(localRepository = localRepository)
    }

    @Provides
    fun getAllTextUseCase(localRepository: LocalRepository):GetAllTextUseCase{
        return GetAllTextUseCaseImpl(localRepository = localRepository)
    }
}
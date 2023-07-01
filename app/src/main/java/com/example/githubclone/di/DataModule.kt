package com.example.githubclone.di

import android.content.Context
import androidx.room.Room
import com.example.githubclone.data.locale.AppDao
import com.example.githubclone.data.locale.AppDatabase
import com.example.githubclone.data.remote.GitHubApi
import com.example.githubclone.data.repository.LocalRepositoryImpl
import com.example.githubclone.data.repository.MainRepositoryImpl
import com.example.githubclone.data.utils.CustomInterceptor
import com.example.githubclone.domain.repository.LocalRepository
import com.example.githubclone.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient):GitHubApi{
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(CustomInterceptor()).build()
    }

    @Provides
    @Singleton
    fun provideInterceptor():HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun mainRepository(gitHubApi: GitHubApi): MainRepository {
        return MainRepositoryImpl(gitHubApi = gitHubApi)
    }

    @Provides
    @Singleton
    fun localRepository(appDao: AppDao): LocalRepository {
        return LocalRepositoryImpl(appDao = appDao)
    }

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java, "db_name")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: AppDatabase) = db.appDao()


}
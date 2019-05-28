package com.example.dagger2.di.Module

import com.example.dagger2.network.api.UserApi
import com.example.dagger2.repository.UserRepoImplementation
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideImageRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideImageApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideUserService(userApi: UserApi): UserRepoImplementation =
        UserRepoImplementation(userApi)
}
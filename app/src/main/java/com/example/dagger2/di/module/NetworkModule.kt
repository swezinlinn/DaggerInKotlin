package com.example.dagger2.di.module

import com.example.dagger2.network.api.UserApi
import com.example.dagger2.repository.UserRepoImplementation
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideImageRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideImageApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideUserService(userApi: UserApi): UserRepoImplementation =
        UserRepoImplementation(userApi)
}
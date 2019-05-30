package com.example.dagger2.di.module

import com.example.dagger2.network.api.UserApi
import com.example.dagger2.repository.UserRepo
import com.example.dagger2.repository.UserRepoImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Provides
    @Singleton
    fun provideRepoModule(api: UserApi): UserRepo = UserRepoImplementation(api)
}
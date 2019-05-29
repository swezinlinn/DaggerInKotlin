package com.example.dagger2.di.Module

import com.example.dagger2.domain.scheduler.PostExecutionThread
import com.example.dagger2.util.UiThread
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class UiModule {
    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread) : PostExecutionThread
 }
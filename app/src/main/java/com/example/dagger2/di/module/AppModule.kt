package com.example.dagger2.di.module

import android.app.Application
import dagger.Module
import javax.inject.Singleton
import dagger.Provides

@Module
class AppModule(mApplication: Application) {
    var application: Application = mApplication

}
package com.example.dagger2

import android.app.Application
import com.example.dagger2.di.ApplicationComponent
import com.example.dagger2.di.DaggerApplicationComponent
import com.example.dagger2.di.Module.AppModule
import com.example.dagger2.di.Module.NetworkModule

class DaggerApplication : Application(){
    private lateinit var appComponent : ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            // list of modules that are part of this component need to be created here too
            .appModule(AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
            .networkModule(NetworkModule())
            .build()
    }

    fun getAppComponent(): ApplicationComponent {
        return appComponent
    }
}
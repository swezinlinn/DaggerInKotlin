package com.example.dagger2

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDexApplication
import com.example.dagger2.di.DaggerApplicationComponent
import com.example.dagger2.di.module.AppModule
import com.example.dagger2.di.module.NetworkModule
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class DaggerApplication : MultiDexApplication(), HasActivityInjector{

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
            // list of modules that are part of this component need to be created here too
            .application(this)
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build().inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}
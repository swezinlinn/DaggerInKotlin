package com.example.dagger2.di

import android.app.Application
import com.example.dagger2.DaggerApplication
import com.example.dagger2.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton
import com.example.dagger2.di.module.AppModule



@Singleton
@Component(modules = [AndroidInjectionModule::class,NetworkModule::class, AppModule::class, ViewModelModule::class , RepoModule::class, UiModule::class, ActivityBuilder::class])
interface ApplicationComponent {
    @Component.Builder
    interface instance {
        @BindsInstance
        fun application(application: Application): instance

        @BindsInstance
        fun appModule(appModule: AppModule): instance

        @BindsInstance
        fun networkModule(netModule: NetworkModule): instance

        fun build(): ApplicationComponent
    }

    fun inject(app : DaggerApplication )

}
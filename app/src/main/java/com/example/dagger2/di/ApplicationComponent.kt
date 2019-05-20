package com.example.dagger2.di

import com.example.dagger2.MainActivity
import com.example.dagger2.NextActivity
import com.example.dagger2.di.Module.NetworkModule
import com.example.dagger2.di.Module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, AppModule::class])
public interface ApplicationComponent {
    fun inject (mainActivity: MainActivity)
    fun inject (nextActivity: NextActivity)
}
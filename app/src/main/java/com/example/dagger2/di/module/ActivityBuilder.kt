package com.example.dagger2.di.module

import com.example.dagger2.FirstFragment
import com.example.dagger2.MainActivity
import com.example.dagger2.SecondFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector()
    abstract fun bindMainActivity() : MainActivity

    @ContributesAndroidInjector()
    abstract fun bindFirstFragment() : FirstFragment

    @ContributesAndroidInjector()
    abstract fun bindSecondFragment() : SecondFragment

}
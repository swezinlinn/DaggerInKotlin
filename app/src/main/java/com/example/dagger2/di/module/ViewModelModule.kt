package com.example.dagger2.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dagger2.di.viewmodel.ViewModelFactory
import com.example.dagger2.di.viewmodel.ViewModelKey
import com.example.dagger2.presentation.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(userViewModel: UserViewModel) : ViewModel
}
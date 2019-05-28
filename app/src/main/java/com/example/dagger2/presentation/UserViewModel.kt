package com.example.dagger2.presentation

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.dagger2.domain.UserUseCase
import com.example.dagger2.network.api.UserApi
import com.example.dagger2.resource.*
import com.example.myapplication.UserList
import com.example.myapplication.Users
import io.reactivex.observers.DisposableSingleObserver
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class UserViewModel @Inject constructor(val userUseCase: UserUseCase) : ViewModel() {

    val userList : MediatorLiveData<Resource<List<Users>>> = MediatorLiveData()

    init {
        userList.setInitial()
        requestUserList();
    }

    private fun requestUserList() = userUseCase.execute(UserListSubscriber())

    inner class UserListSubscriber : DisposableSingleObserver<UserList>(){
        override fun onSuccess(response : UserList) {
            Log.d("TAG", "on success")
            userList.setSuccess(response.users)        }

        override fun onError(e: Throwable) {
            userList.setError("fail to load data.")
        }
    }
}
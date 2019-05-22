package com.example.dagger2.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.dagger2.network.UserApi
import com.example.dagger2.resource.Resource
import com.example.dagger2.resource.setError
import com.example.dagger2.resource.setLoading
import com.example.dagger2.resource.setSuccess
import com.example.myapplication.UserList
import com.example.myapplication.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserViewModel : ViewModel() {

    private var userList : MutableLiveData<Resource<List<Users>>>?= null

    fun getUserList() : MutableLiveData<Resource<List<Users>>> {
        if (userList == null) {
            userList = MutableLiveData<Resource<List<Users>>>()
            loadUser();
        }
        return userList as MutableLiveData<Resource<List<Users>>>
    }

    fun loadUser() {
    userList!!.setLoading()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(UserApi::class.java)
        val call = api.users

        call.enqueue(object : Callback<UserList>{
            override fun onFailure(call: Call<UserList>, t: Throwable) {

            }

            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if(response.isSuccessful){
                    Log.d("TAG", "on success")
                    userList?.setSuccess(response.body()?.users)
                }else{
                    userList?.setError("fail to load data.")
                    Log.d("TAG", "on failure")
                }
            }
        })
    }
}
package com.example.dagger2.network.api

import com.example.myapplication.UserList
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {
    @GET("users?q=rokano")
    fun userList() : Single<UserList>
}
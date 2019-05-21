package com.example.dagger2.network

import com.example.myapplication.UserList
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {
    @get:GET("users?q=rokano")
    val users: Call<UserList>
}
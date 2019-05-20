package com.example.dagger2.network

import com.example.dagger2.network.response.UserList
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @get:GET("users?q=rokano")
    val users: Call<UserList>
}
package com.example.dagger2.network.response

import com.example.dagger2.network.response.Users
import com.google.gson.annotations.SerializedName

class UserList{
    @SerializedName("items")
    var users: List<Users>? = null
}
package com.example.myapplication

import com.google.gson.annotations.SerializedName

class UserList{
    @SerializedName("items")
    var users: List<Users>? = null
}
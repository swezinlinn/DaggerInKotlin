package com.example.dagger2.repository

import com.example.myapplication.UserList
import io.reactivex.Single

interface UserRepo {
    fun requestUserList() : Single<UserList>
}
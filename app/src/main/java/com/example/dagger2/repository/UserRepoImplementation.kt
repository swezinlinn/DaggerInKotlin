package com.example.dagger2.repository

import com.example.dagger2.network.api.UserApi
import com.example.myapplication.UserList
import io.reactivex.Single
import javax.inject.Inject

class UserRepoImplementation @Inject constructor(val api: UserApi) : UserRepo {

    override fun requestUserList(): Single<UserList> =
            api.userList()
}
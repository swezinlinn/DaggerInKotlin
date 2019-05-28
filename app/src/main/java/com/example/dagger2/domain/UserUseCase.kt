package com.example.dagger2.domain

import com.example.dagger2.domain.observer.SingleUseCase
import com.example.dagger2.domain.scheduler.PostExecutionThread
import com.example.dagger2.repository.UserRepo
import com.example.myapplication.UserList
import io.reactivex.Single
import java.lang.reflect.Parameter
import javax.inject.Inject

class UserUseCase @Inject constructor(val userRepo: UserRepo, val postExecutionThread: PostExecutionThread)
    :SingleUseCase<UserList>(postExecutionThread){
    override fun buildUseCaseSingle(): Single<UserList> {
        return userRepo.requestUserList()
    }
}
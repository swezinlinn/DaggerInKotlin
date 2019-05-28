package com.example.dagger2.domain.observer

import com.example.dagger2.domain.UserUseCase
import com.example.dagger2.domain.scheduler.PostExecutionThread
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<Object> constructor(
    private val postExecutionThread: PostExecutionThread
){
    private val compositeDisposable = CompositeDisposable()
    protected abstract fun buildUseCaseSingle(): Single<Object>

    open fun execute(observer: DisposableSingleObserver<Object>){
        val completable = this.buildUseCaseSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
        addDisposable(completable.subscribeWith(observer))
    }

     fun addDisposable(disposable : Disposable){
        compositeDisposable.add(disposable)
     }

    fun dispose(){
        if(!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}
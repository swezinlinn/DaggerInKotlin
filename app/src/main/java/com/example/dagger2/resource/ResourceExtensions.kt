package com.example.dagger2.resource

import android.arch.lifecycle.MutableLiveData

fun <T> MutableLiveData<Resource<T>>.setInitial() =
        postValue(Resource(ResourceState.INITIAL, null,null))

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T? = null) =
        postValue(Resource(ResourceState.SUCCESS, data,null))

fun <T> MutableLiveData<Resource<T>>.setLoading() =
        postValue(Resource(ResourceState.LOADING, null,null))

fun <T> MutableLiveData<Resource<T>>.setError(message : String? = null) =
        postValue(Resource(ResourceState.ERROR, value?.data , message))
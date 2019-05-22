package com.example.dagger2.resource

sealed class ResourceState {
    object SUCCESS : ResourceState()
    object LOADING : ResourceState()
    object ERROR : ResourceState()
}
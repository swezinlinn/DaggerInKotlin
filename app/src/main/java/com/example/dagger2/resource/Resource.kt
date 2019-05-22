package com.example.dagger2.resource

data class Resource<out T>(val state: ResourceState,
                           val data: T? = null,
                           val message: String? = null)
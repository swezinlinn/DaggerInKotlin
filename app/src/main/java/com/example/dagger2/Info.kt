package com.example.dagger2

import javax.inject.Inject

class Info @Inject constructor(){
    val text = "Hello Dagger2"
    val nextText = "Hello I am Next!!"
}
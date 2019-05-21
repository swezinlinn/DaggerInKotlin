package com.example.dagger2.view

import android.widget.TextView
import com.example.dagger2.R
import com.example.myapplication.UserList
import com.example.myapplication.Users
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View

@Layout(R.layout.layout_user_view)
class UserViewGenerator(private val userData: Users)  {
    @View(R.id.textView1)
    lateinit var textView1: TextView
    @View(R.id.textView2)
    lateinit var textView2: TextView
    @View(R.id.textView3)
    lateinit var textView3: TextView

    @Resolve
    public fun onResolve(){
        textView1.text = userData.id.toString()
        textView2.text = userData.login
        textView3.text = userData.score.toString()
    }
}
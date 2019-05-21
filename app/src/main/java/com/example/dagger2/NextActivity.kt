package com.example.dagger2

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView
import butterknife.ButterKnife
import com.example.dagger2.viewmodel.UserViewModel

import kotlinx.android.synthetic.main.activity_next.*
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import com.example.dagger2.view.UserViewGenerator
import com.example.myapplication.Users
import com.mindorks.placeholderview.PlaceHolderView


class NextActivity : AppCompatActivity() {

    @BindView(R.id.phv_user)
    lateinit var phvUser : PlaceHolderView

    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        setSupportActionBar(toolbar)
        ButterKnife.bind(this)

        (application as DaggerApplication).getAppComponent().inject(this) // Injecting using `ComputerComponent` initialised in application class

        setView()

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        userViewModel.getUserList().observe(this,Observer{
                data: List<Users>? -> for(i in 0 until data!!.size) {
                   phvUser.addView(
                       UserViewGenerator(data[i]))
               }
        })
        fab.setOnClickListener { view ->

        }
    }

    fun setView(){
        phvUser.builder
            .setHasFixedSize(false)
            .setItemViewCacheSize(10)
            .setLayoutManager(
                LinearLayoutManager(
                    this,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            )
    }

}

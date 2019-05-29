package com.example.dagger2

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import javax.inject.Inject
import android.util.Log
import com.example.dagger2.network.api.UserApi
import com.example.dagger2.presentation.UserViewModel
import com.example.dagger2.resource.Resource
import com.example.dagger2.resource.ResourceState
import com.example.dagger2.view.UserViewGenerator
import com.example.myapplication.UserList
import com.example.myapplication.Users
import com.mindorks.placeholderview.PlaceHolderView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    @BindView(R.id.phv_user)
    lateinit var phvUser: PlaceHolderView
    private lateinit var context: Context
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var userViewModel : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as DaggerApplication).getAppComponent().inject(this) // Injecting using `ComputerComponent` initialised in application class
        ButterKnife.bind(this)
        context = this
        setView()
        userViewModel = ViewModelProviders.of(this,viewModelFactory)[UserViewModel::class.java]
        userViewModel.userList.observe(this, Observer { showList(it) })
    }

    private fun showList(resource: Resource<List<Users>>?) {
        resource?.let {
            when(it.state){
                ResourceState.LOADING -> av_loading.show()
                ResourceState.SUCCESS -> av_loading.hide()
                ResourceState.ERROR -> av_loading.hide()
            }

            it.data?.let{
                for(i in 0 until it.size){
                    phvUser.addView(
                        UserViewGenerator(
                            it.get(i)
                        )
                    )
                }
            }

            it.message?.let {

            }
        }
    }

    private fun setView(){
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

    @OnClick(R.id.button)
    fun onClick(){

        startActivity(Intent(this,NextActivity::class.java))
    }
}


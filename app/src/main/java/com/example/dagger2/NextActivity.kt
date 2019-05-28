package com.example.dagger2

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView
import butterknife.ButterKnife
import com.example.dagger2.presentation.UserViewModel

import kotlinx.android.synthetic.main.activity_next.*
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import com.example.dagger2.resource.Resource
import com.example.dagger2.resource.ResourceState
import com.example.dagger2.view.UserViewGenerator
import com.example.myapplication.Users
import com.mindorks.placeholderview.PlaceHolderView
import com.wang.avi.AVLoadingIndicatorView
import javax.inject.Inject


class NextActivity : AppCompatActivity() {

    @BindView(R.id.phv_user)
    lateinit var phvUser : PlaceHolderView
    @BindView(R.id.av_loading)
    lateinit var avLoading: AVLoadingIndicatorView

    lateinit var userViewModel: UserViewModel
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        setSupportActionBar(toolbar)
        ButterKnife.bind(this)

        (application as DaggerApplication).getAppComponent().inject(this) // Injecting using `ComputerComponent` initialised in application class

        setView()

        userViewModel = ViewModelProviders.of(this,viewModelFactory)[UserViewModel::class.java]
        userViewModel.userList.observe(this,Observer{ showList(it)})

        fab.setOnClickListener { view ->

        }
    }

    fun showList(resource : Resource<List<Users>>?){
        resource?.let {
            when(it.state){
                ResourceState.LOADING -> avLoading.show()
                ResourceState.SUCCESS -> avLoading.hide()
                ResourceState.ERROR -> avLoading.hide()
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

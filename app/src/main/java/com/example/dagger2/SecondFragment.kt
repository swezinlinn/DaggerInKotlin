package com.example.dagger2


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dagger2.di.viewmodel.ViewModelFactory
import com.example.dagger2.presentation.UserViewModel
import com.example.dagger2.resource.Resource
import com.example.dagger2.resource.ResourceState
import com.example.dagger2.view.UserViewGenerator
import com.example.myapplication.Users
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_second.*
import javax.inject.Inject

class SecondFragment : Fragment() {
    lateinit var userViewModel: UserViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        userViewModel = ViewModelProviders.of(this,viewModelFactory)[UserViewModel::class.java]
        userViewModel.userList.observe(this, Observer{ showList(it)})
        setView()
        super.onActivityCreated(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }
    fun setView(){
        phv_user.builder
            .setHasFixedSize(false)
            .setItemViewCacheSize(10)
            .setLayoutManager(
                LinearLayoutManager(
                    this.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            )
    }

    fun showList(resource : Resource<List<Users>>?){
        resource?.let {
            when(it.state){
                ResourceState.LOADING -> av_loading.show()
                ResourceState.SUCCESS -> av_loading.hide()
                ResourceState.ERROR -> av_loading.hide()
            }

            it.data?.let{
                for(i in 0 until it.size){
                    phv_user.addView(
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

}

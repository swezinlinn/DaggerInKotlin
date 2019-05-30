package com.example.dagger2


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dagger2.di.viewmodel.ViewModelFactory
import com.example.dagger2.presentation.UserViewModel
import com.example.dagger2.resource.Resource
import com.example.dagger2.resource.ResourceState
import com.example.dagger2.view.UserViewGenerator
import com.example.myapplication.Users
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.av_loading
import kotlinx.android.synthetic.main.fragment_first.phv_user
import javax.inject.Inject

class FirstFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var userViewModel : UserViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)
        userViewModel = ViewModelProviders.of(this,viewModelFactory)[UserViewModel::class.java]
        userViewModel.userList.observe(this, Observer { showList(it) })
        setView()
        button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.goToSecond))
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
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

    private fun showList(resource: Resource<List<Users>>?) {
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

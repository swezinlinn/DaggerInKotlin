package com.example.dagger2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import javax.inject.Inject
import android.util.Log
import com.example.dagger2.network.Api
import com.example.dagger2.network.response.UserList
import com.mindorks.placeholderview.PlaceHolderView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var api: Api

    @BindView(R.id.phv_user)
    lateinit var phvUser: PlaceHolderView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as DaggerApplication).getAppComponent().inject(this) // Injecting using `ComputerComponent` initialised in application class
        ButterKnife.bind(this)
        setView()
        api.users.enqueue(object : Callback<UserList> {
            override fun onFailure(call: Call<UserList>, t: Throwable) {
                Log.d("TAG", "on failure${t.printStackTrace()}")

            }

            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if(response.isSuccessful){
                    Log.d("TAG", "on success")

                    for(i in 0 until response.body()!!.users!!.size){
                        phvUser.addView(ViewGenerator(response.body()!!.users!![i]) )
                    }
                }else{
                    Log.d("TAG", "on failure")
                }
            }
        })
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


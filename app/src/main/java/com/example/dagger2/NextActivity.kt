package com.example.dagger2

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife

import kotlinx.android.synthetic.main.activity_next.*
import retrofit2.Retrofit
import javax.inject.Inject

class NextActivity : AppCompatActivity() {

    @Inject
    lateinit var retrofit: Retrofit

    @BindView(R.id.txv_next_hello)
    lateinit var txvNextHello : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        setSupportActionBar(toolbar)
        ButterKnife.bind(this)

        (application as DaggerApplication).getAppComponent().inject(this) // Injecting using `ComputerComponent` initialised in application class


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}

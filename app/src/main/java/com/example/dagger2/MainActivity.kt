package com.example.dagger2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var info: Info

    @BindView(R.id.txv_hello)
    lateinit var textHello: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMagicBox.create().poke(this)

        ButterKnife.bind(this)

        textHello.text = info.text
    }

    @OnClick(R.id.button)
    fun onClick(){
        startActivity(Intent(this,NextActivity::class.java))
    }
}


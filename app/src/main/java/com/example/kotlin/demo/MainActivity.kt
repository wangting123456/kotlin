package com.example.kotlin.demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlin.R
import kotlinx.android.synthetic.main.activity_rxjava1.*

class MainActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava1)
        tv_init.setOnClickListener{
            //val intent = Intent(this@MainActivity.c)
        }
        val user:User = User()

        user?.name
        user?.age
        user?.let{
            user.name
            user.age
        }


    }

}
package com.example.kotlin.kotlin_basic.jihe

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 *plus 与 minus 操作符
 */
class MainActivity9:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = listOf("one","two","three","four")
        list.plus("five")
        Log.d("TAG,",""+list)
        list.minus("two")
        Log.d("TAG,",""+list)
        val list2 = list + "six"
        val list3 = list - listOf("one","two")
    }
}
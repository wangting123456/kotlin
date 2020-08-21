package com.example.kotlin.kotlin_basic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin.R
import java.lang.reflect.Array.get

/**
 * 扩展函数
 */
class KuoZhan:AppCompatActivity() {
    val TAG:String = "KuoZhan"
    //扩展属性
    val <T> List<T>.lastIndex: Int
        get() = size - 1
    companion object {

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava1)
        //扩展函数
        Log.d(TAG,"${"nihao".sayHello()}")
        val str =  StringBuffer()
        str.append("121")
        Log.d(TAG,"${str.to1()}")
        //扩展属性
        val index = listOf(1,2,3).lastIndex
        Log.d(TAG,"$index")
        //扩展伴生对象
        KuoZhan.Companion.foo()
    }
    fun String.sayHello():String{
        return "hello$this"
    }
    fun StringBuffer.to1():String{
        return "123$this"
    }
    fun KuoZhan.Companion.foo(){
        Log.d(TAG,"Companion foo")
    }
}
package com.example.kotlin.kotlin_basic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin.R

/**
 * 空安全
 */
class Null:AppCompatActivity() {
    val TAG:String = "Null"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava1)
        //可空类型对象的方法时需加?，如果此对象确实是null，则方法表达式结果最终返回null。
        val name:String? = null
        val y = name?.length
        Log.d(TAG,"$y")
        //在调用函数过程中可进行验空判断使用?:操作符，后面可以跟对象或表达式。
        val x:Int? = null
        val z = x?.toLong() ?: 0
        Log.d(TAG,"$z")
        x?.toLong() ?: Log.d(TAG,"12133")
        //如果确认访问的对象一定不是空可使用!!操作符告诉编译器此处空指针的检查。
        val a:Int? = 3
        val b = a!!.toLong()
        Log.d(TAG,"$b")
    }
}
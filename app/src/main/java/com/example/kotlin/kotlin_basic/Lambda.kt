package com.example.kotlin.kotlin_basic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.kotlin.R
import com.example.kotlin.databinding.ActivityRxjava1Binding

/**
 * 函数和Lambda表达式
 */
class Lambda:AppCompatActivity() {
    val TAG:String = "Lambda"
    lateinit var mBinding:ViewDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_rxjava1)
        //函数
        val a = add(3)
        Lambda().add(4)
        Log.d(TAG,"$a")
        foo( b=2)
        foo2 {
            Log.d(TAG,"foo $a")
        }
        foo2(1){
            Log.d(TAG," foo2 $a")
        }
        foo2(block = { Log.d(TAG," foo3 $a")})

    }
    //参数没默认值
    fun add(a:Int):Int{
        return a*2;
    }
    //参数给默认值
    fun read(a:Int = 1,b:String = "2"){

    }
    //覆盖方法
    open class A{
       open fun work(a:Int = 10){

        }
    }

    //不能有默认值
    class B:A(){
        override fun work(a: Int) {
            super.work(a)
        }
    }

    fun foo(a:Int = 1,b:Int){
        Log.d("Lambda","$a, $b")
    }
    fun foo2(a:Int = 1,b:Int = 2,block:()->Unit){

    }

    //单表达式函数
    fun double(a:Int):Int = a*2

}
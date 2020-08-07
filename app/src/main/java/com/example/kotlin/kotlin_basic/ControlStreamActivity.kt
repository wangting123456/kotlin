package com.example.kotlin.kotlin_basic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * 控制流：if、when、for、while
 */
class ControlStreamActivity:AppCompatActivity() {
    val TAG = "ControlStreamActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.If 表达式
          //a.在 Kotlin 中，if是一个表达式，即它会返回一个值。
          iftest()
          //b.If 的分支可以是代码块，最后的表达式作为该块的值：
           var a = 10
           var b = 12
           val max = if(a > b) {
               println("111")
               a
           } else{  println("222")
               b}
        //2.when 取代了类 C 语言的 switch 操作符。其最简单的形式如下
          whentest()
        //3.for 循环可以对任何提供迭代器（iterator）的对象进行遍历，这相当于像 C# 这样的语言中的 foreach 循环
          fortest()
    }
    /**
     * if表达式
     */
    fun iftest(){
        var a = 10
        var b = 12
        var max = if(a > b) a else b
        Log.d(TAG,"max:"+ max)

        //传统方式
        if(a > b){
            max = a
        }else{
            max = b;
        }
        Log.d(TAG,"max:"+ max)
    }

    /**
     * when表达式
     * when会把参数和所有分支顺序比较，直到某个分支满足条件
     */
    fun whentest(){
       var x = 0
        when(x){
            0,1 -> Log.d(TAG,"X == 0 or X == 1")
            else ->Log.d(TAG,"other")
        }

        when(x){
            0 ->  Log.d(TAG,"X == 0")
            1 ->  Log.d(TAG,"X == 1")
            else ->{
                Log.d(TAG,"X == other")
            }
        }

        when(x){
            in 0..9 ->Log.d(TAG,"在区间范围内")
            else -> Log.d(TAG,"不在")
        }
    }

    /**
     * for
     * 通过索引遍历一个array /list
     */
    fun fortest(){
        var arr = intArrayOf(1,2,3)
        //indices 指数的意思
        for(i in arr.indices){
            Log.d(TAG,"fortest:"+arr[i])
        }
        //打印 1，2，3

        //for在数字区间上迭代
        for (i in 1..3){
            Log.d(TAG,"fortest in :"+i)
        }
        //打印 1，2，3
    }

}
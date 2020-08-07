package com.example.kotlin.kotlin_basic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * 返回和跳转
 */
class ReturnAndBreakActivity :AppCompatActivity() {
    val TAG = "ReturnAndBreakActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //return:默认从最直接包围它的函数返回
        //break;终止最直接包围它的循环
        //continue:继续下一次最直接包围它的循环

        //标签
        loop1@ for(i in 1..10){
            //Log.d(TAG,"for:"+i)
        }
        test()
        test2()
    }

    /**
     * 终止循环
     */
    fun test(){
        for (i in 1..10){
            for (j in 1..5){
                Log.d(TAG,"i:"+i+"j:"+j)
                if(i == 5){
                    break
                }
            }
        }

        looptest@for (i in 1..10){
            for (j in 1..5){
                Log.d(TAG,"i:"+i+"j:"+j)
                if(i == 5){
                    break@looptest
                }
            }
        }
    }

    /**
     * 返回到标签
     */
    fun test2(){
        listOf(1,2,3,4,5).forEach {
            if(it == 3) return //返回到test2()的调用着
            Log.d(TAG,""+it)
        }
        Log.d(TAG,"this point is unreachable")

        //如果我们需要从lambda表达式中返回，需要加标签，并用以限制return
        listOf(1,2,3,4,5).forEach lit@{
            if(it == 3) return@lit
            Log.d(TAG,""+it)
        }
        Log.d(TAG,"done with explicit label")


        listOf(1,2,3,4,5).forEach {
            if(it == 3) return@forEach
            Log.d(TAG,""+it)
        }
        Log.d(TAG,"done with implicit label")

        listOf(1,2,3,4,5).forEach(fun (value:Int){
            if(value == 3) return
            Log.d(TAG,""+value)
        })
        Log.d(TAG,"done with anonymous label")


        //return@lit 1:意为“返回 1 到 @a”
    }
}
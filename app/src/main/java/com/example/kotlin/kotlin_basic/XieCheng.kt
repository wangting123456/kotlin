package com.example.kotlin.kotlin_basic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin.R
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * 协程
 */
class XieCheng:AppCompatActivity() {
    val TAG:String = "XieCheng"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava1)
        //协程挂起而非阻塞
        //轻量级线程
        //按串行模式完成异步编码

        //启动协程的方法1
        xiecheng1()
        test()
        jointest()
        //启动协程的方法2
        testAsync()
        //取消协程
        cancelXie()
        cancel2()
        //协程上下文和调度
        testDispatchers()
    }
    fun xiecheng1(){
        Log.d(TAG,"launch 0")
        //这意味着新协程的生命周期只受整个应用程序的生命周期限制。
        GlobalScope.launch {
            Log.d(TAG,"launch 1")
            delay(2000)
            Log.d(TAG,"launch 2")
        }

        Log.d(TAG,"Thread start")
        Thread.sleep(3000)
        Log.d(TAG,"Thread end")

        //08-19 15:03:43.360 28033-28033/com.example.myapplication D/XieCheng: launch 0
        //08-19 15:03:43.360 28033-28033/com.example.myapplication D/XieCheng: Thread start
        //08-19 15:03:43.360 28033-28057/com.example.myapplication D/XieCheng: launch 1
        //08-19 15:03:45.360 28033-28057/com.example.myapplication D/XieCheng: launch 2
        //08-19 15:03:46.360 28033-28033/com.example.myapplication D/XieCheng: Thread end
        //协程不阻塞线程，sleep组塞线程

    }

    fun test() = runBlocking{
        Log.d(TAG,"runBlocking start")
        GlobalScope.launch {
            //delay函数就是一个挂起函数，用suspend修饰
            delay(2000)
            Log.d(TAG,"runBlocking finish")
        }

        Log.d(TAG,"runBlocking 111")
        delay(2000)
        Log.d(TAG,"runBlocking 222")
    }

    fun jointest() = runBlocking {
        Log.d(TAG,"jointest start")
        val job = GlobalScope.launch {
            //delay函数就是一个挂起函数，用suspend修饰
            delay(2000)
            Log.d(TAG,"jointest finish")
        }
        //等待协程执行完毕
        job.join()
        Log.d(TAG,"jointest complete")
    }
    //async协程必须指定调度器。
    fun testAsync() = runBlocking(Dispatchers.Default) {
        Log.d(TAG,"Thread  ${Thread.currentThread()}")
       val time = measureTimeMillis {
            val one = GlobalScope.async(Dispatchers.Unconfined) {
                delay(2000)
                Log.d(TAG,"Thread one ${Thread.currentThread()}")
            }
            val two = GlobalScope.async(Dispatchers.Unconfined) {
                delay(2000)
                Log.d(TAG,"Thread two ${Thread.currentThread()}")
            }
            //await 获取异步协程的结果
            Log.d(TAG,"${one.await()}+${two.await()}")
        }
        Log.d(TAG,"completed in :$time ms")
    }

    fun cancelXie() = runBlocking {
       val job = GlobalScope.launch {
            repeat(100){
                Log.d(TAG,"repeat $it")
                delay(300)
            }
        }
        delay(2000)
        job.cancel()
        Log.d(TAG,"cancelXie cancel")
    }
    //cancel可以取消一个正在运行的挂起函数，但是不能取消一个计算函数，此时可能需要判断协程状态。
    fun cancel2() = runBlocking {
       val job = GlobalScope.launch {
            var i = 0
            while (i < 1000){
                if(!isActive){
                    return@launch
                }
                if(i % 7 == 0) {
                    Log.d(TAG,"coroutine executing i:$i")
                }
                i++
            }
           Log.d(TAG,"coroutine finish thread:${Thread.currentThread()}")
       }
        delay(200)
        job.cancel()
        println("test func coroutineCancelTest2 end")
    }
    fun testDispatchers() = runBlocking {
        println("[testDispatchers] start thread:${Thread.currentThread()}")
        val jobs = arrayListOf<Job>()
        jobs.add(GlobalScope.launch(Dispatchers.Unconfined) {
            println("Unconfined is working thread:${Thread.currentThread()}")
        })

        jobs.add(GlobalScope.launch(Dispatchers.Main) {
            println("Main is working thread:${Thread.currentThread()}")
        })

        jobs.add(GlobalScope.launch(Dispatchers.Default) {
            println("Default is working thread:${Thread.currentThread()}")
        })

        jobs.add(GlobalScope.launch(newSingleThreadContext("myThread")) {
            println("newSingleThreadContext is working thread:${Thread.currentThread()}")
        })

        jobs.forEach {
            it.join()
        }
        println("testDispatchers....... end")
    }
}
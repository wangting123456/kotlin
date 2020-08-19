package com.example.kotlin.yangchong

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.R

/**
 * Kotlin类介绍
 */
class MainActivity4:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava1)
        //1.kotlin类
          //a.kotlin如何表示类
            //使用 Kotlin 来声明 Point 类则只需要一行代码，两者完全等同
            class Point(x:Int,y:Int){

            }
          //b.kotlin中默认是public，final的，不可被继承
          //c.没有new关键字

        //2.internal inner
            //internal模块内可见，inner内部类
        //3.抽象类
        //4.嵌套类
        //5.匿名内部类
        //6.内部类
        //7.枚举类
        //8.kotlin单例模式
        val items = listOf{
            "给初学者的RxJava2.0教程（七）: Flowable"
            "Android之View的诞生之谜"
            "Android之自定义View的死亡三部曲之Measure"
            "Using ThreadPoolExecutor in Android "
            "Kotlin 泛型定义与 Java 类似，但有着更多特性支持。"
            "Android异步的姿势，你真的用对了吗？"
            "Android 高质量录音库。"
            "Android 边缘侧滑效果，支持多场景下的侧滑退出。"
        }
       val recyclerView = findViewById(R.id.recycleView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
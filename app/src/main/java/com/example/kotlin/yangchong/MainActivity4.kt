package com.example.kotlin.yangchong

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Kotlin类介绍
 */
class MainActivity4:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }
}
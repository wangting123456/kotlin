package com.example.kotlin.kotlin_classandobject

import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

/**
 * 嵌套类和内部类
 */
class MainActivity9:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //嵌套类使用
        val demo1 = Outer.Nested().foo()
        Log.d("Outer","demo1:"+demo1)
        val demo2 = Outer1().Demo().foo()
        Log.d("Outer1","demo2:"+demo1)
    }
    //1.嵌套类
    /**
     * 嵌套类
     * 类可以嵌套在其他类中
     */
    class Outer{
        private val bar:Int = 1
        class Nested{
            fun foo(){
                Log.d("Outer","foo()")
            }
        }
    }

    //2.内部类
    /**
     * 内部类
     * 标记为inner的嵌套类能够访问其外部类的成员，内部类会带有一个对外部类对象的引用
     */
    class Outer1{
        private val bar = 1
        inner class Demo{
            fun foo() = bar
        }
    }

    //3.匿名内部类
    /**
     * 匿名内部类
     * 使用对象表达式创建匿名内部类实例
     */


}
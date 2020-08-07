package com.example.kotlin.kotlin_classandobject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * 数据类
 *
 */
class MainActivity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //复制
        val user = User("zhangsan", 12)
        val otherUser = user.copy(age = 2)
        //解构声明
        val user1 = User("lisi", 23)
        val (name, ager) = user1
        Log.d("解构声明", "$name  is $ager years old")
    }
    //1.数据类
    /**
     * 数据类
     * 我们经常创建一些只保存数据的类。数据类，标记为data
     */
    data class User(val name: String, var age: Int) {

    }

    //2.在类体中声明的属性
    /**
     * 在类体中声明的属性
     */
    data class Person(var name: String) {
        val age: Int = 10
    }

    //3.复制
    /**
     * 复制
     * 在很多情况下，我们需要复制一个对象，改变它的一些属性，但其余部分保持不变
     */
    //如onCreate 函数里面

    //4.数据类与解构声明
    /**
     * 数据类与解构声明
     */
}
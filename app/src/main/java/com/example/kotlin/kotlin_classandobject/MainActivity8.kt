package com.example.kotlin.kotlin_classandobject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 泛型
 */
class MainActivity8:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    //1.泛型
    /**
     * 与java类似，kotlin中也可以有类型参数
     */
    class Box<T>(t:T){
        val value = t
    }
    //一般情况下创建类的实例，我们需要提供类型参数
    val box:Box<Int> = Box<Int>(1)
    //但是如果类型参数可以推断出来，可以省略类型参数
    val box1 = Box(1)

    //2.型变
    /**
     * 型变
     *
     */
}
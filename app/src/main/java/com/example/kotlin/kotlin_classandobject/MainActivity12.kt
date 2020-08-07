package com.example.kotlin.kotlin_classandobject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.File

/**
 * 类型别名
 */
class MainActivity12:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //typealias A = A.Inner
        //typealias B = B.Inner
    }
    //1.类型别名
    /**
     * 类型别名
     * 现有类型声明太长，引入短的名称
     */
    //泛型别名
    //typealias FileTable<K> = MutableMap<K, MutableList<File>>

    //函数别名
    //typealias myHandler = (Int, String) -> Unit

    //同名类型别名
    class A{
        inner class Inner(){

        }
    }
    class B{
        inner class Inner(){

        }
    }

}


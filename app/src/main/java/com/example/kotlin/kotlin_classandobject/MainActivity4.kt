package com.example.kotlin.kotlin_classandobject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 可见性修饰符
 */
class MainActivity4:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //在kotlin中有四种可见性修饰符
          //private protected public internal 如果没有显示指定修饰符的话，默认是public
    }

    //1.包
    /**
     * 包
     *函数，属性，类，对象和接口可以在顶层声明，直接在包内
     */
    private fun foo(){           //private只会在声明它的文件内可见

    }
    public val age:Int = 27       //public随处可见
    internal val name:String = "1"//internal相同模块内可见

    //2.类和接口

    //3.构造函数
    /**
     * 构造函数
     * 要指定一个类的主构造函数的可见性，使用以下语法
     */
    class Person private constructor(name:String){

    }

    //4.模块
    /**
     * 模块
     * 可见性修饰符internal意味着该成员只在相同模块内可见
     */

    
}

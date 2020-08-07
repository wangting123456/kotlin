package com.example.kotlin.kotlin_classandobject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

//
 import com.example.myapplication.kotlin_demo.kotlin_classandobject.test.getLongestString

/**
 * 扩展
 * kotlin能够扩展一个类的新功能，而无需继承该类
 */
class MainActivity5:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = mutableListOf(1,2,3)
        for (i in list.indices){
            Log.d("mutableListOf","list:i"+list[i])
        }

        list.swap(0,1)
        for (i in list.indices){
            Log.d("mutableListOf","swap list:i"+list[i])
        }

        //伴生对象的扩展
        MyClass.myPrint()

        //扩展的作用域
        val list2 = mutableListOf(1,2,3,4,5)
        list2.getLongestString()
    }
    //1.扩展函数
    /**
     * 扩展函数
     * 我们需要用一个接收者类型，也就是被扩展的类型来作为他的前缀
     * 这个是MutableList<Int> 添加的swap函数
     */
     fun MutableList<Int>.swap(index1:Int,index2:Int){
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }

    //2.扩展是静态解析的
    /**
     * 扩展是静态解析的
     * 扩展不能真正的修改他们所扩展的类，通过定义一个扩展，你并没有在一个类中插入新成员，仅仅是通过该
     * 类型的变量通过点的方式调用这个新函数
     */

    //2.可空接收者
    /**
     * 可空接收者
     * 可以为可空的接收者定义扩展
     */
    fun Any?.toString():String{
        if(this == null){
            return "null"
        }
        return toString()
    }

    //3.扩展属性
    /**
     * 扩展属性
     *与函数相似，kotlin支持扩展属性
     */
    val <T> List<T>.lastIndex:Int
        get() = size -1
    //4.伴生对象的扩展
    /**
     * 伴生对象的扩展
     * 如果一个类定义了一个伴生对象，你也可以为伴生对象扩展属性和函数，就像伴生对象的常规成员一样
     */
    class MyClass{
        companion object{

        }
    }
     fun MyClass.Companion.myPrint(){
         Log.d("Companion","myPrint")
     }
    //使用:只使用类名限定符来调用伴生对象的扩展函数

    //5.扩展的作用域
    /**
     * 扩展的作用域
     * 要使用所定义之外的扩展，需要导入包
     */

    //6.扩展声明为成员
    /**
     * 扩展声明为成员
     */



}
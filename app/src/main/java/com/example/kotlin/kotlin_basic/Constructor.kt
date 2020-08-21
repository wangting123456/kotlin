package com.example.kotlin.kotlin_basic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin.R

/**
 * 类与构造函数
 */
class Constructor:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava1)
        //继承:
             //必须声明open关键字表示可以继承或复写，也就是说默认情况下类、方法、成员变量默认具有final属性。
             //由于使用override关键字声明的方法或属性默认是open的，如果不想继续被复写需再声明final关键字。
        //构造函数:主构造函数以及一个或多个次构造函数。主构造函数是类头的一部分：它跟在类名（与可选的类型参数）
             //后，以constructor关键字声明。如果该类没有修饰符或注解可以省略constructor关键字。
        //init初始化代码块
            //由于主构造函数不能包含其他代码，kotlin提供了init代码块用作类的初始化。init 代码块可以有多个，
              //执行顺序即为在类中的出现顺序
    }

    //声明为open类
    open class Person:testInterface{
        override fun get() {
            TODO("Not yet implemented")
        }
        //声明open方法
        open fun work(){

        }

    }
    //Student 继承Person
    class Student:Person(){
        //必须显示声明override
        override fun work() {
            super.work()
        }
    }

    class Employee:Person(){
       final override fun work() {
            super.work()
        }
    }

    interface testInterface{
        fun get()
    }
    //显示声明主构造器
    class Factory constructor(name:String){

    }
    //缺省constructor关键字
    class Factory1(name: String)

    //主构造器私有
    class Factory2 private constructor(name:String){

    }

    //构造参数可缺省，相当于同时声明了一个无参的构造函数
    class Factory3 constructor(name: String = ""){

    }

    //声明主构造函数
    class Factory4(name: String){
        //声明次构造函数，必须显示调用主构造函数
        constructor(name: String,parent:Factory4):this(name)
    }

    //无主构造函数
    class Factory5{
        //声明一个或多个次构造函数
        constructor(name: String,parent: Factory5)
        constructor(name: String)
    }

    class InitBlock(name: String){
        fun test(){

        }
        //属性初始化 按照在类中的出现顺序 它先于init代码块
        val first = "${name.also(::println)}"
        init {
            println("First initializer block that prints $name")
        }
    }

}
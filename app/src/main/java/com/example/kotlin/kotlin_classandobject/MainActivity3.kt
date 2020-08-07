package com.example.kotlin.kotlin_classandobject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * 接口
 */
class MainActivity3:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Employee("zhang","san",1)
    }

    //1.接口的定义
    /**
     * 接口的定义
     */
    interface MyInterface{
        fun foo()
        fun draw(){
            Log.d("MyInterface","MyInterface draw")
        }
    }

    //2.实现接口
    /**
     * 实现接口
     * 一个类或者对象可以实现一个或多个接口
     */
    class child:MyInterface{
        override fun foo() {
            Log.d("MyInterface","child foo()")
        }
    }
    //3.接口中的属性
    /**
     * 接口中的属性
     */
    interface MyInterface2{
        var pop:Int //抽象的
    }
    class myChild :MyInterface2{
        override var pop: Int
            get() = 1
            set(value) {}
    }

    //4.接口继承
    /**
     * 接口继承
     * 一个接口可以从其他接口派生，从而提供原有属性和新的属性，很自然的，实现这样的接口，只需要实现缺少的属性
     */
    interface  Name{
        var name:String
    }
    interface Person:Name{
        val firstName:String
        val lastName:String
        override var name: String
            get() = "$firstName $lastName"
            set(value) {}
    }
    data class Employee(override val firstName: String, override val lastName: String,val postion:Int):Person{
         init {
             Log.d("Employee","name:"+name)
         }
    }

    //5.解决接口冲突
    /**
     * 解决接口冲突
     */
    interface A{
        fun foo(){
            Log.d("interface","foo A")
        }
        fun bar()//没有实现，认为是抽象的
    }
    interface B{
        fun foo(){
            Log.d("interface","foo B")
        }
        fun bar(){
            Log.d("interface","bar B")
        }
    }
    class C :A{
        override fun bar() {
            Log.d("interface","bar C")
        }
    }
    class D:A,B{
        override fun foo() {
            super<A>.foo()
            super<B>.foo()
        }

        override fun bar() {
            super.bar()
            super<B>.bar()
        }
    }


}
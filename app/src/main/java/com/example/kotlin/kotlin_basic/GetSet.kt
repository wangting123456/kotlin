package com.example.kotlin.kotlin_basic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin.R

/**
 * 成员变量和get set方法
 */
class GetSet:AppCompatActivity() {
    val TAG:String = "GetSet"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava1)
        //成员变量
        val p = Person()
        p.age = 10
        Log.d(TAG,"${p.age}")
        val s = Student()
        Log.d(TAG,"${s.name}")
        p.getUpper2()
        //Getter Setter
        Log.d(TAG,"${ s.grade}")
        s.grade = 100
        Log.d(TAG,"${ s.grade}")
    }
    open class Person{
        //成员变量
         var age:Int? = null
        //属性声明open表示可重写
        open val name:String = ""
        //延迟初始化
        lateinit var str:String

        fun getUpper():String{
            return str.toUpperCase()
        }
        fun getUpper2(){
            if(::str.isInitialized){
                println("str is isInitialized")
            }else{
                println("str not isInitialized")
            }
        }
    }
    open class Student:Person(){
        override val name: String = "zhangsan"
        var grade:Int? = 10
           set(value) {
               Log.d("111","$value")
               field = value
           }
    }
}
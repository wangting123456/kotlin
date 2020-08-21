package com.example.kotlin.kotlin_basic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin.R
import java.lang.reflect.Array.set
import java.util.*
import kotlin.random.Random.Default.nextInt

/**
 * 范围函数(let,run,apply,also,with)
 */
class letApplay:AppCompatActivity() {
    val TAG:String = "letApplay"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava1)
        //定义:
          //它会形成一个临时范围。在此范围内，您可以在不使用其名称的情况下访问该对象。这些函数称为范围函数。
          //有5个人：let，run，with，apply，和also。
        //let:
          //功能：在函数块内可以通过 it 指代该对象。返回值为函数块的最后一行或指定return表达式。
        //run:
          //功能：调用run函数块。返回值为函数块最后一行，或者指定return表达式。
        //apply:
          //功能：调用某对象的apply函数，在函数块内可以通过 this 指代该对象。返回值为该对象自己。
        //also:
          //功能：调用某对象的also函数，则该对象为函数的参数。在函数块内可以通过 it 指代该对象。返回值为该对象自己。
        //with:
          //功能：它是将某对象作为函数的参数，在函数块内可以通过 this 指代该对象。返回值为函数块的最后一行或指定return表达式。
        let()
        let2()
        run()
        apply()
        also()
        with()
    }

    fun let(){
        //源码
        val p = Person("zhangsan",26)
        p.age = 30
        p.name = "zhangsi"

        val w = p.let {
            it.age = 20
            it.name = "zhangsi"
            Log.d("letApplay","${it.name}+${it.age}")
            println()
        }
    }
    fun let2(){
       val nums = mutableListOf("one", "two", "three", "four", "five")
       val res = nums.map {
            it.length
            //it.contains("f")
        }.filter {
            it > 3
            //true
        }
        Log.d("letApplay res","$res")//[false, false, false, true, true] //[5, 4, 4]

        val res2 = nums.map {
            it.length
            //it.contains("f")
        }.filter {
            it > 3
            //true
        }.let {
            Log.d("letApplay res2","$it")
        }

        nums.map {
            it.length
        }.filter {
            it > 3
        }.let(::println)//:是this的引用
    }
    fun run(){
        var str = "abcsdjdjg"
        val run1 = str.run {
            this.length
        }
        Log.d("letApplay run","$run1")
    }

    fun  apply(){
       val p = Person("lis",24)
       val p2 = p.apply {
            p.age = 12
            p.name = "12e"
        }
        Log.d("letApplay apply","${p2.age}")
    }
    fun also(){
        "qwewewr".also { it ->
            Log.d("letApplay also","$it")
         }
    }

    fun with(){
        val numbers = mutableListOf("one", "two", "three")
       val with =  with(numbers){
           numbers.remove("one")
           numbers.add(0,"12")
       }
        Log.d("letApplay with","$numbers")
    }

    data class Person(var name:String,var age:Int)
}
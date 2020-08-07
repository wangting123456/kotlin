package com.example.kotlin_start

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * 基本语法
 */
class MainActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.包的定义和导入
        //2.程序入口点
        //3.函数
           //a.带有两个Int参数，返回Int类型
           //b.将表达式作为函数体、返回值类型自动推断的函数：
           //c.函数返回无意义的值
           //d.Unit 返回类型可以省略
        //4.变量
           //a.定义只读局部变量使用关键字 val 定义。只能为其赋值一次
               val a = 3
           //b.可重新赋值的变量使用 var 关键字：
               var b = 5
               b++
        //5.注释
           //a.与大多数现代语言一样，Kotlin 支持单行（或行末）与多行（块）注释。
        //6.字符串模板
           //a.模板中的简单名称：
            val c = 1
            val s1 = "a is $c"
           //b.模板中的任意表达式
            val s2 = "${ s1.replace("is","was")} , but now is $a}"
        //7.条件表达式
            //a.
            fun maxof(a:Int,b:Int):Int{
            if(a > b)
                return a
            else return b
            }
           //b
           fun maxof2(a:Int,b:Int) =  if(a > b) a else b
        //8.空值与 null 检测
           //a.当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空
            fun parseInt(str:String):Int?{
               return 0
            }
        //9.类型检测与自动类型转换
           //a.is运算符检测一个表达式是否某类型的一个实例：
             //如果一个不可变的局部变量或属性已经判断出为某类型，那么检测后的分支中可以直接当作该类型使用，无需显式转换
            fun getStringLendth(str:String):Int?{
               if(str is String){
                   return str.length
               }
               return null
           }
        //10.for 循环
          //a.
          val list = listOf("apple","banana","pear")
          for (item in list){
              Log.d("TAG","item:"+item)
          }
          //b.
          for (index in list.indices){
              Log.d("TAG","$index is ${list[index]}")
          }
        //11.while 循环
           var idx = 0
           while (idx < list.size){
               Log.d("TAG","$idx is ${list[idx]}")
               idx++
           }
        //12.when 表达式
          fun des(obj:Any):String =
            when(obj){
                1 -> "one"
                2 -> "two"
                "hello" ->"hello"
                else -> "unknown"
            }
        //13.使用区间（range）
          //a.使用in 运算符来检测某个数字是否在指定区间
             val x = 1
             val y = x+1
             if(x in 1.. y+1){
                 Log.d("TAG","in")
             }
           //b.区间迭代
             for (x in 1..5){
                 Log.d("TAG","x:"+x)
             }
            //或数列迭代：
             for (x in 1..10 step 2) {
                 Log.d("TAG","数列迭代x:"+x)
             }
             println()
               for (x in 9 downTo 0 step 3) {
                   Log.d("TAG","数列迭代 x2:"+x)
               }
         //14.集合
             //a.对集合进行迭代
             val items = listOf("apple","apple2","apple3","apple4","banana","pear")
             for (item in items) {
                 Log.d("集合","item:"+item)
              }
              //b.用 lambda 表达式来过滤（filter）与映射（map）集合：
             items
                 .filter { it.startsWith("a") }
                 .map { it.toUpperCase() }
                 .sortedBy { it }
                 .forEach { Log.d("11","startsWith:"+it) }
         //15.创建基本类及其实例
             //val rectangle = Rectangle(5.0, 2.0)
    }

    /**
     * 函数
     * 带有两个Int参数，返回Int类型
     */
    fun sum(a:Int,b:Int):Int{
        return a+b
    }
    /**
     * 将表达式作为函数体，返回值类型自动推断
     */
    fun sum2(a:Int,b:Int) = a+b

    /**
     * 函数返回无意义的值
     */
    fun printSum(a:Int,b:Int):Unit{
        Log.d("printSum:","sum of $a + $b is ${a+b}")
    }
    //Unit 可以省略
    fun printSum2(a:Int,b:Int){
        Log.d("printSum2:","sum of $a + $b is ${a+b}")
    }


}
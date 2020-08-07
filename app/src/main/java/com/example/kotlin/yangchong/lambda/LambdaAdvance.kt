package com.example.kotlin.yangchong.lambda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LambdaAdvance:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //一.高阶函数介绍
          //1.在Kotlin中，高阶函数即指:将函数用作一个函数的参数
            val sum = "111".sumBy( { it.toInt() } )
             //但是根据Kotlin中的约定，即当函数中只有一个函数作为参数，并且您使用了lambda表达式作为相应的参数，
            println(sum)
            //把字符串中的每一个字符转换为Int的值，用于累加，最后返回累加的值
          //2.将函数用作一个函数的返回值的高阶函数。
             //lock
          //3.高阶函数的使用
             //高阶函数中对Lambda语法的简写
             //即当函数的最后一个参数是一个函数，并且你传递一个lambda表达式作为相应的参数，则可以在圆括号之外指定它
        //二.自定义高阶函数
             val test1 = resultbyOpt(1,2){
                 num1,num2 -> num1+num2
             }
             val test2 = resultbyOpt(3,4){
                num1,num2 -> num1 - num2
              }
              println(test1)
              println(test2)
         //三.常用的标准高阶函数介绍


    }
    private fun resultbyOpt(num1:Int,num2:Int,result:(Int,Int) ->Int):Int{
        return result(num1,num2)
    }


}
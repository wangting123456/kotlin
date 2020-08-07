package com.example.kotlin_funandlambda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
/**
 * 高阶函数与 lambda 表达式
 */
class MainActivity2:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.Lambda 表达式语法
          //a.Lambda 表达式的完整语法形式如下：
             //val sum(Int,Int)->Int = {x:Int,y:Int -> x+y}
          //b.Lambda表达式总是括在花括号中，完整语法形式的参数声明都放在花括号内，并有可选的类型标注
             //函数体跟在一个 -> 符号之后。如果推断出的该 lambda 的返回类型不是 Unit，那么该 lambda
             //主体中的最后一个（或可能是单个） 表达式会视为返回值。
          //c.如果把所有的可选标注都留下
             //val sum ->{x:Int,y:Int -> x+y }
        //2.传递末尾的 lambda 表达式
          //a.在 Kotlin 中有一个约定：如果函数的最后一个参数是函数，那么作为相应参数传入的 lambda 表达式可以放在圆括号之外：
             //val product = items.folds(1){acc,a -> acc*a}
             //这种语法也称为拖尾 lambda 表达式。
        //3.it:单个参数的隐式名称
          //a.一个 lambda 表达式只有一个参数是很常见的。如果编译器自己可以识别出签名，也可以不用声明唯
            //一的参数并忽略 ->。 该参数会隐式声明为 it
            //ints.filter{it > 0}
          //b.从 lambda 表达式中返回一个值

    }
}
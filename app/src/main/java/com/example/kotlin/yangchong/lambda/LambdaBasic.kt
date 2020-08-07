package com.example.kotlin.yangchong.lambda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LambdaBasic:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //2.语法
        //a.Lambda 表达式的语法格式如下：
        //(params) -> express
        //(params) ->{ statements}
        //b.可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
        //可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
        //可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
        //可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。

        //3.Lambda 表达式实例
        //a.不需要参数，返回值为5
        //()-> 5
        //b.接收一个参数(数字类型),返回其2倍的值
        //x -> x * 2
        //c.接受2个参数(数字),并返回他们的差值
        //(x,y)->{x - y}
        //d.接收2个int型整数,返回他们的和
        //(x:Int,y:Int)->{x+y}
        //e.接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)
        //(String s) -> System.out.print(s)

        //一.Lambda介绍
          //a.在java中，但是也是在Java8的时候才支持这种Lambda表达式。kotlin已经成熟使用。
          //b.Lambda表达式的本质是匿名函数，因为在其底层实现中还是通过匿名函数来实现的。同时也是代码变得更加简洁明了
        //二.Lambda使用
          //1.Lambda表达式的特点:
             //a.Lambda表达式总是被大括号括着
             //b.其参数(如果存在)在 -> 之前声明(参数类型可以省略)
             //c.函数体(如果存在)在 -> 后面
          //2.Lambda语法
             //a.无参数的情况
                //val 变量 = {操作的代码}
                //源代码
                fun test(){
                   println("nihao")
                 }
                //Lambda代码
                 val test2 = { println("111")}
                 //调用
                 test()
                 test2()
             //b.有参数的情况
                 //val/var 变量名 : (参数的类型，参数类型，...) -> 返回值类型 = {参数1，参数2，... -> 操作参数的代码 }
                 //源代码
                 fun add(a:Int,b:Int):Int{
                      return a+b
                 }
                 //Lambda代码
                 val add2:(Int,Int)->Int = {a,b -> a+b}
                 //或者
                 val add3 = {a:Int,b:Int ->a+b}
                  //调用
                  add(1,3)
                  add2(1,2)
                  add3(1,2)
             //c.Lambda表达式作为函数中的参数的时候
                 //源代码
                  fun test3(a:Int,b:Int):Int{
                     return a+b
                  }
                  fun sum(a:Int,b:Int):Int{
                      return a+b
                  }
                  test3(10,sum(3,5))
                 //Lambda代码
                  fun test4(a:Int,b:(num1:Int,num2:Int) -> Int):Int{
                     return a+b.invoke(3,5)
                 }
                 //调用
                  test4(10,{num1:Int,num2:Int -> num1+num2})
                 //a.定义完整的Lambda表达式如上面实例中的语法2，它有其完整的参数类型标注，与表达式返回值。
                    //当我们把一些类型标注省略的情况下，就如上面实例中的语法2的另外一种类型
                 //b.在上面例子中语法3的情况表示为：高阶函数，当Lambda表达式作为其一个参数时，只为其表达式提供了参数类型与返回类型，
                   //所以，我们在调用此高阶函数的时候我们要为该Lambda表达式写出它的具体实现。
          //3.Lambda实践
             //3.1.it
                    //a.it并不是Kotlin中的一个关键字
                    //b.it是在当一个高阶函数中Lambda表达式的参数只有一个的时候可以使用it来使用此参数。it可
                      //表示为单个参数的隐式名称，是Kotlin语言约定的
                      val list = listOf(1,3,5,7,9)
                      list.filter { it < 5 }
                      println(list.filter { it < 5 })
                      println(list.filter { it < 5 }.component1())
                    //c.例子
                      fun test(num1:Int, bool:(Int) -> Boolean):Int{
                      return if(bool(num1)) num1 else 0
                       }
                       println(test(10,{it > 5}))
                       println(test(4,{it > 5}))
                       //讲解：上面的代码意思是，在高阶函数test中，其返回值为Int类型，Lambda表达式以num1位条件。
                       //其中如果Lambda表达式的值为false的时候返回0，反之返回num1。故而当条件为num1 > 5这个条件时，
                       //当调用test函数，num1 = 10返回值就是10，num1 = 4返回值就是0。
          //3.2.下划线（_）
               //a.在使用Lambda表达式的时候，可以用下划线(_)表示未使用的参数，表示不处理这个参数。
                  val map = mapOf("key1" to "value1","key2" to "value2","key3" to "value3")
                /* map.forEach{
                     key,value -> println("$key ,$value")
                 }*/
                // map.forEach { _, value -> println(",$value") }
         //3.3 匿名函数
            //a.匿名函数的特点是明确指定其返回值
            //b.匿名函数没有函数名
              //常规函数
              fun testh(a:Int ,b:Int):Int{
                    return a+b
                }
              //匿名函数
              fun(a:Int ,b:Int):Int{
                  return a+b
              }
            //c.练习
             val t1 = fun(a:Int ,b:Int) = a+b
             val t2 = fun(a:Int ,b:Int):Int = a+b
             val t3 = fun(a:Int ,b:Int):Int{
                 return a+b
             }
             println(testh(1,1))
             println(t1(1,1))
             println(t2(1,2))
             println(t3(1,3))
            //d.匿名函数和Lambda表达式的区别：
              //在一个不带标签的return语句中，匿名函数时返回值是返回自身函数的值，而Lambda表达式的返回值是将包含它的函数中返回
    }
}
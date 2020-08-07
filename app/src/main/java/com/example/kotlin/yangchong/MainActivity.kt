package com.example.kotlin.yangchong

import android.graphics.Point
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * 基本语法
 */
class MainActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //一.常量和变量
          //1.变量
             //a.在 kotlin 中一切皆为对象，没有像 Java 中的原始基本类型。在 kotlin 中使用 var 修饰的为变量。
               //例如我们定义一个 Int 类型的变量并赋值为1：
             //b.由于 kotlin 编译器可以自动推断出变量的类型，所以我们通常不需要指定变量的类型
          //2.常量
             //a.在 kotlin 中使用 val 修饰的为常量。这和 java 中的 final 很相似。在 kotlin 中有一个重要的概念是：
               //尽可能地使用 val。
                 val str = "String"          //类型为String
                 val ll = 22L                //类型为Long
                 val d = 2.5                 //类型为Double
                 val f = 5.5f                //类型为Float
                 val point = Point(1,2)//类型为point对象
               //注意：
                  //1.注意这种情况下point一定不会为空，因为val定义的变量必须要初始化，从这个角度来看，又避免了人为导致的空指针错误。
                  //2.比如这样操作会产生编译时错误：point = Point(30, 30)
                  //3.需要注意的是，point不能被修改，但是Point类里面的成员变量是可修改的，所以下面操作是允许的
                   point.x = 30
                   point.y = 30
             //b.使用lateinit var，可以使得变量的初始化延迟到需要的时候
                 //1.比如在使用dagger的时候，需要inject,如下 //@Inject lateinit var mPresenter: MapHomePresent
                 //2.但是需要慎用lateinit, 因为你可能后面忘记了初始化，但是编译器又不会报错提醒。只有运行之后才会检测到
          //3.与Java区别
             //a.对于基本类型，Kotlin 相比 Java 有几点特殊的地方
               //数字、字符和布尔值可以在运行时表示为原生类型值，但对开发者来说，它们看起来就像普通的类
               //Kotlin 对于数字没有隐式拓宽转换，而在 Java 中 int 可以隐式转换为 long
               //在 Kotlin 中字符不能视为数字
               //Kotlin 不支持八进制
          //4.Any 和 Any?
             //a.Any 类型是 Kotlin 所有非空类型的超类型。包括像 Int 这样的基本数据类型
               //如果把基本数据类型的值赋给 Any 类型的变量，则会自动装箱
                val any:Any = 100
                 Log.d("1","any:"+any.javaClass)
             //b.如果想要使变量可以存储包括 null 在内的所有可能的值，则需要使用 Any?
                val any2: Any? = null
         //二.函数[相当于java方法]
           //1.无返回值的函数
               //Kotlin 中的 Unit 类型类似于 Java 中的 void，可以用于函数没有返回值时的情况
               //Unit 表示无返回值，对应 java 中 void：
                fun yc(a:Int,b:Int):Unit{
                   println("$a + $b  is ${a+b}")
                }
               //Unit 的返回类型可以省略：
                fun yc2(a:Int,b:Int){
                   println("$a + $b  is ${a+b}")
                 }
           //2.有返回值的函数
             /*override fun getContentView(): Int {
                 return R.layout.activity_wan_android
             }*/
           //3.构造方法
         //三.空安全
             //1.关于空安全
                //在 kotlin 中，默认定义的变量不能为 null 的，这可以避免很多的 NullPointerException。
                   //a.指定一个变量可null，是通过在类型后面增加一个？
                     var b:String? = "abc"
                     b = null
                   //b.当变量声明为可空时，调用它的属性会报错
                     //val len = b.length
                   //c.可以使用安全操作符?.
                     val len = b?.length
             //2.?.和!!.和?=各自的含义
                 //a.?=  当前面的值不为空取前面的值，否则取后面的值，这和java中三目运算符类似
                 //b.!!. 可以跳过限制检查通过编译，此时如果变量为空会抛出空指针异常
                 //c.?. 操作符，就先判空，如果不为空则赋值

                 //kotlin:     a?.foo()
                 //相当于java:
                 /* if(a!=null){
                     a.foo();
                   }*/

                 //kotlin: a!!.foo()
                 //相当于java:
                   /* if(a!=null){
                       a.foo();
                     }else{
                       throw new KotlinNullPointException();
                    }*/
             //3.?:的含义
                //左边的表达式没有成功，则使用右边的结果；

         //四.修饰符
             //1.public
             //2.protected
             //3.private
             //4.internal
             //5.final和open
        MainActivity2.lunch(this,true,false,11)

    }
    private var index:Int = 1         //定义具体的类型
    private var index2 = 2            //自动识别Int类型，通常也不需要指定类型
    private var point: Point? = null  //定义对象


}
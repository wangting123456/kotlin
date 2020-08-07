package com.example.kotlin.yangchong

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.lang.StringBuilder

/**
 * Kotlin函数
 */
class MainActivity3:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.with函数说明
           //a.with函数用于对同一个对象执行多次操作而不需要反复把对象的名称写出来
            //例如为了构建一个包含指定内容的字符串，需要先后如下调用
             main(arrayListOf("1"))
           //b.改为通过with函数来构建的话会简单很多
             val result = with(StringBuilder()){
                 append("1")
                 append("2")
                 toString()
             }
           //c.with 结构其实是一个接受两个参数的函数,在这个例子中就是一个 StringBuilder 和一个 Lambda
             //表达式，这里利用了把 Lambda 表达式放在括号外的约定
             //with 函数的返回值是执行 Lambda 表达式的结果，该结果就是 Lambda 中的最后一个表达式的返回值
             val result2 = with(StringBuilder()){
                 append("1")
                 append("2")
                 println()//println() 方法无返回值，所以打印出来的内容将是 kotlin.Unit
             }
        //2.apply函数说明
            //a.apply函数和with函数的唯一区别在于：apply函数始终会返回作为实参传递给它的对象
             val result3 = StringBuilder().apply {
                 append("1")
                 append("2")
                 toString()
             }
             Log.d("1","11:"+result3)
            //b.apply函数被声明为一个扩展函数，它的接收者变成了作为实参的 Lambda 的接受者
        //3.内联函数
           //a.下面是with函数的定义：
              //inline fun <T> with(t: T, body: T.() -> Unit) { t.body() }
              //这个函数接收一个T类型的对象，和一个被作为扩展函数的函数，它的实现仅仅是让这个对象去执行这个函数。
              //因为第二个参数是一个函数，所以我们可以把它放在圆括号外面，所以我们可以创建一个代码块
              //在这这个代码块中我们可以使用 this 和直接访问所有的public的方法和属性。
          //b.内联函数与普通的函数有点不同。
              //一个内联函数会在编译的时候被替换掉，而不是真正的方法调用。这在一些情况下可以减少内存分配和运行时开销。
        //4.自定义访问器
           //a.在java中，字段和其访问器的组合被称为属性
           //b.在 Kotlin 中，属性是头等的语言特性，完全替代了字段和访问器方法。在类中声明一个属性和声明
             //一个变量一样是使用 val 和 var 关键字。val 变量只有一个 getter ，var 变量既有 getter 也有 setter。
           //c.访问器的默认实现逻辑很简单：创建一个存储值的字段，以及返回属性值的 getter 和更新属性值的
             //setter。如果需要的话，也可以自定义访问器

    }

     fun main(args:ArrayList<String>){
         val result = StringBuilder()
         result.append("1")
         result.append("2")
         println(result.toString())
     }
     class Point(val x:Int,val y:Int){
         val isEquals:Boolean
           get() {return x == y}
         val isEquals2
                 get() = x == y

     }
}
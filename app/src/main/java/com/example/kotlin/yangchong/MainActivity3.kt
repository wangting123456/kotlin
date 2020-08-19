package com.example.kotlin.yangchong

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin.R
import kotlinx.android.synthetic.main.activity_rxjava1.*
import kotlinx.coroutines.*
import java.lang.StringBuilder

/**
 * Kotlin函数
 */
class MainActivity3:AppCompatActivity() {
    val TAG:String = "MainActivity3"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava1)


        //with函数:
          //a.一个对象，在方法中可以引用对象的公有属性和共有方法，
              //返回值是执行 Lambda 表达式的结果
          //b.with函数用于对同一个对象执行多次操作而不需要反复把对象的名称写出来
             //例如为了构建一个包含指定内容的字符串，需要先后如下调用
          //c.改为通过with函数来构建的话会简单很多
          //d.with 结构其实是一个接受两个参数的函数,在这个例子中就是一个 StringBuilder 和一个 Lambda
             //表达式，这里利用了把 Lambda 表达式放在括号外的约定
             //with 函数的返回值是执行 Lambda 表达式的结果，该结果就是 Lambda 中的最后一个表达式的返回值
            with(tv_init){
                text = "nihao"
            }

             val result = with(StringBuilder()){
                 append("1")
                 append("2")
                 toString()
             }
             println(result)
             Log.d(TAG,result);

             val result2 = with(StringBuilder()){
                 append("1")
                 append("2")
                 println()//println() 方法无返回值，所以打印出来的内容将是 kotlin.Unit
             }
        //2.apply函数说明
            // apply 函数作用 :
            //作用 : 扩展了调用 apply 函数的泛型类型 T 的对象的操作方法 , 等同于为泛型 T 对象定义了一个新的方法
            //特点 : 在 apply 后的闭包内 , 可以调用该 T 对象的所有成员方法 和 成员变量
            //返回值 : appay 方法返回泛型类型 T 对象本身 , 即在闭包内执行的针对 T 对象的内容是保存下来的
            //调用 T 对象本身 : 使用 this 可以访问该对象 , 不是 it
            //apply 接收一个函数类型参数 block: T.() -> Unit
            //函数类型变量名 : block 是函数类型实例名称
            //T.() -> Unit : 函数类型
            //T 表示 image 本身
            //T.() 表示 image 本身的成员方法 , 其返回值是 Unit 空类型
             val result3 = StringBuilder().apply {
                this.append("1")
                 append("2")
             }
             Log.d(TAG,result3.toString())
            //b.apply函数被声明为一个扩展函数，它的接收者变成了作为实参的 Lambda 的接受者
             val student = Student().apply {
                 name = "lisi"
             }
            Log.d(TAG,student.name)
         val res = StringBuilder().also {
            it.append("abc")
            it.append("defg")
        }
        Log.d(TAG,res.toString())
        //3.let
          //源码let函数的结构来看它是只有一个lambda函数块block作为参数的函数,调用T类型对象的let函数，
          //则该对象为函数的参数。在函数块内可以通过 it 指代该对象。返回值为函数块的最后一行或指定return表达式。
         tv_init?.let {
             it.text = "12"
         }
        //4.run
         //执行传入的函数式，并返回函数的执行结果。run的主要目的是强调需要执行的函数。

        //5.alse
         //let是以闭包的形式返回，返回函数体内最后一行的值，如果最后一行为空就返回一个Unit类型的默认值。
         //而also函数返回的则是传入对象的本身

        //协程
        Log.e(TAG, "主线程id：${mainLooper.thread.id}")
       /* test();
        Log.e(TAG, "结束")*/
        //unBlocking启动的协程任务会阻断当前线程，直到该协程执行结束。当协程执行结束之后，页面才会被显示出来。

        GlobalScope.launch {
            delay(3000)
            Log.e(TAG, "launch ")
        }
        Log.e(TAG, "主线程执行结束")
        //从执行结果看出，launch不会阻断主线程。

        GlobalScope.launch(Dispatchers.Default) {
             launch { delay(1000)
                 Log.e(TAG, "delay hello ")
             }
             Log.e(TAG, "hello ")
        }
       val job =  GlobalScope.launch {
            delay(1000)
           Log.e(TAG, "111world ")
        }
        job.cancel();
        Log.e(TAG, "111hello ")
        main()
    }
    //作用很像Thread.join()函数，join()后面的代码会等到协程结束再执行
    fun main() = runBlocking{
      val job = GlobalScope.launch {
           Log.e(TAG, "222hello ")
           delay(1000)
           Log.e(TAG, "222world ")
       }
        job.join()
        Log.e(TAG, "222println ")
    }
    private fun test() = runBlocking {
        repeat(8){
            Log.e(TAG, "协程执行$it 线程id：${Thread.currentThread().id}")
        }
        delay(1000)
    }
    class Student{
        var name:String = "";
        val age:Int = 34;
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

    //also和apply类似，唯一不一样的是:
    //apply是调用对象的扩展block()方法，因此在block里可以用this,而不能用it
    //而also是调用一个block(T t)方法，因此block里不能用this,而能用it
    //also


    //apply

    val res1 = StringBuilder().apply {
        this.append("1")
        append("2")
    }
    //b.apply函数被声明为一个扩展函数，它的接收者变成了作为实参的 Lambda 的接受者
    val student = Student().apply {
        this.name = "lisi"
    }
    val res2 = StringBuilder().also {
        it.append("111")
        it.append("222")
    }
}
package com.example.kotlin_start

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.lang.Exception

/**
 * 习惯用法
 */
class MainActivity2:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.创建 DTOs
        data class Consumer(val name:String, val age:Int){

         }
        //2.函数的默认参数
         fun test(a:Int = 0,b:String = " " ){

         }
        //3.过滤 list
          val list = listOf(1,-1,0,2,3)
          list
            .filter { it > 0 }
            .forEach { Log.d("过滤","it:"+it) }
        //4.检测元素是否存在于集合中
           if(1 in list){
               Log.d("过滤","in")
           }
        //5.字符串内插
          val a = 1
          Log.d("过滤","$a")
        //6.遍历 map/pair型list
          val maplist = mapOf("1" to 1,"2" to 2,"3" to 3)
          for((a,b) in maplist){
              Log.d("过滤","$a   $b")
          }
        //7.使用区间
          for (i in 1..100) {  }  // 闭区间：包含 100
          for (i in 1 until 100) {  } // 半开区间：不包含 100
          for (x in 2..10 step 2) { }
          for (x in 10 downTo 1) { }
          val x = 1
          if (x in 1..10) { }
        //8.只读 list
          val list2 = listOf("a", "b", "c")
        //9.只读map
          val map = mapOf("a" to 1, "b" to 2, "c" to 3)
        //10.访问map
          map["a"]
        //11.延迟属性:用于声明val，多用于单例模式。lateinit 用于声明变量
        val p: String by lazy {
            "hello"
        }
        Log.d("测试","lazyP:"+lazyP)
        Log.d("测试","lazyP:"+lazyP)
        //12.扩展函数：
          //通常在java中，我们是以各种XXXUtils的方式来对已经存在的类进行功能的扩展。但是有了扩展函数，
          //我们就能丢弃让人讨厌的XXXUtils方法工具类
          //a.假如我们需要为String类型添加一个返回这个字符串最后一个字符的方法
           test()
        //13.创建单例
           //object
        //14.If not null 缩写
          val files = File("11").listFiles()
          files ?.size
          //if(files != null){files.size}
        //15.If not null and else 缩写
          println(files ?.size ?:"empty")
        //16.if null 执行一个语句
          val emaile = "111"
          val main = emaile.firstOrNull() ?: "11"
        //17.返回 when 表达式
        //18.“try/catch”表达式
           test1()
        //19.单表达式函数
          fun theAnswer() = 42
          //等价于
          fun the():Int{
              return 41
          }

    }

    val lazyP:String by lazy {
        Log.d("测试","lazyP函数")
        "你好"
    }
    fun String.lastChar():Char = this.get(this.length - 1)

    fun test(){
        "qwqw".lastChar()
    }
    object Resource{

    }

    fun transaction(color:String):Int{
       return when(color){
             "red" -> 0
             "green" -> 1
             "blue" -> 2
            else -> throw IllegalArgumentException("Invalid color param value")
        }
    }

    fun test1(){
       val result = try {
            count()
        }catch (e:Exception){

        }
    }
    fun count(){

    }


}
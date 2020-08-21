package com.example.kotlin.kotlin_basic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * 静态与伴生对象companion
 */
class Companion:AppCompatActivity() {
    val TAG:String = "Companion"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //伴生对象:
           //舍弃static，是使用伴生对象实现，在class内部声明一个companion object代码块，其内部的成员变量和方法都将被编译成为静态的。
           //Factory是最终生成的静态内部类类名，通常来说这个名字可以省略，如果省略，类名为默认的Companion。
        //调用:
           //就跟调用一个静态方法一样类名+方法名。
          TestStatic.test()
          TestStatic.Company.work()
        //注意:
          //一个类中最多只能有一个companion object代码块。
          //伴生对象本质上就是一个静态内部类，所以它还能继承其他类
    }
    class TestStatic{
        //伴生对象
        companion object Factory{
            var str:String = ""
            fun test():TestStatic{
              Log.d("Companion","test")
                return TestStatic()
            }
        }

        //单例对象
        object Company{
            var name:String = ""
            fun work(){

            }
        }
    }
}
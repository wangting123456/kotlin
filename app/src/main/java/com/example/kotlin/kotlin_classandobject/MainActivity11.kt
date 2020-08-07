package com.example.kotlin.kotlin_classandobject

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

/**
 * 对象表达式和对象声明
 */
class MainActivity11:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    //1.对象表达式
    /**
     * 对象表达式
     */
    fun test(){
       /* window.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) { *//*……*//* }

            override fun mouseEntered(e: MouseEvent) { *//*……*//* }
        })*/
    }

    /**
     * 如果超类型有一个构造函数，则必须传递参数
     */
    open class A(i:Int){
        public open val y:Int = i
    }
    interface B{

    }
    val ab:A = object:A(1),B{
        override val y: Int = 15
    }

    /**
     * 如果我们只需要一个对象而已，并不需要特殊超类型，就可以以下这样写
     */
    fun foo(){
        val ab = object{
            var x:Int = 0
            var y:Int = 0
        }
        Log.d("foo","print:"+ab.x+ab.y)
    }

    class C{
        //私有函数，其返回值类型是匿名对象类型
        private fun foo() = object{
            var x :String = "1"
        }
        //公有函数，其返回值类型是Any
        fun foo2() = object {
            var x :String = "1"
        }
        fun bar(){
            foo().x
            //无法访问
            //foo2().x
        }
    }
    /**
     * 对象表达式的代码，可以访问其作用域的变量
     */
    fun click(window:Window){
        var clickCount = 0
        var enterCount = 0

      /*  window.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                clickCount++
            }

            override fun mouseEntered(e: MouseEvent) {
                enterCount++
            }
        })*/

        //2.对象声明
        /**
         * 对象声明
         * 单例模式在一些场景中使用，kotlin中的单例声明
         */
       /* object DataProviderManager{
            fun registerDataProvider(){

            }
        }*/
        //这称为对象声明，在object后跟一个名称，就像变量声明一样，对象声明不是一个表达式，不能用在赋值语句的右侧
    }
}
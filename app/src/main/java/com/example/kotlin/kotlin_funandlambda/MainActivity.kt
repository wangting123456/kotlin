package com.example.kotlin_funandlambda

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * 函数
 */
class MainActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //函数调用
        val double = Double(2)
        //具名函数调用
        reformat("111",word = '1')
        asList(1,2,3)
        Sample().foo()
    }
    //1.函数声明
    /**
     * 函数声明
     * 使用fun 声明函数
     */
    fun Double(x:Int):Int{
        return 2*x
    }

    //2.参数
    /**
     * 参数
     * name:type 参数用逗号分开，每个参数必须有显示类型
     */
    fun powerOff(number:Int,total:Int){

    }

    /**
     * 3.默认参数
     * 函数参数可以有默认值，当省略相应的参数时使用默认值。可以减少重载数量
     */
    fun read(b:Array<Byte>,offet:Int,len:Int = b.size){

    }

    /**
     * 4.覆盖带有默认参数的方法
     * 必须从签名中省略默认值
     */
     open class A{
        open fun foo(age:Int = 10){

        }
    }
    class B:A(){
        //不能有默认值
        override fun foo(age: Int) {
            super.foo(age)
        }
    }
    /**
     * 5.如果一个默认参数在一个无默认参数之前，那么该默认值只能通过具名参数调用该函数来使用
     */
    fun foo2(bar:Int = 0,baz:Int){

    }

    /**
     * 6.具名参数
     * 在调用函数时，使用具名的函数当参数
     */
    fun reformat(str:String,normal:Boolean = true,upper:Boolean = false,word:Char = ' '){
       Log.d("reformat","str:"+str+"word:"+word)
    }

    /**
     * 7.返回Unit
     * 函数无返回值
     * :Unit 可以省略
     */
    fun printName(name:String):Unit{
        Log.d("printName","name:"+name)
    }

    /**
     * 8.单表达式函数
     * 当函数返回单个表达式时，可以省略花括号，在等于号后加上表达体
     */
    fun double(x:Int):Int= x*2
    fun double2(x:Int) = x*2 //返回类型由编辑器推断出来，函数返回类型可以省略

    /**
     * 9.可变数量的参数Varargs
     */
    fun <T> asList(vararg t:T ):List<T>{
        val result = ArrayList<T>()
        for(ts in t){
            result.add(ts)
        }
        return result
    }

    /**
     * 10.成员函数
     */
    class Sample(){
      fun  foo(){
          Log.d("Sample","sample:foo()")
      }
    }


    /**
     * 泛型函数
     */
    fun <T> getData(item:T):List<T>{
        val list = arrayListOf<T>()
        list.add(item)
        return list
    }




}
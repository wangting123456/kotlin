package com.example.kotlin.kotlin_classandobject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatDrawableManager.get
import java.lang.reflect.Array.set

/**
 * 属性和字段
 */
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usePro(Address())
    }

    //1.声明属性
    /**
     * 声明属性
     * var 声明为可变的，val 声明为只读的
     */
    class Address {
        var name: String = "jiedao"
        var street: String = "sijie"
        var city: String = "china"

    }

    //属性的使用
    fun usePro(address: Address) {
        Log.d("usePro", address.city)
        Log.d("usePro", address.street)
        Log.d("usePro", address.name)
    }

    //2.Getters和Setters
    /**
     * 声明一个属性完整语法
     * var properName[:properType][= properInir][getter][setter]
     */
    fun test() {
        var init: Int = 1 //类型Int ,默认Getter
    }

    //只读属性val 不允许setter
    fun test2() {
        val simple: Int?//类型Int ,默认Getter,必须在构造函数中初始化
        val init = 1//类型Int ,默认Getter
    }

    //为属性提供自定义的访问器，每次访问该属性时，都会调用它
    class getter {
        val size:String = "1"
        val isEmpty: Boolean
        get() = this.size.length == 0
    }

    //自定义setter
    class setter{
        var name: String
        get() = this.toString()
        set(value){
           // setDataFromString(value)
        }
    }

    //如果你想改变访问器的可见性
    class test3 {
        var setterVisibility: String = "abc"
        private set // 此 setter 是私有的并且有默认实现
        var setterWithAnnotation: Any? = null
        // @Inject set // 用 Inject 注解此 setter
    }

    //3.幕后字段
    /**
     * 幕后字段
     * 在kotlin中不能直接声明字段，然而当一个属性需要一个幕后字段时，会自动提供fields
     */
    class testFields {
        var counter = 0
            //这个初始器直接为幕后字段赋值
            set(value) {
                if (value >= 0) field = value
            }

    }

    //4.延迟初始化属性和变量
    /**
     * 延迟初始化属性和变量
     * 一般情况下，属性声明为非空，必须在构造函数中初始化，然而，有些属性需要在依赖注入后初始化lateinit
     */
    fun lateinitTest() {
        lateinit var name: String
        fun stepUp() {
            name = "zhangsan"
        }
    }

    //检测一个lateinit var 是否被初始化
    class test4 {
        lateinit var name: String
        /* if(foo::bar.isInitialized) {
             println(foo.bar)
         }*/
    }

}
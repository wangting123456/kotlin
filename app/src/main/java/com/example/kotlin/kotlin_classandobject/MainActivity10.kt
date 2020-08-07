package com.example.kotlin.kotlin_classandobject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

/**
 * 枚举类
 */
class MainActivity10:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val v = Direction.values()
        GetColor.valueOf("0xFF0000")
    }
    //1.枚举类
    /**
     * 枚举类
     * 枚举类最基本的用法是实现类型安全的枚举
     * 每一个枚举常量都是一个对象，用逗号分开
     */
    enum class Direction{
        NORTH, SOUTH, WEST, EAST
    }

    //2.初始化
    /**
     * 初始化
     */
    enum class GetColor(val color:Int){
        GREEN(0xFF0000),
        RED(0xFF0000),
        WHITE(0xFF0000),
        BLACK(0xFF0000);
    }

    //3.匿名类
    /**
     * 匿名类
     * 枚举常量还可以声明其带有相应方法及覆盖了其基类方法的匿名类
     */
    enum class ProtocolState{
        WAITTING{},
        TALKING{};
    }

    //4.使用枚举常量
    /**
     * 使用枚举常量
     *见onCreate
     */
    //每个枚举常量都有在枚举声明中获得其名称和位置的属性
   /* val name: String
    val ordinal: Int*/
}
package com.example.kotlin.kotlin_basic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 取单个元素
 */
class MainActivity11:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.按位置取
           //a.elementAt()
             val list = listOf("one","two")
             list.elementAt(0)
           //b.first();last()
             list.first()
             list.last()
        //2.按条件取
            //a.
            list.first { it.length > 3 }
            list.last { it.startsWith("{") }
            //b.如果没有元素与谓词匹配，两个函数都会抛异常。 为了避免它们，请改用 firstOrNull() 和
               //lastOrNull()：如果找不到匹配的元素，它们将返回 null。
            list.firstOrNull { it.length > 6 }
            list.lastOrNull { it.length > 6  }
        //3.随机取元素
            val list2 = listOf(1,2,3,4)
            list2.random()
        //4.检测存在与否
            val numbers = listOf("one", "two", "three", "four", "five", "six")
            numbers.contains("four")
            numbers.isEmpty()
            numbers.isNotEmpty()
    }
}
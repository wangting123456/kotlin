package com.example.kotlin.kotlin_basic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 *集合操作概述
 */
class MainActivity6:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.集合操作概述
          //Kotlin 标准库提供了用于对集合执行操作的多种函数,这包括简单的操作，例如获取或添加元素，以
          //及更复杂的操作，包括搜索、排序、过滤、转换等。
        //2.扩展与成员函数
          //a.集合操作在标准库中以两种方式声明：集合接口的成员函数和扩展函数
        //3.公共操作
          //a.公共操作可用于只读集合与可变集合
          //b.这些页面中描述的操作将返回其结果，而不会影响原始集合。例如，一个过滤操作产生一个_新集合_，
            //其中包含与过滤谓词匹配的所有元素。此类操作的结果应存储在变量中，或以其他方式使用，例如，传到其他函数中
            val listNumbers = listOf("one","two","three","four")
            listNumbers.filter { it.length > 3 }
            Log.d("TAG","$listNumbers")//`listNumbers` 没有任何改变，结果丢失
            val filterNumbers = listNumbers.filter { it.length > 3 }
            Log.d("TAG","$filterNumbers")//包含两个操作的结果
           //c.为了方便起见，这些函数将目标集合返回了，因此您可以在函数调用的相应参数中直接创建它
            val result = listNumbers.mapTo(HashSet()){it.length}
            Log.d("TAG","$result")
        //4.写操作
           //对于可变集合，还存在可更改集合状态的 写操作 。这些操作包括添加、删除和更新元素。写操作
           //在集合写操作以及 List 写操作与 Map 写操作的相应部分中列出。
           val numbers = mutableListOf("one", "two", "three", "four")
           val sortedNumbers = numbers.sorted()
           println(numbers == sortedNumbers)  // false
           numbers.sort()
           println(numbers == sortedNumbers)  // true
    }
}
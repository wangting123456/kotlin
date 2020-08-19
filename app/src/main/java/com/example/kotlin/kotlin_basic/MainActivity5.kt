package com.example.kotlin.kotlin_basic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 序列
 */
class MainActivity5:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.序列
           //a.除了集合之外，Kotlin 标准库还包含另一种容器类型——序列（Sequence<T>）
        //2.构造
          //a.由元素：要创建一个序列，请调用 sequenceOf() 函数，列出元素作为其参数。
           val numbersSequence = sequenceOf("one","two","three")
          //b.由 Iterable:则可以通过调用 asSequence() 从而创建一个序列。
           val numbers = listOf("one", "two", "three", "four")
           val seq2 = numbers.asSequence()



    }

}
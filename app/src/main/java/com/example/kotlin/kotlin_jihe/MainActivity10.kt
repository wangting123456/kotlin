package com.example.kotlin_jihe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 取集合的一部分
 */
class MainActivity10:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.Slice 切 部分：slice() 返回具有给定索引的集合元素列表
          val numbers = listOf("one","two","three","four","five","six")
          numbers.slice(1..3)
          numbers.slice(0..4 step 2)
          numbers.slice(setOf(1,2,4))

        //2.Take 与 drop
          //a.take():要从头开始获取指定数量的元素。takeLast() 要从尾开始获取指定数量的元素
          numbers.take(3)
          numbers.takeLast(3)
          //b.drop() 或 dropLast()要从头或从尾去除给定数量的元素
           numbers.drop(2)
           numbers.dropLast(2)
        //3.Chunked
           //要将集合分解为给定大小的“块”，请使用 chunked() 函数
           //并返回一个 List 其中包含给定大小的 List。 第一个块从第一个元素开始并包含 size 元素，第二个块包含下一个 size 元素，
           val list2 = (0..13).toList()
           list2.chunked(3)
        //4.Windowed
           //可以检索给定大小的集合元素中所有可能区间。 获取它们的函数称为 windowed()
           val list3 = listOf("one","two","three","four","five","six")
           list3.windowed(3)
    }
}
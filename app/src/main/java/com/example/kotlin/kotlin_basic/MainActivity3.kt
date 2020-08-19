package com.example.kotlin.kotlin_basic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * 迭代器
 */
class MainActivity3:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.迭代器
           //a.可以通过调用 iterator() 函数获得迭代器
            val numbers = listOf("one","two","three")
            val numbersIterator = numbers.iterator()
             while (numbersIterator.hasNext()){
                 Log.d("TAG","numbersIterator.next:"+numbersIterator.next())
             }
           //2.遍历集合的另外一种方法for循环
            val numbers2 = listOf("one","two","three")
            for(i in numbers2){
                Log.d("TAG","item:"+i)
            }
           //3.有一个好用的 forEach() 函数
            val numbers3 = listOf("one","two","three","four")
           numbers3.forEach {    Log.d("TAG","forEach it:"+it) }

        //2.List 迭代器
          //a.对于列表，有一个特殊的迭代器实现： ListIterator 它支持列表双向迭代：正向与反向
            //反向迭代由 hasPrevious() 和 previous() 函数实现。
        val numbers4 = listOf("one", "two", "three", "four")
        val listIt = numbers4.listIterator()
        while (listIt.hasNext()){
            //listIt.next()
            Log.d("TAG"," listIt.next():"+ listIt.next())
            Log.d("TAG"," listIt.nextIndex():"+ listIt.nextIndex())
        }
        while (listIt.hasPrevious()){
            //listIt.previous()
            Log.d("TAG"," listIt.previous():"+ listIt.previous())
            Log.d("TAG"," listIt.previousIndex():"+ listIt.previousIndex())
        }

        //3.可变迭代器
          //a.为了迭代可变集合，于是有了 MutableIterator 来扩展 Iterator 使其具有元素删除函数 remove() 。
            //因此，可以在迭代时从集合中删除元素。
          val number5 = mutableListOf("one", "two", "three", "four")
          val mutableListIt = number5.listIterator()
          Log.d("TAG","  mutableListIt.next():"+  mutableListIt.next())
          //mutableListIt.next()
          mutableListIt.remove()
          Log.d("TAG","number5 数组 is $number5")

         //b.除了删除元素， MutableListIterator 还可以在迭代列表时插入和替换元素。
          val number6 = mutableListOf("one",  "four","four")
          val mutableListNum = number6.listIterator()
          mutableListNum.next()
          Log.d("TAG","number6 数组111 is $number6")
          mutableListNum.add("two")
          Log.d("TAG","number6 数组222 is $number6")
          mutableListNum.next()
          Log.d("TAG","number6 数组333 is $number6")
          mutableListNum.set("three")
          Log.d("TAG","number6 数组444 is $number6")
    }
}
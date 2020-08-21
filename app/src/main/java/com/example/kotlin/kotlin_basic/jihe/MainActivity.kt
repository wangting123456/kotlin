package com.example.kotlin.kotlin_basic.jihe

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * 集合概述
 */
class MainActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //一.Kotlin 标准库提供了基本集合类型的实现： set、list 以及 map
          //1.List
              //a.List<T>以指定的顺序存储，并提供使用索引来访问元素的方法。索引从 0 开始 – 第一个元素的索引
                 //– 直到 最后一个元素的索引 即 (list.size - 1)
                 list()
              //b.List 元素（包括空值）可以重复。List 可以包含任意数量的相同对象或单个对象的出现。
                 //如果两个 List 在相同的位置具有相同大小和相同结构的元素，则认为它们是相等的。
                 list2()
              //c.MutableList 是可以进行写操纵的List,例如用于在特定位置添加或删除元素。
                 list3()

          //2.set
              //a.存储唯一的元素；它们的顺序通常是未定义的。null 元素也是唯一的：一个 Set 只能包含一个 null
                //当两个 set 具有相同的大小并且对于一个 set 中的每个元素都能在另一个 set 中存在相同元素，
                //则两个 set 相等
              set()

          //3.map
              //a.Map 存储 键-值 对（或 条目）；键是唯一的，但是不同的键可以与相同的值配对
                map()
              //b.无论键值对的顺序如何，包含相同键值对的两个 Map 是相等的。
               map2()
              //c.MutableMap 是一个具有写操作的 Map 接口，可以使用该接口添加一个新的键值对或更新给定键的值
               map3()
    }
    fun coll(){
        val strlist = listOf("one","two","three")
        printAll(strlist)
    }
    fun printAll(strings:Collection<String>){
        for (s in strings){
            print("$s")
        }
    }

    /**
     * list使用
     */
    fun list(){
       val numbers = listOf("one","two","three","four")
        Log.d("list","Number of elements: ${numbers.size}")
        Log.d("list","Third elements: ${numbers.get(2)}")
        Log.d("list","Fourth elements: ${numbers[3]}")
    }

    fun list2(){
        val people = People("zhangsan",21)
        val peolist = listOf<People>(people,people,people)
        val peolist2 = listOf<People>(People("lisi",21),people,people)
        println(peolist == peolist2)
    }
    fun list3(){
        val list = mutableListOf("1","2","3","4")
        list.add("5")
        list.removeAt(0)
        list.shuffle()
        println(list)
    }

    fun set(){
        val set = setOf(1,2,3,4)
        val set2 = setOf(4,3,2,1)
        println("The sets are equal: ${set == set2}")
    }
    fun map(){
       val map = mapOf("key1" to 1,"key2" to 2,"key3" to 3)
        println("All keys: ${map.keys}")
        println("All values: ${map.values}")
    }
    fun map2(){
        val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)
        val anotherMap = mapOf("key2" to 2, "key1" to 1, "key4" to 1, "key3" to 3)

        println("The maps are equal: ${numbersMap == anotherMap}")
    }

    fun map3(){
       val numbersMap = mutableMapOf("key1" to 1,"key2" to 2)
        numbersMap.put("key3",3)
        numbersMap["key1"] = 11
        println(numbersMap)
    }


}
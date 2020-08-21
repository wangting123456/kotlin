package com.example.kotlin.kotlin_basic.jihe

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 *集合转换
 */
class MainActivity7:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.映射
         //a.映射 map(): 转换从另一个集合的元素上的函数结果创建一个集合。 基本的映射函数是 map(),它将给定的
           //lambda 函数应用于每个后续元素，并返回 lambda 结果列表
            val numbers = setOf(1,2,3)
            Log.d("TAG,","map:"+numbers.map { it * 2 })
         //b.mapIndexed(): 结果的顺序与元素的原始顺序相同。 如需应用还要用到元素索引作为参数的转换，请使用 mapIndexed()。
            numbers.mapIndexed { index, value -> value * index }
            Log.d("TAG,","numbers.map { it * 2 }:"+numbers.mapIndexed { index, value -> value * index })
         //c.mapNotNull():如果转换在某些元素上产生 null 值，则可以通过调用 mapNotNull() 函数取代 map()
            numbers.mapNotNull { if(it == 2) null else it * 2}
         //d.mapIndexedNotNull() 取代 mapIndexed() 来从结果集中过滤掉 null 值
            numbers.mapIndexedNotNull { index, value -> if(index == 0) null else index*value }
         //e.mapKeys()，mapValues() 映射转换时，有两个选择：转换键，使值保持不变，反之亦然。 要将指定转换应用于键，
            val numbersMap = mapOf("key1" to 1 ,"key2" to 2,"key3" to 3)
            numbersMap.mapKeys { it.key.toUpperCase() }
            numbersMap.mapValues { it.value + it.key.length }
            Log.d("TAG","mapKeys:"+ numbersMap.mapKeys { it.key.toUpperCase() })
            Log.d("TAG","mapValues:"+numbersMap.mapValues { it.value + it.key.length })

        //2.双路合并
          //a.zip() 也可以中缀形式调用 a zip b 。
             val colors = listOf("red", "brown", "grey")
             val animals = listOf("fox", "bear", "wolf")
             Log.d("TAG","colors zip animals:"+(colors zip animals))
             val animals2 = listOf("fox", "bear")
             Log.d("TAG","colors zip animals:"+(colors zip (animals2)))
          //b.要分割键值对列表，请调用 unzip()。
             val numberPairs = listOf("one" to 1,"two" to 2,"three" to 3)
             Log.d("TAG","unzip:"+ numberPairs.unzip())
        //3.关联
            //a.associateWith:关联 转换允许从集合元素和与其关联的某些值构建 Map
            val numbers2 = listOf("one", "two", "three", "four")
            Log.d("TAG","associateWith:"+ numbers2.associateWith { it.length })
            //b. associateBy():为了使用集合元素作为值来构建 Map，有一个函数 associateBy()
            numbers2.associateWith { it.first().toUpperCase() }
            numbers2.associateBy(keySelector = { it.first().toUpperCase() }, valueTransform = { it.length })
            //c.associate():ap 的方法是使用函数 associate()，其中 Map 键和值都是通过集合元素生成的。
              //它需要一个 lambda 函数，该函数返回 Pair
            val names = listOf("Alice Adams", "Brian Brown", "Clara Campbell")
            //println(names.associate { name -> parseFullName(name).let { it.lastName to it.firstName } })
        //4.打平
           //a.flatten()
            val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
            println(numberSets.flatten())
          //b.flatMap
          /* val containers = listOf(
              StringContainer(listOf("one", "two", "three")),
              StringContainer(listOf("four", "five", "six")),
              StringContainer(listOf("seven", "eight"))
            )
        println(containers.flatMap { it.values })*/

        //5.字符串表示
          //a.joinToString() 与 joinTo()
        val numbers3 = listOf("one", "two", "three", "four")

        println(numbers3)
        println(numbers3.joinToString())

        val listString = StringBuffer("The list of numbers: ")
        numbers3.joinTo(listString)
        println(listString)
    }
}
package com.example.kotlin_jihe

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.HashSet

/**
 * 构造集合
 */
class MainActivity2:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.由元素构造
          //a.创建集合的最常用方法是使用标准库函数 listOf<T>()、setOf<T>()、mutableListOf<T>()、mutableSetOf<T>()。
           val numbers = setOf("one","two","three")
          //b.如果以逗号分隔的集合元素列表作为参数，编译器会自动检测元素类型。创建空集合时，须明确指定类型。
           val emptyNums = mutableSetOf<String>()
          //c.Map 也有这样的函数 mapOf() 与 mutableMapOf()。
           val mapNumbers = mapOf("key1" to 1,"key2" to 2)
            //to 符号创建了一个短时存活的 Pair 对象,因此建议仅在性能不重要时才使用它。为避免过多的内存使用，请使用其他方法
          //d.可以创建可写 Map 并使用写入操作填充它。 apply() 函数可以帮助保持初始化流畅
           val numbersMap = mutableMapOf<String,String>().apply { this["one"] = "1";this["two"] = "2" }

        //2.空集合
          //a.还有用于创建没有任何元素的集合的函数，emptyList()、emptySet() 与 emptyMap()。创建空集合时，应指定集合将包含的元素类型
           val emptyList = emptyList<String>()
        //3.list 的初始化函数
          //a.对于 List，有一个接受 List 的大小与初始化函数的构造函数,该初始化函数根据索引定义元素的值
          val double = List(3,{it * 2})
        //4.具体类型构造函数
          //a.要创建具体类型的集合，例如 ArrayList 或 LinkedList，可以使用这些类型的构造函数。Set 与 Map中也有提供
           val linkList = LinkedList<String>(listOf("one","two","three"))
           val set = HashSet<Int>(32)
        //5.复制
          //a.要创建与现有集合具有相同元素的集合，可以使用复制操作。
          //b.在特定时刻通过集合复制函数，例如toList()、toMutableList()、toSet() 等等。创建了集合的快照。
            //结果是创建了一个具有相同元素的新集合 如果在源集合中添加或删除元素，则不会影响副本。
            val sourceList = mutableListOf(1,2,3)
            val copyList = sourceList.toMutableList()
            sourceList.add(4)
          //c.这些函数还可用于将集合转换为其他类型，例如根据 List 构建 Set，反之亦然。
             val source = mutableListOf(1,2,3)
             val copyset =  source.toMutableSet()
             copyset.add(3)
             copyset.add(4)
             println(copyset)
          //d.可以创建对同一集合实例的新引用。使用现有集合初始化集合变量时，将创建新引用。 因此，
            //当通过引用更改集合实例时，更改将反映在其所有引用中。
            val slist = mutableListOf(1,2,3)
            val referenceList = slist
            referenceList.add(4)
          //e.集合的初始化可用于限制其可变性。例如，如果构建了一个 MutableList 的 List 引用，当你试图
            //通过此引用修改集合的时候，编译器会抛出错误。
             val mulist = mutableListOf(1,2,3)
             val refer:List<Int> = mulist
              //refer.add(4)
              mulist.add(4)
          //6.调用其他集合的函数
            //a.可以通过其他集合各种操作的结果来创建集合。例如，过滤列表会创建与过滤器匹配的新元素列表：
             val list = listOf("one","two","three","four")
             val longthan3 = list.filter { it.length > 3 }
             println(longthan3)
            //b.映射生成转换结果列表
             val numberSet = setOf(1, 2, 3)
             println(numberSet.map { it * 3 })
             Log.d("wangting","mapIndexed11"+numberSet.map { it * 3 })
             println(numberSet.mapIndexed { idx, value -> value * idx })
             Log.d("wangting","mapIndexed"+numberSet.mapIndexed { idx, value -> value * idx })
            //c.关联生成map
            val numbers_list = listOf("one", "two", "three", "four")
            println(numbers_list.associateWith { it.length })
            Log.d("wangting","associateWith"+numbers_list.associateWith { it.length })

    }
}
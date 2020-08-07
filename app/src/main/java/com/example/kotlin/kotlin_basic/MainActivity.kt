package com.example.kotlin.kotlin_basic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * 基本类型
 */
class MainActivity :AppCompatActivity() {
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shuzi()
        zifu('1')
        decimalDigitValue('2')
        zifuchuanmoban()
        shuzu()
    }

    /**
     * 数字Byte Short Int Long
     */
    fun shuzi(){
        val one = 1
        val billan = 30000000
        val longname = 1L
        val oneByte:Byte = 1
        val pi = 3.14
        val pai = 3.14
        val efloat = 3.1223f

    }
    /**
     * 字面常量 十进制，二进制，十六进制，不支持八进制
     */
    fun zimianchangliang(){
        //使用下划线使数字更容易阅读
        val carNum = 1234_5678_1290_1234

    }

    /**
     * 字符串模板
     */
    fun zifuchuanmoban(){
        val i = 10
        println("i == $i")
        Log.d(TAG,"i == $i")

        val s = "abc"
        Log.d(TAG,"$s length is ${s.length}")

    }
    /**
     * 字符
     */
    fun zifu(c:Char){
      if(c == '1'){
          Log.d(TAG,"c == 1")
      }
    }

    /**
     * 显示把字符转化为Int数字
     */
    fun decimalDigitValue(c:Char):Int{
        if(c !in '0'..'9'){
                //throw IllegalArgumentException("Out of range")
            }
        return c.toInt() - '0'.toInt()
    }

    /**
     * 数组
     */
    fun shuzu(){
        //1.使用arrayof(1,2,3)并传递值
        val arr = arrayOf(1,2,3)
        arr.forEach {   Log.d(TAG,""+it) }
        //打印 123

        //2.使用Array(5)构造函数
        val arr1 =Array(5) { i -> (i * i).toString() }
        arr1.forEach {   Log.d(TAG,""+it) }
        //打印 0 1 4 9 16

        //3.原生类型数组 ByteArray、 ShortArray、IntArray
        val arr2 = intArrayOf(1,2,3)
        var a = arr2[0]+arr2[1]
        Log.d(TAG,"intArrayOf:"+a)
    }


}
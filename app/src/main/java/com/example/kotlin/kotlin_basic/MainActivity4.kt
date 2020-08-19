package com.example.kotlin.kotlin_basic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * 区间与数列
 */
class MainActivity4:AppCompatActivity() {
    val TAG = "区间MainActivity4"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.区间与数列
          //a.Kotlin 可通过调用 kotlin.ranges 包中的 rangeTo() 函数及其操作符形式的 .. 轻松地创建两个
            //值的区间。 通常，rangeTo() 会辅以 in 或 !in 函数
           /* if(i in 1..4){//等同于 1 <= i && i <= 4
                Log.d(TAG,"i:"+i)
             }*/
          //b. 这些区间也是相应整数类型的等差数列。 这种区间通常用于 for 循环中的迭代
             for(i in 1..4)
             {
                 Log.d(TAG,"222i:"+i)
             }
            /*
             java 写法 1-4的循环
             for (int j=1;j<=4;j++){
                 Log.d(TAG,"222i:"+i)
                }*/

            //c.要反向迭代数字，请使用 downTo 函数而不是
             for (i in 4 downTo 1){
                 Log.d(TAG,"333i:"+i)
             }
          //d.也可以通过任意步长（不一定为 1 ）迭代数字。 这是通过 step 函数完成的。
             for (i in 1..8 step 2){
                 Log.d(TAG,"444i:"+i)
             }
             for (i in 8 downTo 1 step 2){
                 Log.d(TAG,"555i:"+i)
              }
           //e.要迭代不包含其结束元素的数字区间，请使用 until 函数：
             for (i in 1 until 10){//i in [1, 10), 10被排除
                 Log.d(TAG,"666i:"+i)
             }
        //2.区间
           //区间从数学意义上定义了一个封闭的间隔：它由两个端点值定义，这两个端点值都包含在该区间内。
           //区间是为可比较类型定义的：具有顺序，可以定义任意实例是否在两个给定实例之间的区间内。
           //区间的主要操作是 contains，通常以 in 与 !in 操作符的形式使用。

        //3.数列

    }

}
package com.example.kotlin.kotlin_classandobject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 密封类
 */
class MainActivity7 :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    //1.密封类
    /**
     * 一个值是有限的数据类型，而不能有任何其他的数据类型时，在某种意义上，他们是枚举类的扩展
     * 每个枚举常量只存在一个实例，而密封类的一个子类，可以有很多的实例
     * Expre 表达式
     */
    sealed class Expr
    /*data class Const(val number: Double) : Expr()
    data class Sum(val e1:Expr,val e2:Expr):Expr()
    object NotNumber:Expr()*/
}
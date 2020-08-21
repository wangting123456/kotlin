package com.example.kotlin.kotlin_basic

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_rxjava1.*
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.*

/**
 * 数据类data与单例类object
 */
class Data :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava1)
         //data class
         val x = User("212",12)
         Log.d("Data","${x.age}  ${x.name}")
         //object class
         TestSingle.test()
         //object还可用于创建匿名内部类，又叫对象表达式。
         tv_init.setOnClickListener { object: View.OnClickListener{
            override fun onClick(v: View?) {
                Log.d("Data","tv_init click")
            }
        } }
        //或者lambda表达式
        tv_init.setOnClickListener { v ->
            Log.d("Data","tv_init click")
        }

        //但是当匿名类有多个方法需要复写或实现是只能使用object
        Observable.just(1).subscribe { t -> Log.d("Data","accept $t") }

        //但是当匿名类有多个方法需要复写或实现是只能使用object
        Observable.just(1,2,3).subscribe(object:Observer<Int>{
            override fun onError(e: Throwable) {
                Log.d("Data","just accept onError")
            }
            override fun onSubscribe(d: Disposable) {
                Log.d("Data","just accept onSubscribe")
            }
            override fun onNext(t: Int) {
                Log.d("Data","just accept onNext: $t")
            }
            override fun onComplete() {
                Log.d("Data","just accept onComplete")
            }
        })


    }

    object TestSingle {
        fun test(){
            Log.d("Data","TestSingle test")
        }
    }
    class User(val name:String = "",val age:Int = 0)
}
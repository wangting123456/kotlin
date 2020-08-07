package com.example.kotlin.yangchong

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Kotlin类的扩展
 */
class MainActivity2:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //1.使用companion object
           //a.当你的类包含太多东西，你想把他们隔离到另外一个类，又不想使用类引用的方式，你就可以使用companion object
              //外部类可以直接访问对象，不需要通过对象指针
                MainActivity2.lunch(this,true,false,11)
           //b.静态属性、常量或者函数
              //我们需要一个类里面有一些静态的属性、常量或者函数，我们可以使用伴随对象
               AndroidUtils.getVersionCode(this)
        //2.object对象声明
          //a.在Java中，单例的声明可能具有多种方式：如懒汉式、饿汉式、静态内部类、枚举等；
          //b.在Kotlin中，单例模式的实现只需要一个 object 关键字即可
            SingleObject.test()
    }
    companion object{
        const val INTENT_TAG_HOME_DATA = "homeData"
        const val INTENT_TAG_BOOLEAN_IS_COLLECT = "isCollect"
        const val INTENT_TAG_INT_ARTICLE_ID = "articleId"
        fun lunch(context: Activity?, homeData: Boolean, isCollect: Boolean, articleId: Int) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(INTENT_TAG_HOME_DATA, homeData)
            intent.putExtra(INTENT_TAG_BOOLEAN_IS_COLLECT, isCollect)
            intent.putExtra(INTENT_TAG_INT_ARTICLE_ID, articleId)
            context?.startActivity(intent)
        }
    }
    class AndroidUtils{
        companion object {
            const val name = "yc"
            fun create(): AndroidUtils = AndroidUtils()
            fun getVersionCode(mContext: Context): Int {
                var versionCode = 0
                try {
                    //获取软件版本号，对应AndroidManifest.xml下android:versionCode
                    versionCode = mContext.packageManager.getPackageInfo(mContext.packageName, 0).versionCode
                } catch (e: PackageManager.NameNotFoundException) {
                    e.printStackTrace()
                }
                return versionCode
            }
        }
    }

    object SingleObject{
        fun test(){

        }
    }
}
package com.example.ms4;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.类的加载过程
        //2.hashmap
        //3.锁的区别底层实现
        //4.okhttp 比原来的优势，拦截器，为什么要有拦截器
        //5.glide的优点
    }
    //类的加载过程
    private void question1(){
        //a.在加载class文件的时候，JVM会先加载类中的所有静态成员( 方法，变量，静态代码块 )都加载到方法区class文件的所处静态区中
        //b.当把所有的静态成员加载完成之后，开始给类中的所有静态成员变量进行默认初始化
        //c.当类中的所有静态成员变量默认初始化之后，接着开始给所有静态成员变量显示赋值。
        //d.当类中所有的静态成员变量显示赋值结束之后，静态代码块才会运行。
        //e.当静态代码块执行结束之后，才表示class文件加载完成
    }
    //hashmap
    private void question2(){
        //数组：存储区间连续，占用内存严重，寻址容易，插入删除困难；
        //链表：存储区间离散，占用内存比较宽松，寻址困难，插入删除容易；
        //Hashmap综合应用了这两种数据结构，实现了寻址容易，插入删除也容易。
        //HashMap 内部结构：可以看作是数组和链表结合组成的复合结构，数组被分为一个个桶（bucket），每个桶存储有
          //一个或多个Entry对象，每个Entry对象包含三部分key（键）、value（值），next(指向下一个Entry），通
          //过哈希值决定了Entry对象在这个数组的寻址
    }
}

package com.example.ms3;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1.Java的垃圾回收机制
        //2.锁有几种，区别
        //3.FragmentManager中add和replace的区别
        //4.自定义view，onMeasure
        //5.四种引用方式
        //6.google的推送
        //7.设计模式mvvm
        //8.kotlin的学习
    }
    //Java的垃圾回收机制
    private void question1(){
        //垃圾收集GC（Garbage Collection）在Java中，程序员不需要去关心内存动态分配和垃圾回收的问题，这一切都
            //交给了JVM来处理。GC中判定为垃圾的标准，标记垃圾的算法以及回收垃圾的算法。
        //什么样的对象才是垃圾？对于Java对象来讲，如果说这个对象没有被其他对象所引用该对象就是无用的，此对象
            //就被称为垃圾，其占用的内存也就要被销毁。
    }
    //锁有几种，区别
    private void questio2(){
        //最多只有一个线程能够获得该锁，当线程A尝试去获得线程B持有的内置锁时，线程A必须等待或者阻塞，知道线程
           //B释放这个锁，如果B线程不释放这个锁，那么A线程将永远等待下去。
        //a.synchronized会自动释放锁(a 线程执行完同步代码会释放锁 ；b 线程执行过程中发生异常会释放锁)，
           // Lock需在finally中手工释放锁（unlock()方法释放锁），否则容易造成线程死锁；
        //b.用synchronized关键字的两个线程1和线程2，如果当前线程1获得锁，线程2线程等待。如果线程1阻塞,
           //线程2则会一直等待下去，而Lock锁就不一定会等待下去，如果尝试获取不到锁，线程可以不用一直等待就结束了；
        //d.Lock锁适合大量同步的代码的同步问题，synchronized锁适合代码少量的同步问题。
    }
    //FragmentManager中add和replace的区别
    private void question3(){
        //1.add不会重新初始化fragment，replace每次都会。所以如果在fragment生命周期内获取获取数据,使用
           //replace会重复获取。
        //2.添加相同的fragment时，replace不会有任何变化，add会报IllegalStateException异常。
        //3.replace会先清空父布局容器，再显示当前fragment，而add是覆盖前一个fragment。所以如果使用add一般会
           //伴随hide()和show()，避免布局重叠。
        //4.使用add，如果应用放在后台，或以其他方式被系统销毁，再打开时，hide()中引用的fragment会销毁，所以
           //依然会出现布局重叠bug，可以使用replace或使用add时，添加一个tag参数。
    }
    //自定义view，onMeasure
    private void question4(){
        //onMeasure()
        //MeasureSpec由两部分组成，一部分是测量模式，另一部分是测量的尺寸大小。
        //其中，Mode模式共分为三类
        //UNSPECIFIED ：不对View进行任何限制，要多大给多大，一般用于系统内部
        //EXACTLY：对应LayoutParams中的match_parent和具体数值这两种模式。检测到View所需要的精确大小，这时候View的最终大小就是SpecSize所指定的值
        //AT_MOST ：对应LayoutParams中的wrap_content。View的大小不能大于父容器的大小。

        //onDraw()
        //save()：用来保存Canvas的状态,save()方法之后的代码，可以调用Canvas的平移、放缩、旋转、裁剪等操作！
        //restore()：用来恢复Canvas之前保存的状态,防止save()方法代码之后对Canvas执行的操作，继续对后续的绘
           // 制会产生影响，通过该方法可以避免连带的影响！
    }
    //四种引用方式
    private void question5(){
        //强引用->强引用是使用最普遍的引用。如果一个对象具有强引用，那垃圾回收器绝不会回收它。当内存空间不足，
             //Java虚拟机宁愿抛出OutOfMemoryError错误
        //软引用->如果一个对象只具有软引用，那么如果内存空间足够，垃圾回收器就不会回收它；如果内存空间不足了，
           //就会回收这些对象的内存。
        //弱引用–>随时可能会被垃圾回收器回收，不一定要等到虚拟机内存不足时才强制回收。要获取对象时，同样可以
           //调用get方法
        //虚引用->是所有引用类型中最弱的一个。一个持有虚引用的对象，和没有引用几乎是一样的，随时都可能被垃圾
           //回收器回收
    }
    //推送
    private void question6(){
        //FCM(FireBaseCloud Messaging)是谷歌在2016年Google在I/O大会推出的最新的Android系统级别的消息推送服务
        //-服务器端主动性: 客户端与服务器交互都是客户端主动的, 服务器一般不能主动与客户端进行数据交互, 因为服务
             //器端无法得知客户端的 IP 地址 及 状态;
        //--数据实时性: 如果服务器端有紧急数据要传递给客户端, 就必须主动向客户端发送数据;
        //--基本原理: 使客户端实时获取服务器端消息, Pull 方式, 小周期轮询, 费电费流量; 另一个就是 Push 方式,
            //服务器端向客户端主动推送数据, 可以省电省流量;
    }
    //inline为什么引入
    private void question7(){
        //调用method方法就要将参数lock和lambda表达式{"我是body的方法体"}进行传递，就要将method方法进行压栈出栈处理，
        //这个过程就会耗费资源。如果是很多地方调用,就会执行很多次,这样就非常消耗资源了.
        //于是乎就引进了inline
        //被inline标记的函数就是内联函数,其原理就是:在编译时期,把调用这个函数的地方用这个函数的方法体进行替换
        //也就是说inline关键字实际上增加了代码量，但是提升了性能，而且增加的代码量是在编译期执行的，对程序可读
          //性不会造成影响,可以说是非常的nice.

        //虽然内联非常好用,但是会出现这么一个问题,就是内联函数的参数(ps:参数是函数,比如上面的body函数)如果在内
            // 联函数的方法体内被其他非内联函数调用,就会报错.
        //原因:因为method是内联函数,所以它的形参也是inline的,所以body就是inline的,但是在编译时期,body已经不是
            // 一个函数对象,而是一个具体的值,然而otherMehtod却要接收一个body的函数对象,所以就编译不通过了
        //解决方法:当然就是加noinline了,它的作用就已经非常明显了.就是让内联函数的形参函数不是内联的,保留原有的
            // 函数特征.
    }
    //启动模式
    private void question8(){
        //"后进先出"模式
        //Standard 标准模式
        /**
         * 每启动一次Activity，就会创建一个新的Activity实例，并置于栈顶，谁启动了Activity,那么这个Activity
         * 就运行在启动它的那个Activity所在的栈中。
         */
        //SingleTop 栈顶复用
        /**
         * 如果需要新建的Activity位于任务栈栈顶，那么此Activity的实例就不会重建，而是重用栈顶的实例。并回调
         * 如下方法onNewIntent
         */
        //SingleTask栈内复用
        /**
         * 该模式是一种单例模式，即一个栈内只有一个该Activity实例。
         * 该模式，可以通过在AndroidManifest文件的Activity中指定该Activity需要加载到那个栈中，即singleTask
         * 的Activity可以指定想要加载的目标栈。
         * singleTask和taskAffinity配合使用，指定开启的Activity加入到哪个栈中。
         */
        //SingleInstance 单例模式
        /**
         * 作为栈内复用模式（singleTask）的加强版,打开该Activity时，直接创建一个新的任务栈，并创建该Activity
         * 实例放入新栈中。一旦该模式的Activity实例已经存在于某个栈中，任何应用再激活该Activity时都会重用该栈中的实例。
         */
    }
    //协程
    private void question9(){
        //协程通过将复杂性放入库来简化异步编程。程序的逻辑可以在协程中顺序地表达。
        //协程就像非常轻量级的线程。线程是由系统调度的，线程切换或线程阻塞的开销都比较大。而协程依赖于线程，
          //但是协程挂起时不需要阻塞线程，几乎是无代价的，协程是由开发者控制的
    }
}

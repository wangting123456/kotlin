package com.example.ms.ms2;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
        getLifecycle().addObserver(new MyLifeCycleObserver());
        //中软没给offer
        //1.了解线程吗？线程：状态，挂起的方式和区别，异步方式同步锁，线程池的使用的API
        //2.databinding的双向绑定机制 edittext 就是双向绑定机制
        //3.lifecycle的实现原理 A startactivity  B
        //4.手机屏幕横竖屏切换的原理 数据保存 对象数据保存
        //5.rxjava的swapmap和flatmap的区别
        //6.JAVA内存GC
        //7.leak内存检测工具的使用原理
        //8.kotlin的内联函数
        //9.原子对象
        //10.项目中遇到的异常捕获机制，全局异常捕获机制
        //11.Arouter的使用和原理 拦截器
        //12.泛型
        //13.glide 原理，为什么生命周期和Activity相同
    }

    //了解线程吗？
    private void question1(){
        //1.线程是什么:
           //在一个进程内部又可以执行多个任务，而这每一个任务我们就可以看成是一个线程。
           //线程是程序使用CPU的基本单位。注意：线程是依赖于进程存在的。而是为了提高应用程序的使用率。
        //2.线程的状态:
          //新建状态:
             //使用 new 关键字和 Thread 类或其子类建立一个线程对象后，该线程对象就处于新建状态。它保持这个状
             //态直到程序 start() 这个线程。
        //就绪状态:
            //当线程对象调用了start()方法之后，该线程就进入就绪状态。就绪状态的线程处于就绪队列中，要等待
            //JVM里线程调度器的调度。
        //运行状态:
            //如果就绪状态的线程获取 CPU 资源，就可以执行 run()，此时线程便处于运行状态。处于运行状态的线程
            //最为复杂，它可以变为阻塞状态、就绪状态和死亡状态。
        //阻塞状态:
            //如果一个线程执行了sleep（睡眠）、suspend（挂起）等方法，失去所占用资源之后，该线程就从运行状态
                //进入阻塞状态。在睡眠时间已到或获得设备资源后可以重新进入就绪状态。可以分为三种：
            //等待阻塞：运行状态中的线程执行 wait() 方法，使线程进入到等待阻塞状态。
            //同步阻塞：线程在获取 synchronized同步锁失败(因为同步锁被其他线程占用)。
            //其他阻塞：通过调用线程的 sleep() 或 join() 发出了 I/O请求时，线程就会进入到阻塞状态。当sleep()
              //状态超时，join() 等待线程终止或超时，或者 I/O 处理完毕，线程重新转入就绪状态。
        //死亡状态:
          //一个运行状态的线程完成任务或者其他终止条件发生时，该线程就切换到终止状态。
        //3.线程控制:
          //sleep(休眠线程):在指定的毫秒数内让当前正在执行的线程休眠（暂停执行），此操作受到系统计时器和调度程序精度和准
                //确性的影响。让其他线程有机会继续执行，但它并不释放对象锁。也就是如果有Synchronized同步块，
                //其他线程仍然不能访问共享数据。
          //join(加入线程):join等待该线程执行完毕了以后,其他线程才能再次执行一个线程B“加入”到另外一个线程A的尾部。
                 //在A执行完毕之前，B不能工作。
          //yield(礼让线程):暂停当前正在执行的线程对象，并执行其他线程。
          //中断线程:stop:停止线程的运行 interrupt:中断线程
        //4.多线程并发安全问题
          //同步代码块的格式:同步的出现解决了多线程的安全问题
               //目标代码段同一时间只会被一个线程执行，进而保证了目标代码段的原子性
          //ReentrantLock是Lock的实现类，是一个互斥的同步器，在多线程高竞争条件下，
              //ReentrantLock比synchronized有更加优异的性能表现。
              //Lock使用起来比较灵活，但是必须有释放锁的配合动作
              //Lock必须手动获取与释放锁，而synchronized不需要手动释放和开启锁
              //Lock只适用于代码块锁，而synchronized可用于修饰方法、代码块等
        //死锁:
          //互斥条件：一个资源每次只能被一个线程使用。
          //请求与保持条件：一个线程因请求资源而阻塞时，对已获得的资源保持不放。
          //不剥夺条件：线程已获得的资源，在未使用完之前，不能强行剥夺。
          //循环等待条件：若干线程之间形成一种头尾相接的循环等待资源关系。

    }
    //databinding的双向绑定机制 edittext 就是双向绑定机制
    private void question2(){
        //正向绑定：当user的name值变化的时候，EidtView的text也同时变化；
        //反向绑定：当EidtView的text变化的时候，user对象的name值也同时变化
          //我们的实体类（User类）继承BaseObservale类
          //Getter上使用注解@Bindable
          //在Setter里调用方法notifyPropertyChanged
          //当代码中设置user的name属性的时候user.setName(name)，调用notifyPropertyChanged(BR.name)，这个
          //方法在BaseObservable类中定义，它会根据BR.name这个ID去通知相应的UI进行更新。以上就在user的name
          //属性变化以后，EditView的text会自动的发送变化，就实现了正向绑定。
        //那怎么实现反向绑定呢？对于一部分属性，在XML中的引用中把“@{}”改成“@={}”
    }
    //lifecycle的实现原理 A startactivity  B
    private void question3(){
        //简单来说就是监听Activity和Fragment的生命周期变化
          //a.生命周期拥有着和生命周期被观察者之间快速方便的建立一种联系，在生命周期拥有者的生命周期变化时，观察者会收到对应的通知。
          //b.可以方便的判断当前生命周期拥有者所处在的生命周期状态。
        //LifeCycleOwner 生命周期拥有者：即Activity和Fragment
        //LifeCycleObserver 生命周期观察者：可以是任何类，比如自定义View
        //如何使用
          //LifeCycleOwner：AppCompatActivity与V4中的Fragment都已默认实现了LifeCyclerOwner接口，所以可以直接使用。
          //LifeCycleObserver：生命周期观察者需要实现LifeCycleObserver接口，这个接口没有任何方法。
          //那他如何对生命周期进行观察呢？答案是依赖注解-->OnLifecycleEvent
    }

    //手机屏幕横竖屏切换的原理 数据保存 对象数据保存
    private void question4(){
        //横竖屏切换时不重新载入数据，只需在menifest中加入：
        //android:configChanges="keyboardHidden|orientation"
    }
    //JAVA内存GC
    private void question6(){
        //这是因为在Java虚拟机中，存在自动内存管理和垃圾清扫机制。概括地说，该机制对JVM（Java Virtual Machine）
        // 中的内存进行标记，并确定哪些内存需要回收，根据一定的回收策略，自动的回收内存，永不停息（Nerver Stop）
        // 的保证JVM中的内存空间，防止出现内存泄露和溢出问题。
    }
    //leak内存检测工具的使用原理
    private void question7(){
        //原来是使用MAT内存模型图。自从有了LeakCanary，原理都是一样的，都是dump java heap出来进行分析，找到泄漏的问题，只是LeakCanary帮我们把分析的工作做了。
    }
    //原子对象
    private void question9(){
        //原子性，简单说就是相关操作不会中途被其他线程干扰，一般通过同步机制实现。
        //原子对象:不能被分开操作的一段代码，就叫原子对象。。
         //你在ATM取款机取钱，ATM程序中吐钱跟到在你账户上扣掉等额的数目就是一个原子性的操作，
         //这两个动作一定要连在一起操作，要么都成功，要么都失败，不可以被分开只执行某一部分。
    }
    //泛型
    private void question12(){
        //a.“泛型” 意味着编写的代码可以被不同类型的对象所重用。泛型的提出是为了编写重用性更好的代码。泛型的本质
        //是参数化类型，也就是说所操作的数据类型被指定为一个参数。
        //b.泛型的本质是参数化类型，也就是说所操作的数据类型被指定为一个参数。
        //类型参数的意义是告诉编译器这个集合中要存放实例的类型，从而在添加其他类型时做出提示，在编译时就为类型安全做了保证。
        //参数类型可以用在类、接口和方法的创建中，分别称为泛型类、泛型接口、泛型方法。
    }
    //glide 原理，为什么生命周期和Activity相同
    private void question13(){
        //LruCache 是个泛型类，主要原理是：把最近使用的对象用强引用存储在 LinkedHashMap 中，当缓存满时，
          //把最近最少使用的对象从内存中移除，并提供 get/put 方法完成缓存的获取和添加LruCache 是线程安全的，
          //因为使用了 synchronized 关键字。
        //Glide.with(context)创建了一个 RequestManager，同时实现加载图片与组件生命周期绑定：
          //在 Activity 上创建一个透明的 ReuqestManagerFragment 加入到 FragmentManager 中，通过添加的 Fragment
          //感知Activty\Fragment 的生命周期。因为添加到 Activity 中的 Fragment 会跟随Activity 的生命周期。在
          //RequestManagerFragment 中的相应生命周期方法中通过 liftcycle 传递给在 lifecycle 中注册的
          //LifecycleListener
        //Glide缓存机制;封装okhttp添加下载速度；图片变换功能
    }
    //协程
    private void question14(){
        //相信大家应该都了解线程的概念，线程在Android开发中一般用来做一些复杂耗时的操作，避免耗时操作阻塞主线
        //程而出现ANR的情况，例如IO操作就需要在新的线程中去完成。但是呢，如果一个页面中使用的线程太多，
        //线程间的切换是很消耗内存资源的，我们都知道线程是由系统去控制调度的，所以线程使用起来比较难于控制。
        //这个时候kotlin的协程就体现出它的优势了，kotlin协程是运行在线程之上的，它的切换由程序自己来控制，
        //无论是 CPU 的消耗还是内存的消耗都大大降低。所以大家赶紧来拥抱kotlin协程吧^
    }
    private String TAG = "MainActivity";
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onActivityResume");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onActivityResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onActivityResume");
    }
}

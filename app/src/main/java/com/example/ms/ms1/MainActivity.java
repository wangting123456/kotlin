package com.example.ms.ms1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //中软没给offer
        //1.如果一个类的方法是private，别人怎么调用
        //2.用过哪些开源框架
        //3.熟悉哪些设计模式 单例 工厂方法模式 抽象工厂
        //4.kotlin let
        //5.设计模式 MVP
        //6.app 性能优化 弱引用
        //7.自定义view
        //8.apk反编译 prograud除了混淆，还有别的用途吗
        //9.https的认识
        //10.rxjava + retrofit 出错怎么办
        //11.fragment的管理 加四大组件
    }
    //如果一个类的方法是private，别人怎么调用
    private void question1(){
        //私有方法是不可以被外部调用的，解决办法:
        //一.修改为public
        //二.在类内部添加一个public方法，该方法去调用私有，这样就不用改动原方法
        //三.使用反射机制调用私有的方法
    }
    //用过哪些开源框架
    private void question2(){
        //UI层
           //a.图片加载Picasso/Glide:
               //Picasso:非常方便。但因为picasso是自带的请求处理，没办法和已有的 http 请求统一调度
               //Glide:因为 Glide 的 with 方法不光接受 Context,还接受 Activity 和 Fragment,Context
                   //会自动的从他们获取，同时将 Activity/Fragment 作为 with()参数的好处是：图片加载会和
                   //Activity/Fragment 的生命周期保持一致，比如 Paused 状态在暂停加载，在 Resumed
                   //的时候又自动重新加载。
               //Glide 和 Picasso 都是非常完美的库。Glide 加载图像以及磁盘缓存的方式都要优于 Picasso，
                    //速度更快，并且 Glide 更有利于减少 OutOfMemoryError 的发生
           //b.依赖注入Butterknife:通过注解绑定视图，避免了 findViewById() 的操作.
                 //编译时对注解进行解析完成相关代码的生成，所以在项目编译时会略耗时，但不会影响运行时的性能
        //数据层
          //a.JSON解析:FastJson:阿里巴巴的开源JSON解析库，可以实现Java Bean和JSON字符串的互相转换，是序
              //列化经常使用的一种方式
          //b.异步网络请求处理:okhttp retrofit
          //c.数据库:greenDao:代码会自动将Bean对象创建成表，不再是传统的手写SQL语句。这里的数据库创建只需
              //要在Application中执行一次即可
        //EventBus是一款针对Android优化的发布/订阅事件总线（内部是观察者设计模式）。主要功能是替代
             //Intent,Handler,BroadCast在Fragment，Activity，Service，线程之间传递消息.优点是开销小，
             //代码更优雅。以及将发送者和接收者解耦。

        //性能优化: leakcanary 分析内存泄漏的神器，当程序出现内存泄漏时会在通知栏给出提示
    }

    //熟悉哪些设计模式 单例 工厂方法模式 抽象工厂
    private void question3(){
        //简单工厂:规范接口—>实现类—>工厂创建
                   //你就要修改工厂类里面的生成产品的代码，在这里你就要增加if-else判断
        //工厂方法:就是通过对应的工厂类来生成对应的产品类，在这里我们就可以实现“开放-封闭”原则，
                   //无论加多少产品类，我们都不用修改原来类中的代码
        //抽象工厂:抽象工厂模式中我们可以定义实现不止一个接口，一个工厂也可以生成不止一个产品类，抽象
                 //工厂模式较好的实现了“开放-封闭”原则
    }

    //kotlin let
    private void question4(){
       //let 扩展函数的实际上是一个作用域函数。
    }
    //设计模式 MVP
    private void question5(){
      //MVC
        //Model（模型层）：主要是对数据相关的操作在Model层中，比如网络请求、数据库相关内容
        //View（视图层）：一般通过xml界面展示出来的与用户交互的UI展示
        //Controller（控制层）：主要是进行逻辑操作的相关内容，Android中一般activity为控制器。
          //在MVC中，Activity/Fragment既充当了控制器层，又充当了VIew层，导致耦合性很高，
          //如果项目的需求功能越来越多，Activity/Fragment中的代码会越来越庞大，会导致难以维护和测试。
          //所以就会有了后面的MVP的架构模式的出现。
     //MVP
        //Model（模型层）：同MVC中的Model层
        //View（视图层）：同MVC中的VIew层
        //Presenter（控制层）：主要是进行逻辑的操作，来支持View和Model之间的交互
          //在MVP模式中，主要的特点是面向接口编程的。它将业务从Activity/Fragment中分离出去，
          //把功能逻辑操作都放在了Persenter层，Activity/Fragment只负责视图层的展示，VIew层和Model
          //层没有任何交互，降低了耦合，更便于后期项目的维护和开发。
       // MVVM
        //Model（模型层）：同MVC中的Model层
        //View（视图层）：同MVC中的VIew层
        //ViewModel（视图模型层）：处理逻辑相关内容操作
         //在MVVM中，View和Model使用DataBinding来进行双向绑定，一方的改变都会影响另一方，开发者不用再去手动
         //修改UI的数据。项目结构更加的低耦合。


    }
    //6.app 性能优化 弱引用
    private void question6(){
       //1.布局优化:
           //布局复用，使用<include>标签重用layout；
           //提高显示速度，使用<ViewStub>延迟View加载；
        //2.绘制优化
           //ondraw方法内不要做耗时操作;ondraw()方法内不要创建新的局部变量，ondraw被频繁调用，很容易引起GC
        //3.线程优化
           //使用线程池来管理和复用线程，避免程序中有大量的Thread，同时可以控制线程的并发数，斌面相互抢占资源
           //而导致线程阻塞
        //4.刷新优化
            //减少刷新次数；
            //缩小刷新区域；
        //5.bitmap优化:
            //等比例压缩图片
            //不用的图片，及时recycle掉
        //5.动画优化
           //在实现动画效果时，需要根据不同场景选择合适的动画框架来实现。有些情况下，可以用硬件加速方式来提
             //供流畅度。

    }
    //7.自定义view
    private void question7(){
        //自定义view三种方式:组合现有控件，继承现有控件，继承View
        //继承view:
           //重写onMeasure、 onLayout、onDraw、onTouchEvent
           //onMeasure:可能多次触发，在measure的过程中注意MeasureSpec，specMode、specSize
           //onLayout:在ViewGroup中，只触发一次，决定子View的位置
           //onDraw:绘制内容，Canvas.drawxxx()，paint
           //onTouchEvent:处理点击事件
    }
    //8.apk反编译 prograud除了混淆，还有别的用途吗
    private void question8(){
        //压缩、优化、混淆和校验
        //它能够检测并删除无用的类、变量、方法和属性
        //它能够优化字节码并删除未使用的指令
        //它能够将类、变量和方法的名字重命名为无意义的名称从而达到混淆效果
        //最后，它还会校验处理后的代码，主要针对 Java 6 及以上版本和 Java ME
    }
    //9.https的认识
    private void question9(){
        //对称加密算法
          //发送方和接收方需要持有同一把密钥，发送消息和接收消息均使用该密钥。
        //非对称加密算法
          //接收方在发送消息前需要事先生成公钥和私钥，然后将公钥发送给发送方。发送放收到公钥后，将待发
          //送数据用公钥加密，发送给接收方。接收到收到数据后，用私钥解密。在这个过程中，公钥负责加密，
          //私钥负责解密，数据在传输过程中即使被截获，攻击者由于没有私钥，因此也无法破解。
    }
    //10.rxjava + retrofit 出错怎么办
    private void question10(){
        //okhttp:Http协议是一种应用层协议，它通过TCP实现了可靠的数据传输，能够保证数据的完整性、正确性
        //rxjava:被观察者和观察者通过订阅产生一种关系，当被观察者发生一些改变，通知观察者，观察者对应做出相应的回应。
        //retrofit:网络请求框架的封装，网络请求的工作本质上是 OkHttp 完成，而 Retrofit 仅负责 网络请求接口的封装
        //通过断开网络连接 模拟 网络异常错误（恢复网络即可成功发送请求）
    }
    //11.fragment的管理 加四大组件
    private void question11(){
        //Activity:
            //Activity的四种启动模式
        //Service:
           //Service分为两种:（这一种是运行在主线程中的，如果要执行耗时操作，可在service中创建一个异步来执行），
               //一种是IntentService（这是一种异步服务，是继承于Service的子类），所以推荐当要执行耗时操作时使用IntentService，
           //启动方式:
              //startService: 通过start方法启动的service一旦服务开启就跟调用者（开启者）没有任何关系了
                  //。开启者退出了，开启者挂了，服务还在后台长期的运行，开启者不能调用服务里面的方法;
               //bindservice:使用bind方法启动的服务，则调用者挂了，服务也挂了，调用者可以调用服务中的方法;
            //启动后回调的函数不同。
       //BroadcastReceiver:
           //广播分为两种:无序广播，有序广播
             //无序广播是完全异步的，在同一时刻在逻辑上是能够被所有的接收者接收到的，传递的效率高，缺点
             //是接收者不能处理结果传给下个接收者，并且无法终止广播的传播（其实有序广播就是和这个相反的）
           // 静态注册和动态注册区别
             //动态注册广播不是常驻型广播，也就是说广播跟随activity的生命周期。注意: 在activity结束前，
                //移除广播接收器。
             //静态注册是常驻型，也就是说当应用程序关闭后，如果有信息广播来，程序也会被系统调用自动运行。
       //ContentProvoder:
            //它主要的作用就是将程序的内部的数据和外部进行共享，为数据提供外部访问接口，
            //被访问的数据主要以数据库的形式存在，而且还可以选择共享哪一部分的数据
    }

}

package com.example.kotlin.kotlin_classandobject

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * 类与继承
 */
class MainActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Student("zhangsan")
        //Circle().draw()
        //Divad("nihao")
        //FillRectangle().draw()
        Cat().draw()
    }

    //1.类的声明
    /**
     * 类的声明
     * 使用class
     */
    class myclass{
        //类名，类头(指定其类型参数、主构造函数等) (可空)，大括号构成 (可空)
    }
    class empty

    /**
     * 创建类的实例
     * 就像普通函数一样，调用构造函数就好
     */
    val myclass1 = myclass()
    val person1 = Person1("lisi")

    //2.构造函数

    /**
     * 构造函数
     * 一个类有一个主构造函数，一个或多个次构造函数构成
     */
    class Person1 constructor(name:String){

    }

    //2.1主构造函数
    /**
     *   主构造函数没有任何注解/可见修饰符，constructor可以省略
     */
    class Person(name:String){

    }

    class Student(name:String){
        val TAG = "MainActivity"
        val firstName = name
        init {
            Log.d(TAG,"name is ${name}")
        }
        init {
            Log.d(TAG,"name length ${name.length}")
        }
    }

    //2.2次构造函数
    /**
     * 次构造函数
     * 前缀有constructor的次构造函数
     */
    class Employee(name:String){
        var children: MutableList<Employee> = mutableListOf<Employee>()
        constructor(name: String,parent:Employee):this(name){
            parent.children.add(this)
        }
    }
    /**
     * 如果类有一个主构造函数，每个次构造函数需要委托给主构造函数，可以直接委托或者通过次构造函数间接委托
     * 委托到同一个类的另一个构造函数，用this关键字
     */
    class Phone(name:String){
        var child:MutableList<Phone> = mutableListOf()
        constructor(name:String,price:String,phone: Phone):this(name){
            phone.child.add(this)
        }
    }

    /**
     * 如果一个类没有声明任何(主/次)构造函数，会生成一个不带参数的主构造函数，而且是public的
     * 如果你不希望你的类有公有的构造函数，那么就声明一个私有的构造函数
     */
    class myDemo private constructor(){

    }

    //主构造函数和次构造函数区别
      //1.主  class myDemo private constructor() 或者  class myDemo constructor() 或class myDemo()
      //2.次  constructor(name:String):this(name)

    //3.继承

    /**
     * 继承
     * 所有的类，都有共同的超类 Any
     * 没有超类型声明的类是默认 Any
     */
    class Empty{

    }

    /**
     * 显示的声明超类型
     * 派生类有主构造函数，其基类必须用派生类主构造函数的参数就地初始化
     */
    open class Base(name:String){

    }
    class myBase(name:String):Base(name){

    }

    /**
     * 派生类没有主构造函数，那个每个次构造函数必须使用super关键字初始化基类
     */
    class myView : View {
        constructor(ctx: Context):super(ctx)
        constructor(ctx:Context,attrs:AttributeSet):super(ctx,attrs)
    }

    //4.覆盖方法
    /**
     * 覆盖方法
     *
     */
     open class Shape{
        val TAG = "Shape"
        open fun draw(){
            Log.d(TAG,"draw")
        }
        fun fill(){
            Log.d(TAG,"fill")
        }

    }
    class Circle():Shape(){
        val TAG1 = "Circle"
        override fun draw() {
            super.draw()
            Log.d(TAG1,"draw")
        }
    }

    //5.覆盖属性
    /**
     * 覆盖属性
     */
    open class Shape2{
       open val index:Int = 4
    }
    class Oval:Shape2(){
        override var index = 8

    }

    //6.派生类初始化顺序
    /**
     * 派生类初始化顺序
     * 在构造派生类的新实例过程中，第一步完成其基类的初始化，发生在派生类的初始化之前
     */
    open class Base1(name:String){
        val TAG = "Base1"
        init {
            Log.d(TAG,"Base1 init")
        }
        open val size:Int = name.length.also {
            Log.d(TAG,"Base1 Initializing name size $it")
        }
    }
    class Divad(name: String):Base1(name.capitalize().also { Log.d("Base1","Argument for Base: $it")
    }){
        init {
            Log.d(TAG,"Divad init")
        }

        override val size:Int = name.length.also {
            Log.d(TAG,"Divad Initializing name size $it")
        }
    }
    //Base1: Argument for Base: Nihao
    //Base1: Base1 init
    //Base1: Base1 Initializing name size 5
    //Base1: Divad init
    //Base1: Divad Initializing name size 5

    //7.调用超类实现
    /**
     * 调用超类实现
     * 派生类中的代码可以使用super关键字调用其超类的函数和属性
     */
    open class Rectangle{
        open fun draw(){
            Log.d("Rectangle1","draw a Rectangle")
        }
        val borderclolr : String get() = "black"

    }
    class FillRectangle :Rectangle(){
        override fun draw() {
            super.draw()
            Log.d("Rectangle1","draw a FillRectangle")
        }
        val fillborder:String get() = super.borderclolr
    }
    /**
     * 在一个内部类中访问外部类的超类，通过super关键字实现 super@Outer
     */
    class Fill2Rectangle:Rectangle(){
        override fun draw() {
            super.draw()
            Log.d("Rectangle1","draw a Fill2Rectangle")
        }
        val fill2border:String get() = super.borderclolr
        inner class Filler{
            fun fill(){
                Log.d("Rectangle1","Filler draw a Fill2Rectangle")
            }
            fun drawAndFill(){

            }
        }
    }

    //8.继承规则
    /**
     * 继承规则
     * 为了表示从哪个超类型继承的实现，super<Base>
     */
    open class Anim{
        open fun  draw(){
            Log.d("Anim","Anim draw")
        }
    }
    interface Polygon{
        fun draw(){ //接口成员默认是open的
            Log.d("Anim","Polygon draw")
        }
    }
    class Cat:Anim(),Polygon{
        //提供其自身的实现
        override fun draw() {
            super<Anim>.draw()//调用 Anim.draw()
            super<Polygon>.draw()//调用 Polygon.draw()
            Log.d("Anim","Cat draw")
        }
    }

    //9.抽象类
    /**
     * 抽象类
     * 类以及其中的某些成员可以声明为abstract，抽象成员在本类中可以不用实现
     * 如下：我们可以用abstract成员覆盖非抽象的开放成员
     */
    open class Polygon1{
        open fun draw(){
            Log.d("Polygon1","Polygon1 draw")
        }
    }
    abstract class Polygon2:Polygon1(){
        abstract override fun draw()
    }




}
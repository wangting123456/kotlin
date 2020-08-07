package com.example.mode.mode_simplefactory;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;

/**
 * 简单工厂模式
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
        //1.含义
          //简单工厂模式又叫静态方法模式（因为工厂类定义了一个静态方法）
          //现实生活中，工厂是负责生产产品的；同样在设计模式中，简单工厂模式我们可以理解为负责生产对象的一个类，称为“工厂类”。
        //2.背景：小成有一个塑料生产厂，用来做塑料加工生意
        //3.目的：最近推出了3个产品，小成希望使用简单工厂模式实现3款产品的生产
        //4.缺点
          //工厂类集中了所有实例（产品）的创建逻辑，一旦这个工厂不能正常工作，整个系统都会受到影响；
          //违背“开放 - 关闭原则”，一旦添加新产品就不得不修改工厂类的逻辑，这样就会造成工厂逻辑过于复杂。
          //简单工厂模式由于使用了静态工厂方法，静态方法不能被继承和重写，会造成工厂角色无法形成基于继承的等级结构。
        try {
            //调用工厂类的静态方法 & 传入不同参数从而创建产品实例
            Factory.getProduct("A").Show();
        }catch (NullPointerException e){
            System.out.println("没有这一类产品");
        }

        try {
            //调用工厂类的静态方法 & 传入不同参数从而创建产品实例
            Factory.getProduct("B").Show();
        }catch (NullPointerException e){
            System.out.println("没有这一类产品");
        }

        try {
            //调用工厂类的静态方法 & 传入不同参数从而创建产品实例
            Factory.getProduct("C").Show();
        }catch (NullPointerException e){
            System.out.println("没有这一类产品");
        }
    }
}

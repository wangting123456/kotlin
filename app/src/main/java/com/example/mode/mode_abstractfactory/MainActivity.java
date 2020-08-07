package com.example.mode.mode_abstractfactory;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
        //一个工厂可以生产好几个产品
        FactoryA factoryA = new FactoryA();
        factoryA.ManufactureContainer().Show();
        factoryA.ManufactureModule().Show();

        FactoryB factoryB = new FactoryB();
        factoryB.ManufactureContainer().Show();
        factoryB.ManufactureModule().Show();

    }
    //容器产品抽象类
    abstract class ContainerProduct extends AbstractProduct{
        @Override
        public abstract void Show();
    }

    //模具产品抽象类
    abstract class MouldProduct extends AbstractProduct{
        @Override
        public abstract void Show();
    }

    //容器产品A类
   public  class ContainerProductA extends ContainerProduct{
        @Override
        public void Show() {
            System.out.println("生产出了容器产品A");
        }
    }

    //容器产品B类
    public class ContainerProductB extends ContainerProduct{
        @Override
        public void Show() {
            System.out.println("生产出了容器产品B");
        }
    }

    //模具产品A类
    public class MouldProductA extends MouldProduct{

        @Override
        public void Show() {
            System.out.println("生产出了模具产品A");
        }
    }

    //模具产品B类
    public class MouldProductB extends MouldProduct{

        @Override
        public void Show() {
            System.out.println("生产出了模具产品B");
        }
    }

    public class FactoryA extends Factory {
        @Override
        public AbstractProduct ManufactureContainer() {
            return new ContainerProductA();
        }

        @Override
        public AbstractProduct ManufactureModule() {
            return new MouldProductA();
        }
    }
    public class FactoryB extends Factory {
        @Override
        public AbstractProduct ManufactureContainer() {
            return new ContainerProductB();
        }

        @Override
        public AbstractProduct ManufactureModule() {
            return new MouldProductB();
        }
    }
}

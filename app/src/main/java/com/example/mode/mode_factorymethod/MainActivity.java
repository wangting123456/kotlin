package com.example.mode.mode_factorymethod;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;

/**
 * 工厂方法模式
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
        //简单工厂模式缺点:工厂一旦需要生产新产品就需要修改工厂类的方法逻辑，违背了“开放 - 关闭原则
        //之所以可以解决简单工厂的问题，是因为工厂方法模式把具体产品的创建推迟到工厂类的子类（
        // 具体工厂）中，此时工厂类不再负责所有产品的创建，而只是给出具体工厂必须实现的接口，
        // 这样工厂方法模式在添加新产品的时候就不修改工厂类逻辑而是添加新的工厂子类，符合开
        // 放封闭原则，克服了简单工厂模式中缺点
        //客户要产品A
        FactoryA factoryA = new FactoryA();
        factoryA.Manufacture().show();
        //客户要产品B
        FactoryB factoryB = new FactoryB();
        factoryB.Manufacture().show();
    }

    /**
     * 创建具体产品类（继承抽象产品类）， 定义生产的具体产品；
     */
    class ProductA extends Product{

        @Override
        public void show() {
            System.out.println("生产出了产品A");
        }
    }
    class ProductB extends Product{

        @Override
        public void show() {
            System.out.println("生产出了产品B");
        }
    }

    /**
     * 工厂A类 - 生产A类产品
     */
    class FactoryA extends Factory{

        @Override
        public Product Manufacture() {
            return new ProductA();
        }
    }
    class FactoryB extends Factory{

        @Override
        public Product Manufacture() {
            return new ProductB();
        }
    }
}

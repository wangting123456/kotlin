package com.example.mode.mode_facadepattern;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;

/**
 * 外观模式
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
        SubSystemA_Light light = new SubSystemA_Light();
        SubSystemB_Television television = new SubSystemB_Television();
        SubSystemC_Aircondition aircondition = new SubSystemC_Aircondition();

     /*   //起床后开电器
        System.out.println("起床了");
        light.on();
        television.on();
        aircondition.on();
        System.out.println("可以看电视了");

        //睡觉时关电器
        System.out.println("睡觉了");
        light.off();
        television.off();
        aircondition.off();
        System.out.println("可以睡觉了");*/
        Facade facade = new Facade(light, television, aircondition);
        facade.on();
        facade.off();
    }
}

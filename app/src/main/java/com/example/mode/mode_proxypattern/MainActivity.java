package com.example.mode.mode_proxypattern;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;

/**
 * 代理模式
 * 给目标对象提供一个代理对象，并由代理对象控制对目标对象的引用
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
        Proxy proxy = new Proxy();
        proxy.buyMac();
    }
}

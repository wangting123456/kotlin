package com.example.mode.mode_proxypattern;

import android.util.Log;

public class Proxy implements Subject {
    RealSubject realSubject;
    public Proxy(){
         realSubject = new RealSubject();

    }
    @Override
    public void buyMac() {
        Log.d("Proxy","buyMac");
        //调用真实对象的方法，进行代理购买Mac
        realSubject.buyMac();
    }
}

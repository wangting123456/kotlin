package com.example.mode.mode_proxypattern;

import android.util.Log;

public class RealSubject implements Subject {
    @Override
    public void buyMac() {
        Log.d("RealSubject","buyMac");
    }
}

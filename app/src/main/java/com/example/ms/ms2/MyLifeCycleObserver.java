package com.example.ms.ms2;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;


public class MyLifeCycleObserver implements LifecycleObserver {
    private String TAG = MyLifeCycleObserver.class.getSimpleName();
    public MyLifeCycleObserver(){
        //这里MyLifeCycleObserver对LifeCycleOwner的ON_RESUME和ON_PAUSE方法进行了观察，
        //在LifeCycleOwner的生命周期产生变化的时候会调用LifeCycleObserver中注解修饰的方法。
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onActivityResume(){
        Log.d(TAG,"onActivityResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onActivityPause(){
        Log.d(TAG,"onActivityPause");
    }
}

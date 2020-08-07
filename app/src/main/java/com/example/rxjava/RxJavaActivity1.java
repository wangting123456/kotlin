package com.example.rxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * 最简单使用
 */
public class RxJavaActivity1 extends AppCompatActivity {
    private Disposable mDisposable;
    private String TAG = RxJavaActivity1.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
        //使用在Rxjava+retrofit；Rxjava+rxbinding；所用用到异步的地方(AsyncTask 、Handler作用)
        //1.定义:RxJava 是一个 基于事件流、实现异步操作的库
        //2.作用:类似于 Android中的 AsyncTask 、Handler作用
        //3.特点:更重要的是，随着程序逻辑的复杂性提高，它依然能够保持简洁 & 优雅
        //4.基本使用:如下
        Observable observable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                Log.e(TAG, "subscribe");
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onComplete();
            }
        });

        Observer<String> reader = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
                Log.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String value) {
                if ("2".equals(value)) {
                    mDisposable.dispose();
                    //断开订阅关系
                    return;
                }
                Log.e(TAG, "onNext:" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError=" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete()");
            }
        };
        //observable.subscribe(reader);

        //5.扩展：RxJava 提供了其他方法用于 创建被观察者对象Observable
        Observable observable1 = Observable.just("a", "b", "c");
        //observable1.subscribe(reader);

        String[] words = {"A", "B", "C"};
        Observable observable2 = Observable.fromArray(words);
        //observable2.subscribe(reader);

        //6.特别注意:提供了多个函数式接口 ，用于实现简便式的观察者模式
        Observable.just("hello").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "accept:" + s);
            }
        });

        //7.被观察者 Observable的subscribe()具备多个重载的方法

           //public final void subscribe(Observer<? super T> observer) {}
           //表示观察者对被观察者发送的任何事件都作出响应

           //public final Disposable subscribe() {}
           //表示观察者不对被观察者发送的事件作出任何响应（但被观察者还是可以继续发送事件）

           //public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete) {}
           // 表示观察者只对被观察者发送的Next事件、Error事件 & Complete事件作出响应

           //public final Disposable subscribe(Consumer<? super T> onNext) {}
           //表示观察者只对被观察者发送的Next事件作出响应
        Observable.just("123456").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "accept:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "accept:" + throwable);
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.e(TAG, "Action :");
            }
        });
        //8.可采用 Disposable.dispose():切断观察者 与 被观察者 之间的连接
           //即观察者 无法继续 接收 被观察者的事件，但被观察者还是可以继续发送事件

    }
}

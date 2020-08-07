package com.example.rxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;


public class RxjavaActivity7 extends AppCompatActivity {
    private String TAG = RxjavaActivity7.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
        //all();
        //contains();
        //takeWhile();
        //skipWhile();
        takeUntil();
    }

    /**
     * 判断发送的每项数据是否都满足 设置的函数条件
     * 若满足，返回 true；否则，返回 false
     */
    private void all(){
        Observable.just(1,0,3,4)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        //因为所有数据都满足函数内条件 （每项数据<=10）
                        return integer <= 10;
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                Log.d(TAG,"all aBoolean:"+aBoolean);
            }
        });
    }

    /**
     * 判断发送的每项数据是否满足 设置函数条件
     * 若发送的数据满足该条件，则发送该项数据；否则不发送
     */
    private void takeWhile(){
        // 每1s发送1个数据 = 从0开始，递增1，即0、1、2、3
        Observable.interval(1, TimeUnit.SECONDS)
                .takeWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return (aLong<3);
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d(TAG,"takeWhile aLong:"+aLong);
            }
        });


    }

    /**
     * 判断发送的每项数据是否满足 设置函数条件
     * 直到该判断条件 = false时，才开始发送Observable的数据
     */
    private void skipWhile(){
        //1. 每隔1s发送1个数据 = 从0开始，每次递增1
        Observable.interval(1, TimeUnit.SECONDS)
                // 2. 通过skipWhile（）设置判断条件
                .skipWhile(new Predicate<Long>(){
                    @Override
                    public boolean test( Long aLong) throws Exception {
                        return (aLong<5);
                        // 直到判断条件不成立 = false = 发射的数据≥5，才开始发送数据
                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Long value) {
                Log.d(TAG,"发送了事件 "+ value);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }
    /**
     * 作用：执行到某个条件时，停止发送事件
     */
    private void takeUntil(){
        // 1. 每1s发送1个数据 = 从0开始，递增1，即0、1、2、3
        Observable.interval(1, TimeUnit.SECONDS)
                // 2. 通过takeUntil的Predicate传入判断条件
                .takeUntil(new Predicate<Long>(){
                    @Override
                    public boolean test( Long integer) throws Exception {
                        return (integer>3);
                        // 返回true时，就停止发送事件
                        // 当发送的数据满足>3时，就停止发送Observable的数据
                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Long value) {
                Log.d(TAG,"发送了事件 "+ value);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });

    }

    /**
     * 作用：等到 skipUntil（） 传入的Observable开始发送数据，（原始）第1个Observable的数据才开始发送数据
     */
    private void skipUntil(){
        // （原始）第1个Observable：每隔1s发送1个数据 = 从0开始，每次递增1
        Observable.interval(1, TimeUnit.SECONDS)
                // 第2个Observable：延迟5s后开始发送1个Long型数据
                .skipUntil(Observable.timer(5, TimeUnit.SECONDS))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });
    }

    /**
     * 作用：判定两个Observables需要发送的数据是否相同
     */
    private void SequenceEqual(){
        Observable.sequenceEqual(
                Observable.just(4,5,6),
                Observable.just(4,5,6)
        )
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept( Boolean aBoolean) throws Exception {
                        Log.d(TAG,"2个Observable是否相同："+ aBoolean);
                        // 输出返回结果
                    }
                });
    }

    /**
     * 作用：判断发送的数据中是否包含指定数据
     */
    private void contains(){
        Observable.just(1,2,3,4)
                .contains(3)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG,"contains aBoolean:"+aBoolean);
                    }
                });
    }
}

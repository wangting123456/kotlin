package com.example.rxjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;


/**
 * 过滤操作符
 */
public class RxJavaActivity6 extends AppCompatActivity {
    private String TAG = RxJavaActivity6.class.getSimpleName();
    TextView tv_init;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
        tv_init = findViewById(R.id.tv_init);
        //根据 指定条件 过滤事件:filter  ofType skip distinct
        //根据 指定事件数量 过滤事件 take takeLast
        //根据 指定时间 过滤事件 throttleFirst（）/ throttleLast（） throttleWithTimeout （） / debounce（）
        //根据 指定事件位置 过滤事件 firstElement（） / lastElement（）
        //filter();
        //ofType();
        //skip();
        //distinct();
        //take();
        //throttle();
        //debounce();
        //firstElement();
        elementAt();
    }

    /**
     * 过滤 特定条件的事件
     */
    private void filter(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        })
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer o) throws Exception {
                        //return true 发送
                        //return false 不发送
                        return o > 2;
                    }
                })
                .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer o) {
                Log.d(TAG, "onNext:"+o);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
    }

    /**
     * 过滤 特定数据类型的数据
     */
    private void ofType(){
        Observable.just(1, "Carson", 3, "Ho", 5)
                .ofType(Integer.class)//筛选出 整型数据
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "ofType accept"+integer);
                    }
                });
    }

    /**
     * 跳过某个事件
     */
    private void skip(){
        Observable.just(1, 2, 3, 4, 5)
                .skip(1)// 跳过正序的前1项
                .skipLast(2)//跳过正序的后2项
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "skip accept"+integer);
                    }
                });
    }

    /**
     * 过滤事件序列中重复的事件 / 连续重复的事件
     */
    private void distinct(){
// 使用1：过滤事件序列中重复的事件
        Observable.just(1, 2, 3, 1 , 2 )
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        Log.d(TAG,"不重复的整型事件元素是： "+ integer);
                    }
                });

        // 使用2：过滤事件序列中 连续重复的事件
        // 下面序列中，连续重复的事件 = 3、4
        Observable.just(1,2,3,1,2,3,3,4,4 )
                .distinctUntilChanged()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        Log.d(TAG,"不连续重复的整型事件元素是： "+ integer);
                    }
                });
    }

    /**
     * 指定观察者最多能接收到的事件数量
     */
    private void take(){
      Observable.create(new ObservableOnSubscribe<Integer>() {
          @Override
          public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
              emitter.onNext(1);
              emitter.onNext(2);
              emitter.onNext(3);
              emitter.onNext(4);
          }
      }).take(2).subscribe(new Consumer<Integer>() {
          @Override
          public void accept(Integer integer) throws Exception {
              Log.d(TAG, "take accept"+integer);
          }
      });
    }

    /**
     * 指定观察者只能接收到被观察者发送的最后几个事件
     */
    private void takeLast(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
            }
        }).takeLast(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "take accept"+integer);
            }
        });
    }
    /**
     * throttleFirst（）/ throttleLast（）
     * 在某段时间内，只发送该段时间内第1次事件 / 最后1次事件
     */
    private void throttle(){
            /*在某段时间内，只发送该段时间内第1次事件 */
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        // 隔段事件发送时间
                        e.onNext(1);
                        Thread.sleep(500);

                        e.onNext(2);
                        Thread.sleep(400);

                        e.onNext(3);
                        Thread.sleep(300);

                        e.onNext(4);
                        Thread.sleep(300);

                        e.onNext(5);
                        Thread.sleep(300);

                        e.onNext(6);
                        Thread.sleep(400);

                        e.onNext(7);
                        Thread.sleep(300);
                        e.onNext(8);

                        Thread.sleep(300);
                        e.onNext(9);

                        Thread.sleep(300);
                        e.onComplete();
                    }
                }).throttleFirst(1, TimeUnit.SECONDS)//每1秒中采用数据
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d(TAG, "开始采用subscribe连接");
                            }

                            @Override
                            public void onNext(Integer value) {
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


             /*   在某段时间内，只发送该段时间内最后1次事件 */
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        // 隔段事件发送时间
                        e.onNext(1);
                        Thread.sleep(500);

                        e.onNext(2);
                        Thread.sleep(400);

                        e.onNext(3);
                        Thread.sleep(300);

                        e.onNext(4);
                        Thread.sleep(300);

                        e.onNext(5);
                        Thread.sleep(300);

                        e.onNext(6);
                        Thread.sleep(400);

                        e.onNext(7);
                        Thread.sleep(300);
                        e.onNext(8);

                        Thread.sleep(300);
                        e.onNext(9);

                        Thread.sleep(300);
                        e.onComplete();
                    }
                }).throttleLast(1, TimeUnit.SECONDS)//每1秒中采用数据
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d(TAG, "开始采用subscribe连接");
                            }

                            @Override
                            public void onNext(Integer value) {
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
     * Sample（）
     * 在某段时间内，只发送该段时间内最新（最后）1次事件
     * 与 throttleLast（） 操作符类似
     */
    private void sample(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // 隔段事件发送时间
                e.onNext(1);
                Thread.sleep(500);

                e.onNext(2);
                Thread.sleep(400);

                e.onNext(3);
                Thread.sleep(300);

                e.onNext(4);
                Thread.sleep(300);

                e.onNext(5);
                Thread.sleep(300);

                e.onNext(6);
                Thread.sleep(400);

                e.onNext(7);
                Thread.sleep(300);
                e.onNext(8);

                Thread.sleep(300);
                e.onNext(9);

                Thread.sleep(300);
                e.onComplete();
            }
        }).sample(1, TimeUnit.SECONDS)//每1秒中采用数据
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
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
     * throttleWithTimeout （） / debounce（）
     * 发送数据事件时，若2次发送事件的间隔＜指定时间，就会丢弃前一次的数据，直到指定时间内都没有新数据发射时才会发送后一次的数据
     */
    private void debounce(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // 隔段事件发送时间
                e.onNext(1);
                Thread.sleep(500);
                e.onNext(2); // 1和2之间的间隔小于指定时间1s，所以前1次数据（1）会被抛弃，2会被保留
                Thread.sleep(1500);  // 因为2和3之间的间隔大于指定时间1s，所以之前被保留的2事件将发出
                e.onNext(3);
                Thread.sleep(1500);  // 因为3和4之间的间隔大于指定时间1s，所以3事件将发出
                e.onNext(4);
                Thread.sleep(500); // 因为4和5之间的间隔小于指定时间1s，所以前1次数据（4）会被抛弃，5会被保留
                e.onNext(5);
                Thread.sleep(500); // 因为5和6之间的间隔小于指定时间1s，所以前1次数据（5）会被抛弃，6会被保留
                e.onNext(6);
                Thread.sleep(1500); // 因为6和Complete实践之间的间隔大于指定时间1s，所以之前被保留的6事件将发出

                e.onComplete();
            }
            //debounce 去毛刺
        }).debounce(1, TimeUnit.SECONDS)//每1秒中采用数据
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
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
     * 仅选取第1个元素
     */
    private void firstElement(){
        Observable.just(1,2,3,4)
                .firstElement().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "integer"+integer);
            }
        });
    }

    /**
     * 指定接收某个元素（通过 索引值 确定）
     */
    private void elementAt(){
// 使用1：获取位置索引 = 2的 元素
        // 位置索引从0开始
        Observable.just(1, 2, 3, 4, 5)
                .elementAt(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        Log.d(TAG,"获取到的事件元素是： "+ integer);
                    }
                });

// 使用2：获取的位置索引 ＞ 发送事件序列长度时，设置默认参数
        Observable.just(1, 2, 3, 4, 5)
                .elementAt(6,10)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        Log.d(TAG,"获取到的事件元素是： "+ integer);
                    }
                });
    }

}

package com.example.rxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


/**
 * 组合/合并操作符
 */
public class RxJavaActivity4 extends AppCompatActivity {
    private String TAG = RxJavaActivity4.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
        //1.作用:组合 多个被观察者（Observable） & 合并需要发送的事件
        //2.类型
        //组合多个被观察者:
        //concat（） / concatArray（）
        //merge（） / mergeArray（）
        //concatDelayError（） / mergeDelayError（）
        //合并多个事件：
        // zip():合并多个被观察者（Observable）发送的事件，生成一个新的事件序列（即组合过后的事件序列），并最终发送
        //3.应用场景 & 对应操作符 介绍
        //组合多个被观察者:
        //concat（） / concatArray（）作用:组合多个被观察者一起发送数据，合并后 按发送顺序串行执行
        //merge（） / mergeArray（）作用:组合多个被观察者一起发送数据，合并后 按时间线并行执行
        //concatDelayError（） / mergeDelayError（）:使用contact和merge时候，如果
        // 合并多个事件:
        //zip():作用:合并多个被观察者（Observable）发送的事件，生成一个新的事件序列（即组合过后的事件序列），并最终发送
        //特别注意:事件组合方式 = 严格按照原先事件序列 进行对位合并
        //最终合并的事件数量 = 多个被观察者（Observable）中数量最少的数量
        //combineLatest（）作用:当两个Observables中的任何一个发送了数据后，将先发送了数据的Observables
        //的最新（最后）一个数据 与 另外一个Observable发送的每个数据结合，最终基于该函数的结果发送数据
        //reduce（）作用:聚合的逻辑根据需求撰写，但本质都是前2个数据聚合，然后与后1个数据继续进行聚合，依次类推
        //collect（）作用:将被观察者Observable发送的数据事件收集到一个数据结构里
        //发送事件前追加发送事件
        //startWith（） / startWithArray()作用:在一个被观察者发送事件前，追加发送一些数据 / 一个新的被观察者
        //注：追加数据顺序 = 后调用先追加
        // 统计发送事件数量
        //count（）作用:统计被观察者发送事件的数量


        //concat();
        //merge();
        //concatArrayDelayError();
        //zip();
        //combineLatest();
        //reduce();
        //collect();
        //startWith();
        //count();
        demo();
    }

    private void concat() {
        //concat（）：组合多个被观察者（≤4个）一起发送数据
        // 注：串行执行
        Observable.concat(Observable.just(1, 2), Observable.just(3, 4))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "concat accept:" + integer);
                    }
                });
        Observable.concatArray(Observable.just(1, 2, 3),
                Observable.just(4, 5, 6),
                Observable.just(7, 8, 9),
                Observable.just(10, 11, 12),
                Observable.just(13, 14, 15)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "concatArray accept:" + integer);
            }
        });
    }

    private void merge() {
        Observable.merge(Observable.intervalRange(0, 3, 1,
                1, TimeUnit.SECONDS), // 从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                Observable.intervalRange(2, 3, 1, 1,
                        TimeUnit.SECONDS))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "merge accept:" + aLong);
                    }
                });
    }

    private void concatArrayDelayError() {
        //Observable.concatArrayDelayError
        Observable.concat(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                        emitter.onError(new NullPointerException()); // 发送Error事件，因为无使用concatDelayError，所以第2个Observable将不会发送事件
                        emitter.onComplete();
                    }
                }),
                Observable.just(4, 5, 6))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
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

    private void zip() {
        Observable observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "被观察者1发送了事件1");
                emitter.onNext(1);
                // 为了方便展示效果，所以在发送事件后加入2s的延迟
                Thread.sleep(1000);

                Log.d(TAG, "被观察者1发送了事件2");
                emitter.onNext(2);
                Thread.sleep(1000);

                Log.d(TAG, "被观察者1发送了事件3");
                emitter.onNext(3);
                Thread.sleep(1000);

                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());//设置被观察者1在工作线程1中工作
        Observable observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "被观察者2发送了事件A");
                emitter.onNext("A");
                Thread.sleep(1000);

                Log.d(TAG, "被观察者2发送了事件B");
                emitter.onNext("B");
                Thread.sleep(1000);

                Log.d(TAG, "被观察者2发送了事件C");
                emitter.onNext("C");
                Thread.sleep(1000);

                Log.d(TAG, "被观察者2发送了事件D");
                emitter.onNext("D");
                Thread.sleep(1000);

                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());//设置被观察者2在工作线程2中工作
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String string) throws Exception {
                return integer + string;
            }

        }).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d(TAG, "accept " + o);
            }
        });

    }

    private void combineLatest() {
        Observable.combineLatest(Observable.just(1L, 2L, 3L), Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS)
                , new BiFunction<Long, Long, Object>() {
                    @Override
                    public Object apply(Long aLong, Long aLong2) throws Exception {
                        Log.e(TAG, "合并的数据是： " + aLong + " " + aLong2);
                        return aLong + aLong2;
                        // 合并的逻辑 = 相加
                        // 即第1个Observable发送的最后1个数据 与 第2个Observable发送的每1个数据进行相加
                    }
                }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d(TAG, "combineLatest:" + o);
            }
        });
    }

    private void reduce() {
        Observable.just(1, 2, 3, 4).reduce(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                Log.d(TAG, "本次计算的数据是： " + integer + " 乘 " + integer2);
                return integer * integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "最终计算的结果是： " + integer);
                Log.d(TAG, "reduce:" + integer);
            }
        });
    }

    private void collect() {
        //1. 创建数据结构（容器），用于收集被观察者发送的数据
        Observable.just(1, 2, 3, 4, 5, 6).collect(new Callable<ArrayList<Integer>>() {
            @Override
            public ArrayList<Integer> call() throws Exception {
                return new ArrayList<>();
            }
            // 2. 对发送的数据进行收集
        }, new BiConsumer<ArrayList<Integer>, Integer>() {
            @Override
            public void accept(ArrayList<Integer> integers, Integer integer) throws Exception {
                integers.add(integer);
            }
        }).subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(ArrayList<Integer> integers) throws Exception {
                Log.d(TAG, "integers:" + integers);
            }
        });
    }

    private void startWith() {
        Observable.just(4, 5, 6)
                .startWith(0)
                .startWithArray(1, 2, 3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "startWith integers:" + integer);
                    }
                });
        Observable.just(4, 5, 6)
                .startWith(0)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "startWith integers:" + integer);
                    }
                });
    }

    private void count() {
        Observable.just(1, 2, 3)
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "count aLong:" + aLong);
                    }
                });
    }

    /**
     * 对于从磁盘 / 内存缓存中 获取缓存数据 的功能逻辑如下：
     */
    private void demo() {
        final String memorycache = null;
        final String diskCache = "从磁盘缓存中获取数据";
        //检查内存缓存是否有该数据的缓存
        Observable ob1 = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                if (memorycache != null) {
                    emitter.onNext(memorycache);
                } else {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io());

        //检查磁盘缓存是否有该数据的缓存
        Observable ob2 = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                if (diskCache != null) {
                    emitter.onNext(diskCache);
                } else {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io());

        /*
         * 设置第3个Observable：通过网络获取数据
         **/
        Observable<String> network = Observable.just("从网络中获取数据");

        //* 通过concat（） 和 firstElement（）操作符实现缓存功能
        //通过concat（）合并memory、disk、network 3个被观察者的事件（即检查内存缓存、磁盘缓存 & 发送网络请求）
        //并将它们按顺序串联成队列
        Observable.concat(ob1, ob2, network)
                // 2. 通过firstElement()，从串联队列中取出并发送第1个有效事件（Next事件），即依次判断检查memory、disk、network
                // 即本例的逻辑为：
                // a. firstElement()取出第1个事件 = memory，即先判断内存缓存中有无数据缓存；由于memoryCache = null，即内存缓存中无数据，所以发送结束事件（视为无效事件）
                // b. firstElement()继续取出第2个事件 = disk，即判断磁盘缓存中有无数据缓存：由于diskCache ≠ null，即磁盘缓存中有数据，所以发送Next事件（有效事件）
                // c. 即firstElement()已发出第1个有效事件（disk事件），所以停止判断。
                .firstElement()
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d(TAG, "最终获取的数据来源 =  " + o);
                    }
                });
    }

    String result = "数据源来自 = ";

    /**
     * 同时向2个数据源获取数据 -> 合并数据 -> 统一展示到客户端
     */
    private void demo2() {
        Observable ob1 = Observable.just("网络");
        Observable ob2 = Observable.just("本地");
        Observable.merge(ob1, ob2).subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe： ");
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG, "onNext： " + o);
                result += o + "+";
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "获取数据完成");
                Log.d(TAG, result);
            }
        });
    }

    // 定义Observable接口类型的网络请求对象
    Observable<RxJavaActivity3.Translation1> observable1;
    Observable<RxJavaActivity3.Translation2> observable2;

    private void zipdemo() {
        // 步骤1：创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();
        //步骤2：创建 网络请求接口 的实例
        GetRequest_Interface getRequest_interface = retrofit.create(GetRequest_Interface.class);
        //步骤3：采用Observable<...>形式 对 2个网络请求 进行封装
        observable1 = getRequest_interface.getCall().subscribeOn(Schedulers.io());
        observable2 = getRequest_interface.getCall_2().subscribeOn(Schedulers.io());

        // 即2个网络请求异步 & 同时发送
        // 步骤4：通过使用Zip（）对两个网络请求进行合并再发送
        Observable.zip(observable1, observable2, new BiFunction<RxJavaActivity3.Translation1, RxJavaActivity3.Translation2, Object>() {
            @Override
            public Object apply(RxJavaActivity3.Translation1 translation1, RxJavaActivity3.Translation2 translation2) throws Exception {
                return translation1.show() + " & " + translation2.show();
            }
        }).observeOn(AndroidSchedulers.mainThread())//在主线程接收 & 处理数据
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d(TAG, "最终接收到的数据是：" + o);
                    }
                }, new Consumer<Throwable>() {
                    // 网络请求错误时调用
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("登录失败");
                    }
                });
    }

    public interface GetRequest_Interface {

        // 网络请求1
        @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
        Observable<RxJavaActivity3.Translation1> getCall();

        // 网络请求2
        @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20china")
        Observable<RxJavaActivity3.Translation2> getCall_2();


    }


}

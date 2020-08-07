package com.example.rxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建操作符
 */
public class RxJavaActivity2 extends AppCompatActivity {
    private String TAG = RxJavaActivity2.class.getSimpleName();
    OkHttpClient okHttpClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
        //1.作用:创建 被观察者（ Observable） 对象 & 发送事件。
        //2.类型:
           //基本创建:create()
           //快速创建&发送事件:just();fromArray();fromIterable()
           //延迟创建
        //3. 例子
          //基本创建:create()
          //快速创建 & 发送事件
             //just():
               //作用:快速创建1个被观察者对象（Observable）;
               //发送事件的特点：直接发送 传入的事件
               //注意:最多只能发送10个参数
            //fromArray():
              //作用:快速创建1个被观察者对象（Observable）
              //发送事件的特点：直接发送 传入的数组数据;会将数组中的数据转换为Observable对象
              //应用场景:快速创建 被观察者对象（Observable） & 发送10个以上事件（数组形式）
          //fromIterable()
             //作用:快速创建1个被观察者对象（Observable）
             //发送事件的特点：直接发送 传入的集合List数据
             //会将数组中的数据转换为Observable对象
          //额外
             //该方法创建的被观察者对象发送事件的特点：仅发送Complete事件，直接通知完成
             //Observable observable1=Observable.empty();
             //即观察者接收后会直接调用onCompleted（）

        //该方法创建的被观察者对象发送事件的特点：仅发送Error事件，直接通知异常
        //可自定义异常
        //Observable observable2=Observable.error(new RuntimeException());
        // 即观察者接收后会直接调用onError（）

        //该方法创建的被观察者对象发送事件的特点：不发送任何事件
        //Observable observable3=Observable.never();
        //即观察者接收后什么都不调用

        //延迟创建:
           //定时操作：在经过了x秒后，需要自动执行y操作
           //周期性操作：每隔x秒后，需要自动执行y操作
        //defer():
           //作用:直到有观察者（Observer ）订阅时，才动态创建被观察者对象（Observable） & 发送事件
           //应用场景:动态创建被观察者对象（Observable） & 获取最新的Observable对象数据
        //timer():
           //作用:快速创建1个被观察者对象（Observable）
           //发送事件的特点：延迟指定时间后，发送1个数值0（Long类型）
           //本质:延迟指定时间后，调用一次 onNext(0)
           //注意:timer操作符默认运行在一个新线程上
           //也可自定义线程调度器（第3个参数）：timer(long,TimeUnit,Scheduler)
        //interval():
           //作用:快速创建1个被观察者对象（Observable）
           //发送事件的特点：每隔指定时间 就发送 事件
           //发送的事件序列 = 从0开始、无限递增1的的整数序列
        //intervalRange():
           //作用:快速创建1个被观察者对象（Observable）
           //发送事件的特点：每隔指定时间 就发送 事件，可指定发送的数据的数量
           //a. 发送的事件序列 = 从0开始、无限递增1的的整数序列
           //b. 作用类似于interval（），但可指定发送的数据的数量
        //range():
           //作用:快速创建1个被观察者对象（Observable）
           //发送事件的特点：连续发送 1个事件序列，可指定范围
           //a. 发送的事件序列 = 从0开始、无限递增1的的整数序列
           //b. 作用类似于intervalRange（），但区别在于：无延迟发送事件

        //just();
        //formArray();
        //defer();
        //timer();
        //interval();
        //intervalRanges();
        okHttpClient = new OkHttpClient.Builder()
                //.addInterceptor(interceptor)
                //   .addInterceptor(basicParamsInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                // 设置缓存
                //错误重连
                .retryOnConnectionFailure(true)
                .build();
        demo12();
    }

    private void just() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext:" + integer);
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

    private void formArray() {
        Integer[] items = {0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12};
        Observable.fromArray(items).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "accept" + integer);
            }
        });
    }

    private void fromIterable() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Observable.fromIterable(list).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "accept" + integer);
            }
        });
    }

    Integer i = 10;

    private void defer() {
        //通过defer 定义被观察者对象
        //注：此时被观察者对象还没创建
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(i);
            }
        });
        i = 15;

        // 观察者开始订阅
        // 注：此时，才会调用defer（）创建被观察者对象（Observable）
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                //因为是在订阅时才创建，所以i值会取第2次的赋值
                Log.d(TAG, "integer:" + integer);
            }
        });

    }

    /**
     * 应用场景
     * 延迟指定事件，发送一个0，一般用于检测
     */
    private void timer() {
        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d(TAG, "accept " + aLong);
            }
        });
    }

    private void interval() {
        // 参数说明：
        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；
        Observable.interval(2, 1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d(TAG, "accept " + aLong);
            }
        });
    }

    /* private void intervalRanges(){
         // 参数1 = 事件序列起始点；
         // 参数2 = 事件数量；
         // 参数3 = 第1次事件延迟发送时间；
         // 参数4 = 间隔时间数字；
         // 参数5 = 时间单位
         Observable.intervalRange(3,10,2, 1, TimeUnit.SECONDS).
                 subscribe(new Consumer<Long>() {
             @Override
             public void accept(Long aLong) throws Exception {
                 Log.d(TAG,"accept "+aLong);
             }
         });
     }*/
    private void range() {
        // 参数说明：
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 注：若设置为负数，则会抛出异常
        Observable.range(3, 10)
                // 该例子发送的事件序列特点：从3开始发送，每次发送事件递增1，一共发送10个事件
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }
                    // 默认最先调用复写的 onSubscribe（）

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

    private void demo12() {
        Observable.interval(2, 8, TimeUnit.SECONDS)
                // 参数说明：
                // 参数1 = 第1次延迟时间；
                // 参数2 = 间隔时间数字；
                // 参数3 = 时间单位；
                // 该例子发送的事件特点：延迟2s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）

                /*  * 步骤2：每次发送数字前发送1次网络请求（doOnNext（）在执行Next事件前调用）
                 * 即每隔1秒产生1个数字前，就发送1次网络请求，从而实现轮询需求
                 **/
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long integer) throws Exception {
                        Log.d(TAG, "doOnNext accept 第 " + integer + " 次轮询");
                        // a. 创建Retrofit对象
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://192.168.8.16:8280") // 设置 网络请求 Url
                                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                                .build();

                        // b. 创建 网络请求接口 的实例
                        MainServices request = retrofit.create(MainServices.class);
                        RequestEntity entity = new RequestEntity();
                        entity.putFilter("type", "A");
                        // c. 采用Observable<...>形式 对 网络请求 进行封装
                        Observable<Response> observable = request.queryFileDetail(entity.toJsonString());
                        // d. 通过线程切换发送网络请求
                        observable.subscribeOn(Schedulers.io())               // 切换到IO线程进行网络请求
                                .observeOn(AndroidSchedulers.mainThread())  // 切换回到主线程 处理请求结果
                                .subscribe(new Observer<Response>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                        Log.d(TAG, "getFileDetailList onSubscribe");
                                    }

                                    @Override
                                    public void onNext(Response result) {
                                        Log.d(TAG, "getFileDetailList onNext" + result.getMessage());
                                        // e.接收服务器返回的数据
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.d(TAG, "getFileDetailList onError");
                                    }

                                    @Override
                                    public void onComplete() {
                                        Log.d(TAG, "getFileDetailList onComplete");
                                    }
                                });

                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe");
            }

            @Override
            public void onNext(Long value) {
                Log.d(TAG, " onNext:" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, " 对Complete事件作出响应");
            }
        });

    }
}

package com.example.rxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


/**
 * 变换操作符
 */
public class RxJavaActivity3 extends AppCompatActivity {
    private String TAG = RxJavaActivity3.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
        //1.作用:
        //对事件序列中的事件 / 整个事件序列 进行加工处理（即变换），使得其转变成不同的事件 / 整个事件序列
        //2.类型
        //map();flatmap();concatmap();Buffer()
        //3.应用场景 & 对应操作符 介绍
        //map()
        //作用:对 被观察者发送的每1个事件都通过 指定的函数 处理，从而变换成另外一种事件
        //应用场景:数据类型转换
        //具体使用:下面以将 使用Map（） 将事件的参数从 整型 变换成 字符串类型 为例子说明
        //map();
        //FlatMap()
        //作用：将被观察者发送的事件序列进行 拆分 & 单独转换，再合并成一个新的事件序列，最后再进行发送
        //原理
        //为事件序列中每个事件都创建一个 Observable 对象；
        //将对每个 原始事件 转换后的 新事件 都放入到对应 Observable对象；
        //将新建的每个Observable 都合并到一个 新建的、总的Observable 对象；
        //新建的、总的Observable 对象 将 新合并的事件序列 发送给观察者（Observer）
        //应用场景:无序的将被观察者发送的整个事件序列进行变换
        //flatMap();
        //ConcatMap()
        //作用：类似FlatMap（）操作符
        //与FlatMap（）的 区别在于：拆分 & 重新合并生成的事件序列 的顺序 = 被观察者旧序列生产的顺序
        //应用场景:有序的将被观察者发送的整个事件序列进行变换
        //ConcatMap();
        //Buffer()
        //Buffer（）
        //作用:定期从 被观察者（Obervable）需要发送的事件中 获取一定数量的事件 & 放到缓存区中，最终发送
        //应用场景:缓存被观察者发送的事件
        //buffer();
        //网络请求嵌套回调

        //List();
        //compare();
        retrofit();
    }

    private void map() {
        //map() 将参数中的 Integer 类型对象转换成一个 String类型 对象后返回
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                Log.d(TAG, "map apply integer :" + integer);
                return integer + "  i am";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String str) throws Exception {
                Log.d(TAG, "Consumer accept str :" + str);
            }
        });
    }

    private void flatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList();
                for (int i = 0; i < 3; i++) {
                    list.add("事件" + integer + "拆分后的" + i);
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Exception {
                Log.d(TAG, "Consumer accept str :" + o);
            }
        });
    }

    private void ConcatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList();
                for (int i = 0; i < 3; i++) {
                    list.add("事件" + integer + "拆分后的" + i);
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Exception {
                Log.d(TAG, "Consumer accept str :" + o);
            }
        });
    }

    private void buffer() {
        Observable.just(1, 2, 3, 4, 5)
                .buffer(4, 1)// 设置缓存区大小 & 步长
                // 缓存区大小 = 每次从被观察者中获取的事件数量
                // 步长 = 每次获取新事件的数量
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "buffer onSubscribe  :" + d);
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.d(TAG, " 缓存区里的事件数量 = " + integers.size());
                        for (Integer value : integers) {
                            Log.d(TAG, " 事件 = " + value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "buffer apply  :" + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "buffer onComplete  :");
                    }
                });

    }

    private void List() {
        List<QuestionInfo> list = new ArrayList<>();
        list.add(new QuestionInfo(1, "1", "1", "1"));
        list.add(new QuestionInfo(2, "3", "3", "3"));
        list.add(new QuestionInfo(3, "4", "4", "4"));
        QuestionInfo q = new QuestionInfo(1, "1", "1", "1");
        Log.d(TAG, "list.contains(q):" + list.contains(q));
        if (list.contains(q)) {
            //contains方法源码里面使用的是equals 判断是否相等。
            //使用“==”比较对象的地址，如果是同一对象即地址相同的情况下，才会返回true，而对于对象属性值相同但地址不同的不同对象，始终返回false！
        }
        //则需要重写Object的equals方法，并在equals方法中一一比较对象的每个属性值
    }

    private void compare() {
        List<Integer> intList = Arrays.asList(2, 3, 1);
        print(intList);//2,3,1
        Collections.sort(intList);//默认正序
        print(intList);//1,2,3
        Collections.sort(intList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                /* return o1 - o2;*/ // 1,2,3正序
                return o2 - o1;//3,2,1 逆序
            }
        });
        print(intList);
        //自定义类
        List<QuestionInfo> list = new ArrayList<>();
        list.add(new QuestionInfo(2, "2", "2", "2"));
        list.add(new QuestionInfo(1, "1", "1", "1"));
        list.add(new QuestionInfo(3, "3", "3", "3"));
        Collections.sort(list, new Comparator<QuestionInfo>() {
            @Override
            public int compare(QuestionInfo o1, QuestionInfo o2) {
                return o1.getQuestionId() - o2.getQuestionId();//按照 QuestionId正序
            }
        });

    }

    private void print(List<Integer> list) {
        if (list != null && list.size() > 0) {
            for (Integer i : list) {
                Log.d(TAG, "print i:" + i);
            }
        }
    }

    private Observable<Translation1> observable;
    private Observable<Translation2> observable2;

    private void retrofit_rxjava() {
        //变化操作符的用法
        //步骤1:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();
        //步骤2:创建网络请求接口的实例
        GetRequest_Interface getRequest_interface = retrofit.create(GetRequest_Interface.class);
        observable = getRequest_interface.getCall();
        observable2 = getRequest_interface.getCall2();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Translation1>() {
                    @Override
                    public void accept(Translation1 translation1) throws Exception {
                        Log.d("RxJavaActivity3", "注册");
                        translation1.show();
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<Translation1, ObservableSource<Translation2>>() {
                    @Override
                    public ObservableSource<Translation2> apply(Translation1 translation1) throws Exception {
                        return observable2;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Translation2>() {
                    @Override
                    public void accept(Translation2 translation2) throws Exception {
                        translation2.show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //登陆失败
                        Log.d("RxJavaActivity3", "登陆失败");
                    }
                });

    }

    private void retrofit() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        //创建 网络请求接口 的实例
        GetRequest_Interface interfaces = retrofit.create(GetRequest_Interface.class);
        //对 发送请求 进行封装
        Call<Translation1> call = interfaces.getCall1();
        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation1>() {
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                Log.d(TAG, "response:" + response.message());
            }

            @Override
            public void onFailure(Call<Translation1> call, Throwable t) {
                Log.d(TAG, "response:" + call.toString());
            }
        });
    }

    public class Translation1 {
        private int status;
        private content content;

        private class content {
            private String from;
            private String to;
            private String vendor;
            private String out;
            private int errNo;
        }

        //定义 输出返回数据 的方法
        public String show() {

            Log.d("RxJavaActivity3", "翻译内容 = " + content.out);
            return "";

        }
    }

    public class Translation2 {
        private int status;
        private content content;

        private class content {
            private String from;
            private String to;
            private String vendor;
            private String out;
            private int errNo;
        }

        //定义 输出返回数据 的方法
        public String show() {

            Log.d("RxJavaActivity3", "翻译内容 = " + content.out);
return "";
        }
    }

    public interface GetRequest_Interface {
        @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20register")
        Observable<Translation1> getCall();

        @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20login")
        Observable<Translation2> getCall2();

        @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
        Call<Translation1> getCall1();
    }


}

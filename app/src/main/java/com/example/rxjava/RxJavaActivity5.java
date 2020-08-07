package com.example.rxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


/**
 * 功能性操作符
 */
public class RxJavaActivity5 extends AppCompatActivity {
    private String TAG = RxJavaActivity5.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
        //1.作用:辅助被观察者（Observable） 在发送事件时实现一些功能性需求
        //2.类型:
             //连接被观察者 & 观察者:
                  //subscribe（）作用:订阅，即连接观察者 & 被观察者
             //线程调度:
                  //subscribeOn（） & observeOn（）:
                    //1.作用:快速、方便指定 & 控制被观察者 & 观察者 的工作线程
                    //2.为什么指定:被观察者 （Observable） / 观察者（Observer）的工作线程 = 创建自身的线程
                            //若被观察者 （Observable） / 观察者（Observer）在主线程被创建，那么他们的工作（生产事件 / 接收& 响应事件）就会发生在主线程
                       //冲突:对于一般的需求场景，需要在子线程中实现耗时的操作；然后回到主线程实现 UI操作.
                       //解决方案:为了解决上述冲突，即实现 真正的异步操作，我们需要对RxJava进行 线程控制（也称为调度 / 切换）
                    //3.实现方式:采用 RxJava内置的线程调度器（ Scheduler ），即通过 功能性操作符subscribeOn（） & observeOn（）实现
                      //Observable.subscribeOn（Schedulers.Thread）：指定被观察者 发送事件的线程（传入RxJava内置的线程类型）
                      //Observable.observeOn（Schedulers.Thread）：指定观察者 接收 & 响应事件的线程（传入RxJava内置的线程类型）
                      //若Observable.subscribeOn（）多次指定被观察者 生产事件的线程，则只有第一次指定有效，其余的指定线程无效
                      //若Observable.observeOn（）多次指定观察者 接收 & 响应事件的线程，则每次指定均有效，即每指定一次，就会进行一次线程的切换
            //在事件的生命周期中操作:
                 //do():在某个事件的生命周期中调用
            //错误处理
                 //需求场景:发送事件过程中，遇到错误时的处理机制
                 //onErrorReturn（）:作用:遇到错误时，发送1个特殊事件 & 正常终止
                 //onErrorResumeNext（）:作用:遇到错误时，发送1个新的Observable
                 //onExceptionResumeNext():作用:遇到错误时，发送1个新的Observable
                 //retry（）:作用:重试，即当出现错误时，让被观察者（Observable）重新发射数据
        /*
                  <-- 1. retry（） -->
                  // 作用：出现错误时，让被观察者重新发送数据
                  // 注：若一直错误，则一直重新发送

                 <-- 2. retry（long time） -->
                  // 作用：出现错误时，让被观察者重新发送数据（具备重试次数限制
                  // 参数 = 重试次数

                 <-- 3. retry（Predicate predicate） -->
                  // 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送& 持续遇到错误，则持续重试）
                  // 参数 = 判断逻辑

                  <--  4. retry（new BiPredicate<Integer, Throwable>） -->
                  // 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送 & 持续遇到错误，则持续重试
                  // 参数 =  判断逻辑（传入当前重试次数 & 异常错误信息）

                 <-- 5. retry（long time,Predicate predicate） -->
                 // 作用：出现错误后，判断是否需要重新发送数据（具备重试次数限制
                 // 参数 = 设置重试次数 & 判断逻辑
                   */
          // 重复发送
              //repeat():无条件地、重复发送 被观察者事件
        //subscribe();
        //subscribeOn();
        //observeOn();
        //doTest();
        //onErrorReturn();
        //onErrorResumeNext();
        //retry();
        retryWhen();
        //repeat();
    }
    private void subscribe(){
        Observable observable =  Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                Log.d(TAG, "observable subscribe");
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
            }
        });
        observable.subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG, "对onNext事件作出响应 "+o);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对onError事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        });
    }

    private void subscribeOn(){
      /*  Observable observable =  Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                Log.d(TAG, "observable subscribe currentThread:"+Thread.currentThread());
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
            }
        });
        observable.subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG, "onNext currentThread:"+Thread.currentThread());
                Log.d(TAG, "对onNext事件作出响应 "+o);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对onError事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        });*/
        Observable ob1 =  Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                Log.d(TAG, "Observable  subscribe currentThread:"+Thread.currentThread().getName());
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
            }
        });
        ob1.subscribeOn(Schedulers.io());
        ob1.subscribeOn(Schedulers.newThread());
        ob1.observeOn(AndroidSchedulers.mainThread());
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "observer onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG, "observer 对onNext事件作出响应 "+o +"thread "+Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "observer 对onError事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "observer 对onComplete事件作出响应");
            }
        };
        ob1.subscribe(observer);
    }
    private void observeOn(){
        //整体方法调用顺序：观察者.onSubscribe（）> 被观察者.subscribe（）> 观察者.doOnNext（）>观察者.onNext（）>观察者.onComplete()
        Observable ob1 =  Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                Log.d(TAG, " subscribe ObservableEmitter:"+Thread.currentThread().getName());
                emitter.onNext(45);
                emitter.onNext(79);
                emitter.onComplete();
            }
        });
        ob1.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d(TAG, "doOnNext  o："+o + "第一次观察者Observer的工作线程是："+Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe ");
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG, " onNext "+o+"Thread.currentThread().getName():"+Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, " onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, " onComplete ");
            }
        });



        Observable ob2 =  Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                Log.d(TAG, " subscribe ObservableEmitter:"+Thread.currentThread().getName());
                emitter.onNext(45);
                emitter.onNext(79);
                emitter.onComplete();
            }
        });
        ob2.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d(TAG, "doOnNext  o："+o + "第一次观察者Observer的工作线程是："+Thread.currentThread().getName());
                    }
                })
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, " onSubscribe ");
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d(TAG, " onNext "+o+"Thread.currentThread().getName():"+Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, " onError ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, " onComplete ");
                    }
                });
    }

    private void doTest(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "subscribe");
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new Throwable("发生错误了"));
                //emitter.onComplete();
                Log.d(TAG, "subscribe完毕");
            }
        })
                // 1. 当Observable每发送1次数据事件就会调用1次
              /*  .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Exception {
                        Log.d(TAG, "doOnEach accept "+integerNotification);
                    }
                })*/// 2. 执行Next事件前调用
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "doOnNext: " + integer);
                    }
                })
                //3. 执行Next事件后调用
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "doAfterNext: " + integer);
                    }
                })
                //4. Observable正常发送事件完毕后调用
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "doOnComplete: ");
                    }
                })
                // 5. Observable发送错误事件时调用
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "doOnError: ");
                    }
                })
                //6. 观察者订阅时调用
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d(TAG, "doOnSubscribe: ");
                    }
                })
                // 7. Observable发送事件完毕后调用，无论正常发送完毕 / 异常终止
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "doAfterTerminate: ");
                    }
                })
                // 8. 最后执行
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doFinally: ");
                    }
                })
                .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext"+integer);
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
    private void onErrorReturn(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.e(TAG, "subscribe ");
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("发生错误了"));
                emitter.onNext(3);
                Log.e(TAG, "被观察者 ");
            }
        })
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) throws Exception {
                        Log.e(TAG, "在onErrorReturn处理了错误: "+throwable.toString() );
                        return 6666;
                    }
                })
                .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext:"+integer);
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
    private void onErrorResumeNext(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("错误"));
            }
        })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
                    @Override
                    public ObservableSource<? extends Integer> apply(Throwable throwable) throws Exception {
                        Log.d(TAG, "throwable:"+throwable.getMessage());
                        return Observable.just(11,22);
                    }
                })
                .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext:"+integer);
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
    private void retry(){
          Observable.create(new ObservableOnSubscribe<Integer>() {
              @Override
              public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                  e.onNext(1);
                  e.onNext(2);
                  e.onError(new Exception("发生错误了"));
                  e.onNext(3);
              }
          })    /*.retry()  // 遇到错误时，让被观察者重新发射数据（若一直错误，则一直重新发送*/
                /*.retry(3) // 设置重试次数 = 3次*/
                /*  .retry(new Predicate<Throwable>() {
                      @Override
                      public boolean test(Throwable throwable) throws Exception {
                          //返回false = 不重新重新发送数据 & 调用观察者的onError结束
                          //返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
                          return false;
                      }
                  })*/
               /* .retry(new BiPredicate<Integer, Throwable>() {
                    @Override
                    public boolean test(Integer integer, Throwable throwable) throws Exception {
                        // 捕获异常
                        Log.e(TAG, "异常错误 =  "+throwable.toString());

                        // 获取当前重试次数
                        Log.e(TAG, "当前重试次数 =  "+integer);

                        //返回false = 不重新重新发送数据 & 调用观察者的onError结束
                        //返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
                        return true;
                    }
                })*/
                  // 拦截错误后，判断是否需要重新发送请求
                  .retry(3, new Predicate<Throwable>() {
                      @Override
                      public boolean test(@NonNull Throwable throwable) throws Exception {
                          // 捕获异常
                          Log.e(TAG, "retry错误: "+throwable.toString());

                          //返回false = 不重新重新发送数据 & 调用观察者的onError（）结束
                          //返回true = 重新发送请求（最多重新发送3次）
                          return true;
                      }
                  })
                  .subscribe(new Observer<Integer>() {
              @Override
              public void onSubscribe(Disposable d) {
                  Log.d(TAG, "onSubscribe");
              }

              @Override
              public void onNext(Integer integer) {
                  Log.d(TAG, "onNext"+integer);
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
    private void retryUntil(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        }).retryUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() throws Exception {
                return false;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "accept"+integer);
            }
        });
    }
    private void retryWhen(){
         Observable.create(new ObservableOnSubscribe<Integer>() {
             @Override
             public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                 e.onNext(1);
                 e.onNext(2);
                 e.onError(new Exception("发生错误了"));
                 e.onNext(3);
             }
         })
                 // 遇到error事件才会回调
                 .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
             @Override
             public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                 // 参数Observable<Throwable>中的泛型 = 上游操作符抛出的异常，可通过该条件来判断异常的类型
                 // 返回Observable<?> = 新的被观察者 Observable（任意类型）
                 // 此处有两种情况：
                 // 1. 若 新的被观察者 Observable发送的事件 = Error事件，那么 原始Observable则不重新发送事件：
                 // 2. 若 新的被观察者 Observable发送的事件 = Next事件 ，那么原始的Observable则重新发送事件：
                 return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                     @Override
                     public ObservableSource<?> apply(Object o) throws Exception {
                            // 1. 若返回的Observable发送的事件 = Error事件，则原始的Observable不重新发送事件
                            // 该异常错误信息可在观察者中的onError（）中获得
                          //return Observable.just(new Throwable("retryWhen终止啦"));
                           // 2. 若返回的Observable发送的事件 = Next事件，则原始的Observable重新发送事件（若持续遇到错误，则持续重试）
                          return Observable.just(1);
                     }
                 });
             }
         }).subscribe(new Observer<Integer>() {
             @Override
             public void onSubscribe(Disposable d) {
                 Log.d(TAG, "onSubscribe");
             }

             @Override
             public void onNext(Integer integer) {
                 Log.d(TAG, "接收到了事件"+ integer  );
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

    private void repeat(){
        // 不传入参数 = 重复发送次数 = 无限次
       // repeat（）；
        // 传入参数 = 重复发送次数有限
        //repeat（Integer int ）；
        Observable.just(1,2,3)
                .repeat(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "accept"+integer);
                    }
                });
    }
    private void repeatWhen(){
        Observable.just(1,2,4).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            // 在Function函数中，必须对输入的 Observable<Object>进行处理，这里我们使用的是flatMap操作符接收上游的数据
            public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
                // 将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
                // 以此决定是否重新订阅 & 发送原来的 Observable
                // 此处有2种情况：
                // 1. 若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
                // 2. 若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Object throwable) throws Exception {

                        // 情况1：若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
                        return Observable.empty();
                        // Observable.empty() = 发送Complete事件，但不会回调观察者的onComplete（）

                        // return Observable.error(new Throwable("不再重新订阅事件"));
                        // 返回Error事件 = 回调onError（）事件，并接收传过去的错误信息。

                        // 情况2：若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                        // return Observable.just(1);
                        // 仅仅是作为1个触发重新订阅被观察者的通知，发送的是什么数据并不重要，只要不是Complete（） /  Error（）事件
                    }
                });

            }
        })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应：" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });
    }
}

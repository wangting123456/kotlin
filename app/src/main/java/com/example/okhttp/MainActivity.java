package com.example.okhttp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.kotlin.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * okhttp 简介
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
      //一.OKHttp定义
        //1.okhttp定义
          //是一个处理网络请求的开源项目，目前使用比较多的网络框架之一，是square公司用于替代Android提供的HttpUrlConnection和HttpClient
        //2.特点
          //基于建造者模式(将一个负责对象的构建和表示分离，用于属性参数很多时)
          //链式调用，每一个方法的返回值都是当前类的对象
        //3.优点
          //支持HTTP2/SPDY(SPDY是Google开发的基于TCP的传输层协议，用以最小化网络延迟，提升网络速度，优化用户的网络使用体验)
          //socket自动选择最优路线，并支持自动重连，拥有自动重连的socket连接池，减少握手次数。减少请求延迟，共享socket
            //减少对服务器的请求次数
          //拥有Interceptors轻松处理请求和相应(自动处理GZIP压缩)
       //二.OKHttp的功能
         //put delete post get 请求
         //文件的上传和下载
         //加载图片(内部会图片大小自动压缩)
         //支持请求回调,直接返回对象，对象集合
         //支持Session的保持
       //三.OKHttp的流程使用
           //get();
           post();
           //a.同步和异步都是通过Dispatch这个分发器来分发的。
           //b.异步操作比同步操作多了创建线程池的操作，开启了子线程。
           //c.同步和异步最后都走到了getResponseWithInterceptorChain这个方法
        //四.OKHttp拦截器
          //拦截器可以分为内部拦截器和外部拦截器
          //内部拦截器：okhttp系统的拦截器，有5个。
          //外部拦截器：就是我们自己定义的拦截器 /分为应用拦截器和网络拦截器，二者都需要实现Interceptor接口

          //CallServerInterceptor(读写拦截器)
             //最后一个拦截器，CallServerInterceptor，它负责实现网络 IO，所有拦截器都要依赖它才能拿到响应数据。
          //ConnectInterceptor (连接拦截器)
            //我们知道，TCP 协议需要需要建立连接才能进行通信，每次请求都建立连接会极大地影响通信效率。
            //OkHttp 的优点之一优化了连接的建立：内部维护了可以重复使用的 Socket 连接池，减少握手次数，加快请求响应。
            //1、连接 RealConnection 是对 Socket 的封装
            //2、OkHttp 的连接复用主要是通过 StreamAllocation 来实现的，每个连接上持有一个。
            //3、StreamAllocation 引用的列表，以此来标识当前连接是否空闲
            //4、判断连接是否可以重用，除了比较连接当前的 host，也可以比较路由信息
            //5、连接池在添加新连接时会运行清理任务，默认最多空闲连接为 5，最长空闲时间为 5 分钟
         //CacheInterceptor (缓存拦截器)
            //第三个拦截器是缓存处理拦截器 CacheInterceptor，它的重要性用一句话来描述：最快的请求就是不请求，直接用缓存。
            //首先，根据request来判断cache中是否有缓存的response，如果有，得到这个response，然后进行判断当前response是否有效，
            //没有将cacheCandate赋值为空。
            //根据request判断缓存的策略，是否要使用了网络，缓存 或两者都使用
            //调用下一个拦截器，决定从网络上来得到response
            //如果本地已经存在cacheResponse，那么让它和网络得到的networkResponse做比较，决定是否来更新缓存的cacheResponse
            //缓存未经缓存过的response
        //RetryAndFollowUpInterceptor （重定向拦截器）
        //       RetryAndFollowUpInterceptor 的拦截操作中做了这么几件事：
        //      1、创建一个 StreamAllocation
        //      2、发起请求
        //      3、请求异常时会重试
        //      4、根据响应码做重定向和重试
        //      5、重定向时如果地址不一致会释放连接
        //      6、另外也保存是否取消的状态值，在重试、请求得到响应后都会判断是否取消
        //二、BridgeInterceptor (桥接拦截器)
        //       内置的拦截器中第二个是 BridgeInterceptor。Bridge，桥，什么桥？连接用户请求信息 和 HTTP 请求的桥梁。
        //       BridgeInterceptor 负责把用户构造的请求转换为发送到服务器的请求、把服务器返回的响应转换为用户友好的响应。
        //      我们说下侨界拦截器大概都做了什么吧，请求前：
        //             1、如果这个请求有请求体，就添加 Content-Type, Content-Length 等
        //             2、如果这个请求没有 Host，就通过 url 来获取 Host 值添加到 Header 中
        //             3、如果这个请求没有接收的数据类型Accept-Encoding，且没指定接收的数据范围，就添加默认接受格式为 gzip
        //            5、去 CookieJar 中根据 url 查询 Cookie 添加到 Header
        //            6、如果当前没有，就添加 User-Agent 信息
        //发起请求后：
        //           7、解析响应 Header 中的 Cookie
        //           8、如果想要数据的格式是 gzip，就创建 GzipSource 进行解压，同时移除 Content-Encoding 和 Content-Length

    }

    /**
     * 下载一个URL并使用String打印它的内容。
     */
    private void get(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://www.baidu.com")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                  if(response.isSuccessful()){
                      System.out.println("get 成功");
                  }else {
                      System.out.println("get 失败");
                  }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("get","e:"+e.toString());
                }
            }
        });
    }
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    /**
     * 上传数据到服务器。
     * 异步
     */
    private void post(){
       final OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder body  = new FormBody.Builder();
        Map<String, String> params = new HashMap<>();
        params.put("isOnline", "1");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                body.add(entry.getKey(), entry.getValue());
            }
        }
        final Request request = new Request.Builder()
                .url("http://192.168.8.16:8280/terminalsystem/doNotify")
                .post(body.build())
                .build();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d("TAG","网络连接失败");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                Log.d("TAG","请求成功");
                                String result = response.body().string();
                                JSONObject jsonObject = com.alibaba.fastjson.JSON.parseObject(result);
                                if (jsonObject != null) {
                                    if (jsonObject.getIntValue("status") == 1) {
                                        Log.d("TAG","请求成功2");
                                    } else {
                                        Log.d("TAG","网络连接失败");
                                    }
                                }
                            } else {
                                Log.d("TAG","网络连接失败2");
                            }

                        }
                    });
                }
            }).start();


    }

    /**
     * 同步操作
     */
    private void post2(){
        final OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder body  = new FormBody.Builder();
        Map<String, String> params = new HashMap<>();
        params.put("isOnline", "1");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                body.add(entry.getKey(), entry.getValue());
            }
        }
        final Request request = new Request.Builder()
                .url("http://192.168.8.16:8280/terminalsystem/doNotify")
                .post(body.build())
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call call =  okHttpClient.newCall(request);
                try {
                    Response re = call.execute();
                    Log.d("TAG","re:"+re.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void lanjieqi(){
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new TokenHeaderInterceptor());
    }

    public class TokenHeaderInterceptor implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
           Request original =  chain.request();
            Request  update = original.newBuilder()
                    .header("token","122")
                    .build();
            return chain.proceed(update);
        }
    }
}

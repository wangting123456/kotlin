package com.example.okhttp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;

import java.io.IOException;

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
        /*
        OkHttp是一个高效HTTP客户端，原因如下：
        HTTP/2支持所有访问相同主机的请求共享一个套接字。
        连接池减少了请求延迟(如果HTTP/2不可用)。
        透明GZIP压缩减少了下载大小。
        响应缓存完全避免了重复请求的网络使用。
         */
        get();
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
                        .url("http://192.168.8.16:8280")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    response.body().toString();
                    Log.d("MainActivity","get():"+ response.body().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    /**
     * 上传数据到服务器。
     */
    private void post(String json){
       OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .url("http://192.168.8.16:8280")
                .post(body)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            response.body().toString();
            Log.d("MainActivity","get():"+ response.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

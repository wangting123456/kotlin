package com.example.webview;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        final WebView webView = findViewById(R.id.webview);
        //第一步设置可以交互
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        settings.setDomStorageEnabled(true);
        webView.loadUrl("file:///android_asset/web/index.html");

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(TAG,"shouldOverrideUrlLoading");
                //第二步加载网页
                view.loadUrl(url);
                 return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.d(TAG,"onPageStarted");
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d(TAG,"onPageFinished");
                super.onPageFinished(view, url);
                ////第二种方式 页面加载完成后，调用方法
                //调用无参
                view.loadUrl("javascript:callJS()");
                //调用有参
                view.loadUrl("javascript:callJSParameter('测试')");
            }
        });

        //第二种方式 只能从4.4（18）以上使用	获取返回值简单
        webView.evaluateJavascript("javascript:callJSReturn('方式二调用JS')", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                //此处为 js 返回的结果
                Log.d(TAG,"onReceiveValue:"+value);
                Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
            }
        });

        //JS 调用Android
        //第二步将Java对象映射到JS对象
        webView.addJavascriptInterface(this, "test");
    }



    //JS 调用Android
    //第一步 定义一个JS调用的方法
    @JavascriptInterface
    public void callAndroid(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}

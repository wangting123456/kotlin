package com.example.mode.mode_adapterpattern;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;

/**
 * 适配器模式
 * 把一个类的接口变换成客户端所期待的另一种接口，从而使原本接口不匹配而无法一起工作的两个类能够在一起工作。
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
        Target target = new Adapter();
        target.Request();
    }
    public interface Target{
        //这是源类Adapteee没有的方法
        public void Request();
    }

    //创建源类（Adaptee）
    public class Adaptee{
        public void SpecificRequest(){
            Log.d("Adaptee","SpecificRequest");
        }

    }

    public class Adapter extends Adaptee implements Target{

        @Override
        public void Request() {
            this.SpecificRequest();
        }
    }
}

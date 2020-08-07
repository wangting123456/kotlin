package com.example.mode.mode_strategy;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Saleman saleman = new Saleman();
        saleman.setStrage(new StrategyA());
        saleman.show();
    }

    class Saleman{
        private Strategy strategy;
        public void setStrage(Strategy strategy){
           this.strategy = strategy;
        }
        public void show(){
            strategy.show();
        }
    }
}

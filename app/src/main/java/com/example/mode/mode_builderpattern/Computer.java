package com.example.mode.mode_builderpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义具体产品类（Product）
 */
public class Computer {
    private List<String> parts = new ArrayList<>();

    public void add(String a){
        parts.add(a);
    }
    public void Show(){
        for (int i = 0;i<parts.size();i++){
            System.out.println("组件"+parts.get(i)+"装好了");
        }
        System.out.println("电脑组装完成，请验收");


    }
}

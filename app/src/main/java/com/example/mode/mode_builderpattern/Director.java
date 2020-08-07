package com.example.mode.mode_builderpattern;

import android.os.Build;

public class Director {
    public void Construct(Builder builder){
        builder.BuildCPU();
        builder.BuildMainboard();
        builder.BuildHD();
    }
}

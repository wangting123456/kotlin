package com.example.mode.mode_templatemethod;

public abstract class MyAbstarct {
    final void cookProcess(){
        pourOil();
    }
    void pourOil(){
        System.out.println("倒油");
    }
    void HeatOil(){
        System.out.println("热油");
    }
    abstract void pourVegetable();
    abstract void pourSauce();
    void fry(){
        System.out.println("炒啊炒啊炒到熟啊");
    }
}

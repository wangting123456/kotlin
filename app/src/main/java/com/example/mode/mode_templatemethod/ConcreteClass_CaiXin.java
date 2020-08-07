package com.example.mode.mode_templatemethod;

public class ConcreteClass_CaiXin extends MyAbstarct {
    @Override
    void pourVegetable() {
        System.out.println("下锅的蔬菜是菜心");
    }

    @Override
    void pourSauce() {
        System.out.println("下锅的蔬菜是蒜蓉");
    }
}

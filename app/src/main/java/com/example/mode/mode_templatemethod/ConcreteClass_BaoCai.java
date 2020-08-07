package com.example.mode.mode_templatemethod;

public class ConcreteClass_BaoCai extends MyAbstarct {
    @Override
    void pourVegetable() {
        System.out.println("下锅的蔬菜是包菜");
    }

    @Override
    void pourSauce() {
        System.out.println("下锅里面的是辣椒");
    }
}

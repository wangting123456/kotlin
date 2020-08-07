package com.example.mode.mode_simplefactory;


public class Factory {
    public static Product getProduct(String ProductName){
        switch (ProductName){
            case "A":
                return new ProduceA();

            case "B":
                return new ProduceB();

            case "C":
                return new ProduceC();

            default:
                return null;

        }
    }
}

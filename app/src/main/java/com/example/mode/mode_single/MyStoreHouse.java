package com.example.mode.mode_single;

public class MyStoreHouse {
    private static MyStoreHouse storeHouse = new MyStoreHouse();
    public static MyStoreHouse getInstance(){
        return storeHouse;
    }
    private MyStoreHouse(){

    }
    private int quantity = 100;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

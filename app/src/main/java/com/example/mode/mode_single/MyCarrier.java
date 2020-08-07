package com.example.mode.mode_single;

public class MyCarrier {
    public MyStoreHouse storeHouse;
    public MyCarrier(MyStoreHouse storeHouse){
        this.storeHouse = storeHouse;
    }
    //搬货进仓库
    public void movein(int i){
        storeHouse.setQuantity(storeHouse.getQuantity()+i);
    }
    //搬货出仓库
    public void moveout(int i){
        storeHouse.setQuantity(storeHouse.getQuantity()-i);
    }
}

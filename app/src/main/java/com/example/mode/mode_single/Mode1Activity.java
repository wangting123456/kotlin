package com.example.mode.mode_single;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kotlin.R;

/**
 * 单例模式
 */
public class Mode1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
  /*      //不是同一个仓库
        StoreHouse mStoreHouse1 = new StoreHouse();
        StoreHouse mStoreHouse2 = new StoreHouse();
        Carrier Carrier1 = new Carrier(mStoreHouse1);
        Carrier Carrier2 = new Carrier(mStoreHouse2);*/
        //是同一个仓库
        MyStoreHouse storeHouse = MyStoreHouse.getInstance();
        MyStoreHouse storeHouse2 = MyStoreHouse.getInstance();
        //两个工人
        MyCarrier myCarrier = new MyCarrier(storeHouse);
        MyCarrier myCarrier2 = new MyCarrier(storeHouse2);
        //工人1搬进去30个
        myCarrier.movein(30);
        System.out.println("仓库商品余量："+myCarrier.storeHouse.getQuantity());
        //工人2搬出来50个
        myCarrier2.moveout(50);
        System.out.println("仓库商品余量："+myCarrier2.storeHouse.getQuantity());
        getXings("李大急");
        Log.d("Mode1Activity","NAME:"+ getXings("李大急"));
    }
    private String getXings(String Gjmj){
        StringBuilder sb = new StringBuilder();
        if(!TextUtils.isEmpty(Gjmj)){
            String[] tmp = Gjmj.split(",");
            if(tmp != null && tmp.length > 0){

                for (int i = 0;i < tmp.length;i++){
                    String xingtmp;
                    if(isFuXing(getTwo(tmp[i]))){
                        String value = getTwo(tmp[i]);
                        xingtmp = value+"警官";
                    }else {
                        String FirstValue = getFirstValue(tmp[i]);
                        xingtmp = FirstValue+"警官";
                    }
                    sb.append(xingtmp);
                }
                return sb.toString();
            }
        }
        return "";
    }

    /**
     * 复姓氏
     * @param xing
     * @return
     */
    private boolean isFuXing(String xing){
        if(TextUtils.isEmpty(xing)){
            return false;
        }
        if(datas != null && datas.length > 0){
            for (int i = 0;i < datas.length;i++){
                if(xing.equals(datas[i])){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 单姓氏
     * @param name
     * @return
     */
    private String getFirstValue(String name){
        if(TextUtils.isEmpty(name)){
            return "";
        }
        String first =  name.substring(0,1);
        return first;
    }
    private String getTwo(String name){
        if(TextUtils.isEmpty(name)){
            return "";
        }
        String first =  name.substring(0,2);
        return first;
    }
    private String[] datas = new String[]{
            "欧阳","太史","端木","上官","司马","东方","独孤","南宫","万俟","闻人","夏侯","诸葛","尉迟",
            "公羊","赫连","澹台","皇甫","宗政","濮阳","公冶","太叔","申屠","公孙","慕容","仲孙","钟离",
            "长孙","宇文","司徒","鲜于","司空","闾丘","子车","亓官","司寇","巫马","公西","颛孙","壤驷",
            "公良","漆雕","乐正","宰 父","谷梁","拓跋","夹谷","轩辕","令狐","段干","百里","呼延","东郭",
            "南门","羊舌","微生","公户","公玉","公仪","梁丘","公仲","公上","公门","公山","公坚","左丘",
            "公伯","西门","公祖", "第五","公乘","贯丘","公皙","南荣","东里","东宫","仲长","子书","子桑","即墨","达奚","褚师"
    };
    /**
     * 仓库类
     */
     class StoreHouse{
        private int quantity = 100; //仓库里面有100个商品

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    /**
     * 搬运工人类
     */
    class Carrier{
        private StoreHouse storeHouse;
        public Carrier(StoreHouse storeHouse){
            this.storeHouse = storeHouse;
        }
        //搬货进仓库
        private void movein(StoreHouse storeHouse){
            storeHouse.setQuantity(storeHouse.getQuantity()+1);
        }
        //搬货出仓库
        private void moveout(StoreHouse storeHouse){
            storeHouse.setQuantity(storeHouse.getQuantity()-1);
        }
    }
}

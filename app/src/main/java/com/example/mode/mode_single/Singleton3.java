package com.example.mode.mode_single;

/**
 * 双重校验锁（懒汉式的改进）
 */
public class Singleton3 {
    // 1. 类加载时，先不自动创建单例
    private static Singleton3 singleton3 = null;
    //2. 构造函数 设置为 私有权限
    private Singleton3(){

    }

    /**
     * 校验锁1：第1个if
     * 作用：若单例已创建，则直接返回已创建的单例，无需再执行加锁操作
     * 即直接跳到执行 return ourInstance
     *
     * 校验锁2：第2个 if
     *  作用：防止多次创建单例问题
     * @return
     */
    public static  Singleton3 getInstance(){
        if(singleton3 == null){
            synchronized (Singleton3.class){
                // 先判断单例是否为空，以避免重复创建
                if(singleton3 == null){
                    singleton3 = new Singleton3();
                }
            }
        }
        return singleton3;
    }
}

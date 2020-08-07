package com.example.mode.mode_single;

/**
 * 懒汉式的改进:同步锁
 */
public class Singleton2 {
    // 1. 类加载时，先不自动创建单例
    private static Singleton2 singleton2 = null;
    //2. 构造函数 设置为 私有权限
    private Singleton2(){

    }
  /*
   写法1
   public static synchronized Singleton2 getInstance(){
        if(singleton2 == null){
            singleton2 = new Singleton2();
        }
        return singleton2;
    }*/

    /**
     * 写法2
     * @return
     */
    //3.加入同步锁
  public static  Singleton2 getInstance(){
      synchronized (Singleton2.class){
          // 先判断单例是否为空，以避免重复创建
          if(singleton2 == null){
              singleton2 = new Singleton2();
          }
      }
      return singleton2;
  }
}

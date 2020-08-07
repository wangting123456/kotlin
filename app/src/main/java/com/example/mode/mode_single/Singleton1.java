package com.example.mode.mode_single;

/**
 * 懒汉式（基础实现）
 * 线程不安全
 */
public class Singleton1 {
  private static Singleton1 singleton1 = null;
  private Singleton1(){

  }
  public static Singleton1 getInstance(){
      if(singleton1 == null){
          singleton1 = new Singleton1();
      }
      return singleton1;
  }
}

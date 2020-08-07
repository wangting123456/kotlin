package com.example.mode.mode_abstractfactory;

abstract public class Factory {
    //一个工厂生产手机
    abstract public AbstractProduct ManufactureContainer();
    //同时还可以生产笔记本
    abstract public AbstractProduct ManufactureModule();
}

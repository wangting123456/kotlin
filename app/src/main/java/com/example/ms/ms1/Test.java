package com.example.ms.ms1;

public class Test {
    private String name;
    private static Test test = new Test();
    private Test(){

    }

    public static Test getInstance(){
        return test;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

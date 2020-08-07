package com.example.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Student {
    @Id(autoincrement = true)
    private long id;
    private String name;
    private String desc;
    private String cover;
    private String ruxueTime;
    private String biyeTime;
    private String age;
    private String chengji;


    @Generated(hash = 1173209242)
    public Student(long id, String name, String desc, String cover,
            String ruxueTime, String biyeTime, String age, String chengji) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.cover = cover;
        this.ruxueTime = ruxueTime;
        this.biyeTime = biyeTime;
        this.age = age;
        this.chengji = chengji;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }


    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getCover() {
        return this.cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getRuxueTime() {
        return this.ruxueTime;
    }
    public void setRuxueTime(String ruxueTime) {
        this.ruxueTime = ruxueTime;
    }
    public String getBiyeTime() {
        return this.biyeTime;
    }
    public void setBiyeTime(String biyeTime) {
        this.biyeTime = biyeTime;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getChengji() {
        return this.chengji;
    }
    public void setChengji(String chengji) {
        this.chengji = chengji;
    }
   
}

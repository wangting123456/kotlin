package com.example.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Singer {
    @Id(autoincrement = true)
    private long id;
    private String name;
    private String desc;
    private String cover;
    @Generated(hash = 1372019942)
    public Singer(long id, String name, String desc, String cover) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.cover = cover;
    }
    @Generated(hash = 242898301)
    public Singer() {
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
}

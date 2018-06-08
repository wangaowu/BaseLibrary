package com.unistrong.demo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {

    public String name;
    public String age;
    @Generated(hash = 2102286658)
    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
}

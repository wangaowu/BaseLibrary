package com.unistrong.demo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Person {
    @Id
    public long id;

    public String name;
    public int sex;
    public String age;

    @Transient
    public String temp;

    @Generated(hash = 1948866780)
    public Person(long id, String name, int sex, String age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Generated(hash = 1024547259)
    public Person() {
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

    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

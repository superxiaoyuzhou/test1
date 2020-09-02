package com.piter.pojo;

import lombok.Data;

public class UserVo {
    private Integer id;

    private String userName;

    private int age;

    private String address;

    public UserVo(Integer id,String userName) {
        this.userName = userName;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}

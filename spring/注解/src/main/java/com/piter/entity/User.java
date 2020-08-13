package com.piter.entity;

public class User {

    private int age;
    private String name;
    private String emile;

    public User(int age, String name) {
        System.out.println("user 被创建!");
        this.age = age;
        this.name = name;
    }

    public void init() {
        emile = "1234562@qq.com";
    }

    public User() {
        System.out.println("user 被创建!");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

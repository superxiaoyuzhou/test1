package com.piter.proxy.myProxy;


public class Student implements Person {
    @Override
    public String say() {
        System.out.println("say");
        return "Hello";
    }

    public String say2() {
        System.out.println("say2");
        return "Hello2";
    }
}

package com.piter.proxy.controller;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 */
public class JDKProxyFactory implements InvocationHandler {

    private Object obj;

    public Object getInstance(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before ...");
        Object invoke = method.invoke(obj, args);
        System.out.println("after ...");
        return invoke;
    }

    public static void main(String[] args) {
        Person proxy = (Person)new JDKProxyFactory().getInstance(new Student());
        proxy.say();
    }
}

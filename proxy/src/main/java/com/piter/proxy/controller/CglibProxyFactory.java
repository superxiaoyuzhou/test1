package com.piter.proxy.controller;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyFactory implements MethodInterceptor {

    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }
    public Object getProxyInstance() {
        // 创建 Enhancer 对象
        Enhancer enhancer = new Enhancer();
        // 设置目标对象的Class
        enhancer.setSuperclass(target.getClass());
        // 设置回调操作，相当于InvocationHandler
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务...");
        // 两种方式执行方法，第二行注释掉的，和当前行代码效果相同，下面会分析；
        Object invoke = method.invoke(target, objects);
        // Object invoke = methodProxy.invokeSuper(proxy, args);
        System.out.println("提交/回滚事务...");
        return invoke;
    }
}

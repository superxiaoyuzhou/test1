package com.example.abc.controller;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Demo {

    public static void main(String[] args) throws Exception {
        //生成jdk代理类class文件在 \Workspaces\项目名称\com\sun\proxy 路径下
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true"); //jdk 动态代理
        Student student = new Student();
        Person studentProxy = (Person)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), student.getClass().getInterfaces(), (InvocationHandler) (proxy, method, args1) -> {
            System.out.println("before ...");
            Object invoke = method.invoke(student, args1);
            System.out.println("after ...");
            return invoke;
        });
        System.out.println(studentProxy.say());

        //生成cglib代理类class类的路径
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:/gradleproject/test/abc/target/classes/");
        //cglib 动态代理1
        /*Person studentProxy2 = (Person)org.springframework.cglib.proxy.Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), student.getClass().getInterfaces(), new org.springframework.cglib.proxy.InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("before ...");
                Object invoke = method.invoke(student, objects);
                System.out.println("after ...");
                return invoke;
            }
        });
        System.out.println(studentProxy2.say());*/

        //cglib 动态代理2
        Dog dog = new Dog();
//        // 创建 Enhancer(增强)对象
//        Enhancer enhancer = new Enhancer();
//        // 设置增强目标对象的Class
//        enhancer.setSuperclass(dog.getClass());
//        // 设置回调操作，相当于InvocationHandler
//        enhancer.setCallback(new MethodInterceptor() {
//            @Override
//            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
//                    throws Throwable {
//                System.out.println("开始事务...");
//                // Object invoke1 = method.invoke(target, args);
//                Object invoke2 = methodProxy.invokeSuper(proxy, args);
//                System.out.println("提交/回滚事务...");
//                return invoke2;
//            }
//        });
//        Dog dogProxy = (Dog)enhancer.create();
//        System.out.println(dogProxy.cat("骨头"));

        Dog dogProxy = (Dog)Enhancer.create(dog.getClass(), (MethodInterceptor) (proxy, method, objects, methodProxy) -> {
            System.out.println("开始事务...");
            //调用 被代理对象 目标方法
            Object invoke1 = method.invoke(dog, objects);
            //调用 代理对象 父类目标方法（因为：cglib无实现接口的代理是通过继承的方式实现）= 被代理对象 目标方法
            Object invoke2 = methodProxy.invokeSuper(proxy, objects);
            System.out.println("提交/回滚事务...");
            return invoke2;
        });
        System.out.println(dogProxy.cat("骨头"));


    }



}

package com.piter.proxy.demo;

/**
 * 静态代理，这个代理类也必须要实现和被代理类相同的Person接口
 * @author yujie.wang
 *
 */
public class ProxyTest implements Person{

    private Person s;

    public ProxyTest(Person s){
        this.s = s;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //s为被代理的对象，某些情况下 我们不希望修改已有的代码，我们采用代理来间接访问
        Student student = new Student();
        //创建代理类对象
        ProxyTest proxy = new ProxyTest(student);
        //调用代理类对象的方法
        proxy.sayHello("welcome to java", 20);
        System.out.println("---------------------------------");
        //调用代理类对象的方法
        proxy.sayGoodBye(true, 100);

    }

    @Override
    public void sayHello(String content, int age) {
        // TODO Auto-generated method stub
        System.out.println("ProxyTest sayHello begin");
        //在代理类的方法中 间接访问被代理对象的方法
        s.sayHello(content, age);
        System.out.println("ProxyTest sayHello end");
    }

    @Override
    public void sayGoodBye(boolean seeAgin, double time) {
        // TODO Auto-generated method stub
        System.out.println("ProxyTest sayHello begin");
        //在代理类的方法中 间接访问被代理对象的方法
        s.sayGoodBye(seeAgin, time);
        System.out.println("ProxyTest sayHello end");
    }

}

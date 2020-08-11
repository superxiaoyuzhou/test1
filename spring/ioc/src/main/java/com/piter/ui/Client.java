package com.piter.ui;

import com.piter.dao.IAccountDao;
import com.piter.service.AccountServiceFactory;
import com.piter.service.IAccountService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

    /**
     * 获取spring的Ioc核心容器，并根据id获取对象
     *
     * ApplicationContext的三个常用实现类：
     *      ClassPathXmlApplicationContext：它可以加载类路径下的配置文件，要求配置文件必须在类路径下。不在的话，加载不了。(更常用)
     *      FileSystemXmlApplicationContext：它可以加载磁盘任意路径下的配置文件(必须有访问权限）
     *
     *      AnnotationConfigApplicationContext：它是用于读取注解创建容器的。
     *
     * 核心容器的两个接口引发出的问题：
     *  ApplicationContext:     单例对象适用              采用此接口
     *      它在构建核心容器时，创建对象采取的策略是采用立即加载的方式。也就是说，只要一读取完配置文件马上就创建配置文件中配置的对象。
     *
     *  BeanFactory:            多例对象使用
     *      它在构建核心容器时，创建对象采取的策略是采用延迟加载的方式。也就是说，什么时候根据id获取对象了，什么时候才真正的创建对象。
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //1.获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
//        ApplicationContext ac = new FileSystemXmlApplicationContext("C:\\Users\\zhy\\Desktop\\bean.xml");
        //2.根据id获取Bean对象
        IAccountService as  = (IAccountService)ac.getBean("accountService");
        IAccountDao adao = ac.getBean("accountDao",IAccountDao.class);
        //3.静态工厂
        IAccountService as2 = ac.getBean("staticFactory", IAccountService.class);
        //4.spring工方法
//        它是实现了FactoryBean<T>接口的Bean，
//        根据该Bean的ID从BeanFactory中获取的实际上是FactoryBean的getObject()返回的对象，
//        而不是FactoryBean本身，如果要获取FactoryBean对象，请在id前面加一个&符号来获取。
        IAccountService as3 = ac.getBean("AccountService2", IAccountService.class);
        AccountServiceFactory asf = ac.getBean("&AccountService2", AccountServiceFactory.class);
        IAccountService object = (IAccountService)asf.getObject();
        System.out.println(as);
        System.out.println(adao);
        adao.saveAccount();


        //--------BeanFactory----------
        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        IAccountService as4  = (IAccountService)factory.getBean("accountService");
        System.out.println(as4);


    }
}

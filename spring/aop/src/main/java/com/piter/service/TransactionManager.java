package com.piter.service;

import org.aspectj.lang.ProceedingJoinPoint;

import java.sql.SQLException;

/**
 * 事务控制类
 */
public class TransactionManager {

    //定义一个 DBAssit
    private DBAssit dbAssit;

    public void setDbAssit(DBAssit dbAssit) {
        this.dbAssit = dbAssit;
    }

    //开启事务
    public void beginTransaction() {
        try {
//            dbAssit.getCurrentConnection().setAutoCommit(false);
            System.out.println("开启事务!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //提交事务
    public void commit() {
        try {
//            dbAssit.getCurrentConnection().commit();
            System.out.println("提交事务!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //回滚事务
    public void rollback() {
        try {
//            dbAssit.getCurrentConnection().rollback();
            System.out.println("回滚事务!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //释放资源
    public void release() {
        try {
//            dbAssit.releaseConnection();
            System.out.println("释放资源!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 环绕通知  * @param pjp
     * spring 框架为我们提供了一个接口：ProceedingJoinPoint，它可以作为环绕通知的方法参数。  *  在环绕通知执行时，spring 框架会为我们提供该接口的实现类对象，我们直接使用就行。  * @return
     */
    public Object transactionAround(ProceedingJoinPoint pjp) {
        //定义返回值
        Object rtValue = null;
        try {
            //获取方法执行所需的参数
            Object[] args = pjp.getArgs();
            //前置通知：开启事务
            beginTransaction();
            //执行方法
            rtValue = pjp.proceed(args);
            //后置通知：提交事务
            commit();
        } catch (Throwable e) {
            //异常通知：回滚事务
            rollback();
            e.printStackTrace();
        } finally {
            //最终通知：释放资源
            release();
        }
        return rtValue;
    }

}

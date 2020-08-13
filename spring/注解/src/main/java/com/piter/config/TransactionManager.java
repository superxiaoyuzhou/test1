package com.piter.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;

/**
 * 事务控制类
 */
@Component("txManager")
@Aspect
public class TransactionManager {

    @Pointcut("execution(* com.piter.service.impl.*.*(..))")
    public void pointcut(){ }

    //定义一个 DBAssit
    private DBAssit dbAssit;

    public void setDbAssit(DBAssit dbAssit) {
        this.dbAssit = dbAssit;
    }

    //开启事务
    @Before("pointcut()")
    public void beginTransaction(JoinPoint joinPoint) {
        try {
//            dbAssit.getCurrentConnection().setAutoCommit(false);
            System.out.println("开启事务!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void beginTransaction() {
        try {
//            dbAssit.getCurrentConnection().setAutoCommit(false);
            System.out.println("开启事务!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //提交事务
    @AfterReturning(pointcut = "pointcut()",returning = "result")
    public void commit(JoinPoint joinPoint, Object result) {
        try {
//            dbAssit.getCurrentConnection().commit();
            System.out.println("调用" + joinPoint.getTarget() + "的" + joinPoint.getSignature().getName() + "方法,返回值" + result);
            System.out.println("提交事务!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
//            dbAssit.getCurrentConnection().commit();
            System.out.println("提交事务!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //回滚事务
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void rollback(JoinPoint joinPoint,Exception e) {
        try {
//            dbAssit.getCurrentConnection().rollback();
            System.out.println("调用" + joinPoint.getTarget() + "的" + joinPoint.getSignature().getName() + "方法发生" + e);
            System.out.println("回滚事务!");
        } catch (Exception ei) {
            ei.printStackTrace();
        }
    }
    public void rollback() {
        try {
//            dbAssit.getCurrentConnection().rollback();
            System.out.println("回滚事务!");
        } catch (Exception ei) {
            ei.printStackTrace();
        }
    }

    //释放资源
    @After("pointcut()")
    public void release() {
        try {
//            dbAssit.releaseConnection();
            System.out.println("释放资源!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 环绕通知
     * @param pjp
     * spring 框架为我们提供了一个接口：ProceedingJoinPoint，它可以作为环绕通知的方法参数。
     *  在环绕通知执行时，spring 框架会为我们提供该接口的实现类对象，我们直接使用就行。
     * @return
     */
//    @Around("pointcut()")
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

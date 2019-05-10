package com.piter.proxy.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
/**
 *代理类也实现了Person接口，看起来和静态代理的方式也会一样的
 *同时代理类也继承了Proxy类
 */
public final class $Proxy0 extends Proxy implements Person {
    private static Method m4;
    private static Method m1;
    private static Method m0;
    private static Method m3;
    private static Method m2;

    public $Proxy0(InvocationHandler paramInvocationHandler) throws Exception{
        super(paramInvocationHandler);
    }

    //实现了Person接口的方法，这就是我们调用这个方法Proxy.newProxyInstance必须提供第二个参数的作用
    @Override
    public final void sayGoodBye(boolean paramBoolean, double paramDouble) throws Error{
        try {
            // 我们看到通过调用代理类的方法时，最终方法都会委托给InvocationHandler实现类的invoke方法
            // m4为代理类通过反射获得的Method
            this.h.invoke(this, m4, new Object[]{Boolean.valueOf(paramBoolean), Double.valueOf(paramDouble)});
            return;
        } catch (Error | RuntimeException localError) {
            throw localError;
        } catch (Throwable localThrowable) {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    @Override
    public final boolean equals(Object paramObject) throws Error{
        try {
            return ((Boolean) this.h.invoke(this, m1, new Object[]{paramObject})).booleanValue();
        } catch (Error | RuntimeException localError) {
            throw localError;
        } catch (Throwable localThrowable) {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    @Override
    public final int hashCode() throws Error{
        try {
            return ((Integer) this.h.invoke(this, m0, null)).intValue();
        } catch (Error | RuntimeException localError) {
            throw localError;
        } catch (Throwable localThrowable) {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    //实现了Person接口的方法，这就是我们调用这个方法Proxy.newProxyInstance必须提供第二个参数的作用
    @Override
    public final void sayHello(String paramString, int paramInt) throws Error{
        try {
            // 我们看到通过调用代理类的方法时，最终方法都会委托给InvocationHandler实现类的invoke方法
            // m4为代理类通过反射获得的Method
            this.h.invoke(this, m3, new Object[]{paramString, Integer.valueOf(paramInt)});
            return;
        } catch (Error | RuntimeException localError) {
            throw localError;
        } catch (Throwable localThrowable) {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    @Override
    public final String toString()
            throws Error{
        try {
            return (String) this.h.invoke(this, m2, null);
        } catch (Error | RuntimeException localError) {
            throw localError;
        } catch (Throwable localThrowable) {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    static {
        try {//代理类通过反射 获得的接口方法Method
            m4 = Class.forName("com.yujie.proxy.dynamic.Person").getMethod("sayGoodBye", new Class[]{Boolean.TYPE, Double.TYPE});
            m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[]{Class.forName("java.lang.Object")});
            m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
            //代理类通过反射 获得的接口方法Method
            m3 = Class.forName("com.yujie.proxy.dynamic.Person").getMethod("sayHello", new Class[]{Class.forName("java.lang.String"), Integer.TYPE});
            m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
//            return;
        } catch (NoSuchMethodException localNoSuchMethodException) {
            throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
        } catch (ClassNotFoundException localClassNotFoundException) {
            throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
        }
    }
}
package 基础知识;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 在JDK8下测试方法区，所以设置了Metaspace(元空间:方法区)的大小为固定的10M
 * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 * 在JDK8的环境下将报出异常:
 * Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
 */
public class JavaMethodAreaOOM {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, args);
                }
            });
            //无限创建动态代理，生成Class对象
            enhancer.create();
        }
    }

    static class OOMObject {
    }
}

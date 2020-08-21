package com.piter.proxy.手写动态代理;

import com.piter.proxy.controller.Person;
import com.piter.proxy.controller.Student;
import sun.misc.ProxyGenerator;
import sun.rmi.rmic.iiop.ClassPathLoader;
import sun.tools.java.ClassPath;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Objects;

/**
 * 简单模拟jdk动态代理
 *
 * @author Piter
 */
public class MyProxy {

    public static void main(String[] args) throws Exception {

        Student student = new Student();
        Person studentProxy = (Person) MyProxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), student.getClass().getInterfaces(), (InvocationHandler) (proxy, method, args1) -> {
            System.out.println("before ...");
            Object invoke = method.invoke(student, args1);
            System.out.println("after ...");
            return invoke;
        });
        System.out.println(studentProxy.say());
    }

    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws Exception {
        Objects.requireNonNull(h);

        final Class<?>[] intfs = interfaces.clone();
        //生成代理类
        Class<?> cl = getProxyClass0(loader, intfs);
        //通过反射创建代理对象
        final Constructor<?> cons = cl.getConstructor(InvocationHandler.class);
        return cons.newInstance(new Object[]{h});

//        return null;
    }

    private static Class<?> getProxyClass0(ClassLoader loader, Class<?>[] intfs) throws FileNotFoundException {
        Class<?> intf = intfs[0];
        String path = loader.getResource("").getPath();
        File file = new File(path + "$MyProxy0.java");
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("package com.piter.proxy.手写动态代理;");
            writer.println();
            writer.println("import java.lang.reflect.InvocationHandler;");
            writer.println("import java.lang.reflect.Method;");
            writer.println();
            writer.println("public final class $MyProxy0 implements " + intf.getSimpleName() + " {\n");
            writer.println("private InvocationHandler h;");
            Method[] methods = intf.getMethods();
            for (int i = 0; i < methods.length; i++) {
                writer.println("private static Method m" + i + ";");
            }
            writer.println("public $MyProxy0(InvocationHandler paramInvocationHandler) throws Exception{\n" +
                    "        this.h = paramInvocationHandler;\n" +
                    "    }");
            writer.println("static {");
            writer.println("    try {");
            for (int i = 0; i < methods.length; i++) {
                writer.println("    m" + i + " = Class.forName(\"" + intf.getName() + "\").getMethod(\"" + methods[i].getName() + "\", new Class[0]);");
            }
            writer.println("    }catch (Exception e) {}");
            writer.println("}");
            for (int i = 0; i < methods.length; i++) {
                writer.println("    @Override\n" +
                        "    public final " + methods[i].getReturnType().getSimpleName() + " " + methods[i].getName() + "() throws Error{\n" +
                        "        try {\n" +
                        "            return ((" + methods[i].getReturnType().getSimpleName() + ") this.h.invoke(this, m" + i + ", null));\n" +
                        "        } catch (Exception e) {} catch (Throwable throwable) {\n" +
                        "            throwable.printStackTrace();\n" +
                        "        }" +
                        "        return null;" +
                        "    }");
            }
            writer.println("}");

            writer.println("");
            writer.flush();
        }
        //编译代理类
        compile(path + "$MyProxy0.java");
        //加载代理类class文件到jvm
        return classLoader(loader,path + "$MyProxy0.class");
    }
    private static Class<?> getProxyClass1(ClassLoader loader, Class<?>[] intfs) throws FileNotFoundException {
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                "$MyProxy0", intfs, 17);

        return defineClass0(loader, "$MyProxy0",
                proxyClassFile, 0, proxyClassFile.length);
    }

    private static Class<?> classLoader(ClassLoader loader, String filePath) {
        try {
            ClassPathLoader classPathLoader = new ClassPathLoader(new ClassPath(filePath));
            return classPathLoader.loadClass("$MyProxy0");
        } catch (Exception e) {

        }
        return null;
    }

    private static void compile(String filePath) {
        try {
            //动态编译
            JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            int status = javac.run(null, null, null, filePath);
            if(status!=0){
                System.out.println("没有编译成功！");
            }

        } catch (Exception e) {
        } catch (Throwable throwable) {
        }
    }

    private static native Class<?> defineClass0(ClassLoader loader, String name,
                                                byte[] b, int off, int len);
}

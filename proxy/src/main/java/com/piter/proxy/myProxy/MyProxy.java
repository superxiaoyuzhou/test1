package com.piter.proxy.myProxy;

//import sun.misc.ProxyGenerator;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 简单模拟jdk动态代理
 *
 * @author Piter
 */
public class MyProxy {

    public static void main(String[] args) throws Exception {

        Student student = new Student();
        Person studentProxy = (Person) MyProxy.newProxyInstance(new MyClassLoader(), student.getClass().getInterfaces(), (InvocationHandler) (proxy, method, args1) -> {
            System.out.println("before ...");
            Object invoke = method.invoke(student, args1);
            System.out.println("after ...");
            return invoke;
        });
        System.out.println(studentProxy.say());
    }

    public static Object newProxyInstance(MyClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws Exception {
        Objects.requireNonNull(h);

        final Class<?>[] intfs = interfaces.clone();
        //生成代理类
        Class<?> cl = getProxyClass0(loader, intfs);
        //通过反射创建代理对象
        final Constructor<?> cons = cl.getConstructor(InvocationHandler.class);
        return cons.newInstance(h);

//        return null;
    }

    private static Class<?> getProxyClass0(MyClassLoader loader, Class<?>[] intfs) throws FileNotFoundException {
        Class<?> intf = intfs[0];
        String path = MyProxy.class.getResource("").getPath();
        File file = new File(path + "$MyProxy0.java");
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("package com.piter.proxy.myProxy;");
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
                        "        } catch (Exception e) { } catch (Throwable throwable) {\n" +
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
        compile(file);
        //加载代理类class文件到jvm
        return loader.findClass("$MyProxy0");
    }
//    private static Class<?> getProxyClass1(ClassLoader loader, Class<?>[] intfs) throws FileNotFoundException {
//        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
//                "$MyProxy0", intfs, 17);
//
//        return defineClass0(loader, "$MyProxy0",
//                proxyClassFile, 0, proxyClassFile.length);
//    }

    private static Class<?> classLoader(ClassLoader loader, String name) {
        try {
            return null;
        } catch (Exception e) {

        }
        return null;
    }

    private static void compile(File file) {
        try {
            //动态编译
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> iterable = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();
        } catch (Exception e) {
        } catch (Throwable throwable) {
        }
    }

    private static native Class<?> defineClass0(ClassLoader loader, String name,
                                                byte[] b, int off, int len);
}

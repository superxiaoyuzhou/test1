package com.piter.proxy.myProxy;

import java.io.*;

public class MyClassLoader extends ClassLoader {

    private File baseDir;

    public MyClassLoader(){
        String path = MyClassLoader.class.getResource("").getPath();
        this.baseDir = new File(path);
    }

    @Override
    protected Class<?> findClass(String name) {
        String className = MyClassLoader.class.getPackage().getName() + "." + name;
        File classFile = new File(baseDir, name.replace("\\.","/") + ".class");
        if (classFile.exists()) {
            try (FileInputStream in = new FileInputStream(classFile);
                 ByteArrayOutputStream out = new ByteArrayOutputStream();
            ) {
                byte[] buff = new byte[1024];
                int len;
                while ((len = in.read(buff)) > 0) {
                    out.write(buff, 0, len);
                }
                return defineClass(className, out.toByteArray(), 0, out.size());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(classFile.getPath() + "class文件不存在！");
        }
        return null;
    }
}

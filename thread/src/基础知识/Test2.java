package 基础知识;

import java.util.concurrent.TimeUnit;

/**
 * 线程可见性问题演示
 * 线程无法终止
 */
public class Test2 {

    private static boolean stop = false;
    //volatile 关键字修饰的变量能保证可见性，线程可以终止
//    private volatile static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
                //加上输出语句会进行内存缓存信息交换，缓存更新线程可以终止
//                System.out.println(i);
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        stop = true;
        System.out.println("stop");
    }
}

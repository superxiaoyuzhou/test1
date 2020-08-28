package 多线程.lock;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.concurrent.TimeUnit;

public class LockDemo1 {

    //总共票数
    private static int i = 100;

    public static void main(String[] args) throws InterruptedException {

        for (int j = 0; j < 10; j++) {
            Thread thread = new Thread(() -> {
                for (int k = 0; k < 100; k++) {
                    if (i >= 1) {
                        System.out.println("获取到第" + (100 - i + 1) + "号票");
                        i--;
                    }
                }
            });
            thread.start();
        }

        Thread.sleep(3000);
        System.out.println("剩余" + i + "张票");
    }

}

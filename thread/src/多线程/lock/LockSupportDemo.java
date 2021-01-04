package 多线程.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport中的方法：
 * park()   阻塞一个线程
 * unpark(Thread thread)  唤醒一个线程
 */
public class LockSupportDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {

        });
        LockSupport.park();
        LockSupport.unpark(thread);

    }
}

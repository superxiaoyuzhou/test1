package 多线程.lock;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockDemo1 {

    //总共票数
    private static int i = 100;

    //可重入锁
    private static Lock lock = new ReentrantLock();
    //公平重入锁,默认为非公平
//    private static Lock lock = new ReentrantLock(true);

    //读写锁:用于读多写少的情况
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //读锁:共享锁
    private static Lock readLock = readWriteLock.readLock();
    //写锁:独占锁
    private static Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) throws InterruptedException {

        for (int j = 0; j < 10; j++) {
            Thread thread = new Thread(() -> {

                for (int k = 0; k < 100; k++) {
                    if (i >= 1) {
                        System.out.println("获取到第" + (100 - i + 1) + "号票");
                        lock.lock();
                        try {
                            i--;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                            lock.unlock();
                        }
                    }
                }
            });
            thread.start();
        }

        Thread.sleep(3000);
        System.out.println("剩余" + i + "张票");
    }

}

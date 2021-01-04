package 多线程.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * Lock锁的方法：
 * lock.lock() 加锁
 * lock.unlock() 释放锁
 * lock.tryLock() 尝试获取锁
 *
 * Condition实现等待通知的相关方法：
 * Condition condition = lock.newCondition();
 * condition.await() 与 Object.wait() 作用相同
 * condition.signal() 与 Object.notify() 作用相同
 * condition.signalAll() 与 Object.notifyAll() 作用相同
 */
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
                        Condition condition = lock.newCondition();
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

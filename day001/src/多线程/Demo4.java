package 多线程;

import java.util.concurrent.Semaphore;

/**
 * Semaphore与CountDownLatch相似，不同的地方在于Semaphore的值被获取到后是可以释放的，并不像CountDownLatch那样一直减到底。
 * 它也被更多地用来限制流量，类似阀门的 功能。
 * 如果限定某些资源最多有N个线程可以访问，那么超过N个主不允许再有线程来访问，同时当现有线程结束后，就会释放，然后允许新的线程进来。
 * 有点类似于锁的lock与 unlock过程。
 * 相对来说他也有两个主要的方法：
 *  用于获取权限的acquire(),其底层实现与CountDownLatch.countdown()类似;
 *  用于释放权限的release()，其底层实现与acquire()是一个互逆的过程。
 *
 *
 */
public class Demo4 {

    private static int num = 10;

    //设置只允许三个线程同时获得锁
    private static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        for (int i = 0; i < num; i++) {
            new NewThread().start();
        }
    }

    static class NewThread extends Thread{
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "多线程---------------开始获取锁----");
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "多线程----------运行中-------------");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "多线程---------------结束释放锁-------");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}



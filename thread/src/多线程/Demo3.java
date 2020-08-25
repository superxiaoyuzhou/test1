package 多线程;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier也是一个同步辅助类，它允许一组线程相互等待，直到到达某个公共屏障点（common barrier point）。
 * 通过它可以完成多个线程之间相互等待，只有当每个线程都准备就绪后，才能各自继续往下执行后面的操作。
 * 类似于CountDownLatch，它也是通过计数器来实现的。当某个线程调用await方法时，
 * 该线程进入等待状态，且计数器加1，当计数器的值达到设置的初始值时，
 * 所有因调用await进入等待状态的线程被唤醒，继续执行后续操作。因为CycliBarrier在释放等待线程后可以重用，
 * 所以称为循环barrier。
 * CycliBarrier支持一个可选的Runnable，在计数器的值到达设定值后（但在释放所有线程之前），
 * 该Runnable运行一次，注，Runnable在每个屏障点只运行一个。
 *
 */
public class Demo3 {

    private static int num = 3;
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(num,  new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("线程都运行到指定位置!开始运行!");
        }
    }));

    public static void main(String[] args) {
        for (int i = 0; i < num; i++) {
            new NewThread().start();
        }
    }

    static class NewThread extends Thread{
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "线程运行---------------开始");
                System.out.println(Thread.currentThread().getName() + "线程运行----------到-------这里,开始等待其它线程");
                Thread.sleep(1000);
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "线程运行---------------结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}



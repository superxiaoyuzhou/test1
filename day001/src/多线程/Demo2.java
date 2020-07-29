package 多线程;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

/**
 * CountDownLatch是一个计数器闭锁，通过它可以完成类似于阻塞当前线程的功能，
 * 即：一个线程或多个线程一直等待，直到其他线程执行的操作完成。
 * CountDownLatch用一个给定的计数器来初始化，该计数器的操作是原子操作，即同时只能有一个线程去操作该计数器。
 * 调用该类await方法的线程会一直处于阻塞状态，直到其他线程调用countDown方法使当前计数器的值变为零，
 * 每次调用countDown计数器的值减1。
 * 当计数器值减至零时，所有因调用await()方法而处于等待状态的线程就会继续往下执行。
 * 这种现象只会出现一次，因为计数器不能被重置，如果业务上需要一个可以重置计数次数的版本，可以考虑使用CycliBarrier。
 *
 */
public class Demo2 {

    private static int num = 5;
    private static CountDownLatch countDownLatch =  new CountDownLatch(num);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < num; i++) {
            FutureTask<String> futureTask = new FutureTask<>(new NewThread());
            new Thread(futureTask).start();
        }
        System.out.println("主线程在这里等待其它线程运行完成");
        countDownLatch.await();
        System.out.println("主线程运行完成");
    }

    static class NewThread implements Callable<String> {

        @Override
        public String call() throws Exception {
            try {
                System.out.println(Thread.currentThread().getName() + "多线程---------开始运行-------");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "多线程---------运行结束-------");
                return Thread.currentThread().getName();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
            return null;
        }
    }

}


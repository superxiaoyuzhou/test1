package 多线程;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 当 线程睡眠 被中断的时候，那方法会立刻抛出InterruptedException异常并不会一直等到睡眠时间过去
 * 注意:
 * 1.线程抛出InterruptedException异常后被trycatch后 isInterrupted会重置为false, 所有catch后需要重新中断设置为true, 否则会中断失败
 * 2.如果线程被设置为守护线程,被守护线程结束后,守护线程的finally不保证一定会执行
 * 3.Thread.interrupted() 方法会设置成Interrupted为false。
 */
public class Demo1 {


    public static void main(String[] args) throws InterruptedException {

        Thread1 thread1 = new Thread1();
        thread1.start();
        //等待5秒，然后中断 thread1
        TimeUnit.SECONDS.sleep(5);
        thread1.interrupt();

        Thread thread2 = new Thread(new RunnableThread());
        //设置为守护线程
        thread2.setDaemon(true);
        thread2.start();
        Thread.sleep(5000);
        System.out.println("end");

        CallableThread callableThread = new CallableThread();
        FutureTask<String> task = new FutureTask<>(callableThread);
        Thread thread3 = new Thread(task);
        thread3.start();
        Thread.sleep(5000);
        thread3.interrupt();


    }


}
class Thread1 extends Thread {
    private int i = 0;

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                i++;
                System.out.println("thread1:" + i);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("thread1 Interrupted:" + isInterrupted());
                this.interrupt();
            }
        }
        System.out.println("thread1 Interrupted:" + isInterrupted());
    }
}

class RunnableThread implements Runnable{
    private int i = 0;

    @Override
    public void run() {
        boolean interrupted = Thread.currentThread().isInterrupted();

        while (!interrupted) {
            i++;
            try {
                System.out.println("RunnableThread:" + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("finally");
            }
        }
        System.out.println("RunnableThread Interrupted:" + interrupted);
    }
}

class CallableThread implements Callable<String> {
    private int i = 0;

    @Override
    public String call() throws Exception {
        Thread thread = Thread.currentThread();
        while (!thread.isInterrupted()) {
            try {
                i++;
                System.out.println("CallableThread:" + i);
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("CallableThread Interrupted:" + thread.isInterrupted());
                thread.interrupt();
            }

        }
        System.out.println("CallableThread Interrupted:" + thread.isInterrupted());

        return "Callbale return";
    }
}

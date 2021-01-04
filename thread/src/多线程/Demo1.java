package 多线程;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 终止线程的相关方法：
 * 1.interrupt() 中断一个线程
 * 2.isInterrupted() 判断当前线程释放处于中断状态
 * 3.静态的interrupted() 判断当前线程释放处于中断状态
 *
 * 当 线程睡眠 被中断的时候，那方法会立刻抛出InterruptedException异常并不会一直等到睡眠时间过去
 * 注意:
 * 1.线程抛出InterruptedException异常后被trycatch后 isInterrupted会重置为false, 所以catch后需要重新中断设置为true, 否则会中断失败
 * 2.如果线程被设置为守护线程,被守护线程结束后,守护线程的finally不保证一定会执行
 * 3.Thread.interrupted() 方法会设置成Interrupted为false。
 *
 * 其它终止线程的相关方法（不推荐使用）：
 * 1.stop() 强制终止线程，该方法不保证线程资源一定被释放
 * 2.suspend() 挂起线程，线程不会释放锁，线程不会释放资源
 * 3.resume() 继续执行suspend()方法挂起的线程
 *
 * 其它线程相关方法：
 * wait() 等待，释放锁
 * notify()/notifyAll 通知，唤醒在调用对象上锁等待的线程
 * yield()  让出cpu , 持有锁不释放
 * sleep()  睡眠，持有锁不释放
 * join() 线程A 执行了线程B 的 join() 方法，线程A必须要等待线程B执行完成了以后，线程A才能继续执行
 */
public class Demo1 {


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Thread1 thread1 = new Thread1();
        //设置线程优先级为1，默认为5
        thread1.setPriority(1);
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
//        task.isDone();  //结束，正常还是异常结束，或自己取消，返回true
//        task.isCancelled(); //任务完成前取消，返回true
        /**
         * cancel方法
         * 任务还没开始或已经结束，返回false;
         * cancel(true)：任务已经启动,中断正在运行的任务，中断成功返回true;
         * cancel(false)：不会去中断已经运行的任务
         */
//        task.cancel(true);
        System.out.println(task.get()); //get()方法调用时，如果线程未执行完成，会等待到执行完成返回结果


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

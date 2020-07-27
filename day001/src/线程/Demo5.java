package 线程;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo5 {
    //线程安全的map
    static ConcurrentHashMap<String, Thread> map = new ConcurrentHashMap<>();
    static List<FutureTask<Integer>> list = new ArrayList<>();
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        for (int i = 0; i < 5; i++) {
            FutureTask<Integer> futureTask = new FutureTask<>(new NewThread());
            list.add(futureTask);
            new Thread(futureTask).start();

        }
        countDownLatch.await();
        map.forEachValue(5L,thread -> {
            System.out.println(thread.getName());
        });
        AtomicInteger sum = new AtomicInteger();
        list.forEach(list->{
            try {
                sum.addAndGet(list.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println(sum);

    }
    static class NewThread implements Callable<Integer> {

        @Override
        public Integer call() {
            Thread thread = Thread.currentThread();
            map.put(thread.getName(), thread);
            int a = (int)(Math.random()*100);
            threadLocal.set(a);
            try {
                System.out.println(thread.getName() + "--------------开始-------------");
                Thread.sleep(1000);
                do {
                    threadLocal.set(threadLocal.get() + 1);
                    System.out.println(thread.getName() + ":" + threadLocal.get());
                } while (threadLocal.get() < 100);
                return threadLocal.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
            return 0;
        }
    }


}


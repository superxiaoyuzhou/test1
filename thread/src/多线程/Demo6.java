package 多线程;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo6 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("a","aaa");
        ConcurrentSkipListMap<Object, Object> map2 = new ConcurrentSkipListMap<>();
        map2.get("");
        map2.put("","");
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
        list.add("1");
        list.remove("");
        list.get(0);
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 20, 1000L, TimeUnit.MILLISECONDS, arrayBlockingQueue, new MyThreadFactory());
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            });
        }

        System.out.println(threadPoolExecutor.getActiveCount());
        Thread.sleep(3000L);
        System.out.println(threadPoolExecutor.getActiveCount());
        threadPoolExecutor.shutdown();

        System.out.println("-------------------- 有返回值统计的 ------------------");

        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(3, 20, 1000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(3), new MyThreadFactory());
        ExecutorCompletionService<Integer> executorCompletionService = new ExecutorCompletionService(threadPoolExecutor2);
        for (int i = 0; i < 10; i++) {
            executorCompletionService.submit(() -> {
                int random = (int) (Math.random() * 1000);
                System.out.println(Thread.currentThread().getName() + ":" + random);
                Thread.sleep(random);
                return random;
            });
        }
        AtomicInteger sum = new AtomicInteger();
        for (int i = 0; i < 10; i++) {
            Integer integer = executorCompletionService.take().get();
            sum.addAndGet(integer);
            System.out.println(integer);
        }
        System.out.println(sum);
        threadPoolExecutor2.shutdown();

    }

    static class MyThreadFactory implements ThreadFactory {

        private static int num = 1;
        private static synchronized int nextNum(){
            return num++;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("MyThread" + nextNum());
            return thread;
        }
    }
}

package 多线程.简单并发任务框架;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        TaskPool taskPool = TaskPool.getTaskPool();
        JobInfo<Integer, Integer> jobInfo = taskPool.executor("test", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,12,13,14), integer -> {
            int random = (int) (Math.random() * 10000);
//            System.out.println(Thread.currentThread().getName() + ":" + random);
            try {
                Thread.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return RestResp.restRespSuccess(random);
        });
        taskPool.executor("test2", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,12,13,14), integer -> {
            int random = (int) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ":" + random);
            try {
                Thread.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return RestResp.restRespSuccess(random);
        });
        testGet();
    }

    private static void testGet() {
        TaskPool taskPool = TaskPool.getTaskPool();
        JobInfo<Integer, Integer> jobInfo = taskPool.getJobInfo("test2");
        do{
            try {
                System.out.println("test2:" + jobInfo.getExecutePercentage());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (jobInfo.getTaskProcesserCount() != jobInfo.getCount());
        jobInfo.getConcurrentHashMap().forEachKey(100,integer -> {
            System.out.println(jobInfo.getConcurrentHashMap().get(integer).getData());
        });

    }
}

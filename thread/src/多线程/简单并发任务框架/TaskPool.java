package 多线程.简单并发任务框架;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class TaskPool {

    //工作列表
    private static ConcurrentHashMap<String, JobInfo> jobs = new ConcurrentHashMap<>(100);
    //CPU核心数
    //计算密集型：线程数量=cpu核心数量
    //IO密集型：线程数量=cpu核心数量*2
    private final static int threadCount = Runtime.getRuntime().availableProcessors();
    //阻塞等待队列
    private static ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5000);
    //任务线程池
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(threadCount, threadCount, 1000L, TimeUnit.MILLISECONDS, workQueue);


    private TaskPool() {
    }

    static class TaskPool2 {
        public static TaskPool taskPool = new TaskPool();
    }

    public static TaskPool getTaskPool() {
        return TaskPool2.taskPool;
    }

    public <T, R> JobInfo<T, R> executor(String jobName, List<T> list, Function<T, RestResp<R>> function) {
        JobInfo<T, R> jobInfo = new JobInfo<>(jobName, list, function);
        jobs.put(jobName, jobInfo);
        run(jobInfo);
        return jobInfo;
    }

    private <T, R> void run(JobInfo<T, R> jobInfo) {
        jobInfo.getList().forEach(parameter -> {
            threadPoolExecutor.execute(() -> {
                RestResp<R> restResp = null;
                try {
                    restResp = jobInfo.getFunction().apply(parameter);
                } catch (Exception e) {
                    restResp = RestResp.restRespError(e.getMessage());
                } finally {
                    jobInfo.saveResult(parameter, restResp);
                }
            });
        });
    }

    public <T, R> JobInfo<T,R> getJobInfo(String jobName) {
        return jobs.get(jobName);
    }
}

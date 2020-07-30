package 多线程.简单并发任务框架;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * 作业信息
 *
 * @param <T> 参数对象
 * @param <R> 结果对象
 */
public class JobInfo<T,R> {
    //作业名称
    private final String jobName;
    //执行任务总数
    private final int count;
    //任务执行成功总数
    private final AtomicInteger successCount;
    //已经执行任务总数
    private final AtomicInteger taskProcesserCount;
    //完成结果集合
    private final ConcurrentHashMap<T,RestResp<R>> concurrentHashMap;
    //请求参数集合
    private final List<T> list;
    //执行函数
    private final Function<T, RestResp<R>> function;

    public JobInfo(String jobName, List<T> list, Function<T, RestResp<R>> function) {
        this.jobName = jobName;
        this.list = list;
        this.function = function;
        this.count = list.size();
        this.successCount = new AtomicInteger(0);
        this.taskProcesserCount = new AtomicInteger(0);
        this.concurrentHashMap = new ConcurrentHashMap<>();
    }

    public void saveResult(T t,RestResp<R> resp) {
        if (resp == null) {
            resp = new RestResp<>();
        }
        concurrentHashMap.put(t, resp);
        if (ResultType.SUCCESS == resp.getResultType()) {
            successCount.incrementAndGet();
        }
        taskProcesserCount.incrementAndGet();
    }

    public String getJobName() {
        return jobName;
    }

    public int getCount() {
        return count;
    }

    public int getSuccessCount() {
        return successCount.get();
    }

    public int getTaskProcesserCount() {
        return taskProcesserCount.get();
    }

    public ConcurrentHashMap<T, RestResp<R>> getConcurrentHashMap() {
        return concurrentHashMap;
    }

    public List<T> getList() {
        return list;
    }

    public Function<T, RestResp<R>> getFunction() {
        return function;
    }

    public String getExecutePercentage() {
        return (getTaskProcesserCount()*100/getCount()) + "%";
    }
}

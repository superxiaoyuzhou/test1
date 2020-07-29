package 多线程.简单并发任务框架;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * 任务信息
 *
 * @param <T> 参数对象
 * @param <R> 结果对象
 */
public class JobInfo<T,R> {
    //任务名称
    private String jobName;
    //执行总数
    private int count;
    //完成总数
    private AtomicInteger successCount = new AtomicInteger(0);
    //完成结果队列
    private ArrayBlockingQueue arrayBlockingQueue;
    //请求参数集合
    private List<T> list;
    //执行函数
    private Function<T, R> function;


}

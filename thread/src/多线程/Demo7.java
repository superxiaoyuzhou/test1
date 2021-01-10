package 多线程;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * Exchange 用于两个线程间的数据交换
 */
public class Demo7 {

    private static Exchanger<List<String>> exchanger = new Exchanger();

    public static void main(String[] args) {

        //第一个线程
        new Thread(() -> {
            List<String> listA = new LinkedList<>();
            listA.add("A");
            listA.add("AA");
            listA.add("AAA");
            try {
                //交换数据
                listA = exchanger.exchange(listA);
                //处理交换后的数据
                System.out.println(Thread.currentThread().getName() + ":" + listA);
            } catch (InterruptedException e) {
            }
        },"ThreadA").start();

        //第二个线程
        new Thread(() -> {
            List<String> listB = new LinkedList<>();
            listB.add("B");
            listB.add("BB");
            listB.add("BBB");
            try {
                //交换数据
                listB = exchanger.exchange(listB);
                //处理交换后的数据
                System.out.println(Thread.currentThread().getName() + ":" + listB);
            } catch (InterruptedException e) {
            }
        }).start();
    }
}





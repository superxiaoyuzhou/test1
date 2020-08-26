package 基础知识;

/**
 * 指令重排序测试
 * 重排序:指在编译器以及CPU优化情况下(编译器&CPU优化会破坏程序执行顺序)，提高性能
 * 并保证程序以单线程方式执行时，其结果的不变性。
 */

public class 重排序问题演示 {

    private static int a = 0, b = 0, c = 0, d = 0;
    private static int e = 0, f = 0, g = 0, h = 0;
//    private volatile static int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0;

//    private static int b = 0;
//    private static int c = 0;
//    private static int d = 0;
//    private static int e = 0;
//    private static int f = 0;
//    private static int g = 0;
//    private static int h = 0;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100000; i++) {
            //join可以保证线程a b都执行完成之后，再继续下一次循环
            ThreadA threadA = new ThreadA();
            threadA.start();

            ThreadB threadB = new ThreadB();
            threadB.start();

            threadA.join();
            threadB.join();
            System.out.println("(" + c + "," + d + ")");
            //清空数据，便于测试
            a = b = c = d = 0;
        }
    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            a = 1;
            c = a;
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            b = 1;
            d = b;
        }
    }

}
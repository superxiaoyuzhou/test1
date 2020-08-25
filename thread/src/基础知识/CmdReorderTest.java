package 基础知识;

/**
 * 指令重排序测试
 */

public class CmdReorderTest {

    private volatile static int a = 0, b = 0, c = 0, d = 0;
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

            //清空数据，便于测试
            a = b = c = d = e = f = g = h = 0;
        }
    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            a = 1;
            b = 1;
            c = 1;
            d = 1;
            e = 1;
            f = 1;
            g = 1;
            h = 1;
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            if (b == 1 && a == 0) {
                System.out.println("发生了重排序");
                System.out.println("b=1");
            }
            if (c == 1 && (a == 0 || b == 0)) {
                System.out.println("发生了重排序");
                System.out.println("c=1");
            }
            if (d == 1 && (a == 0 || b == 0 || c == 0)) {
                System.out.println("发生了重排序");
                System.out.println("d=1");
            }
            if (e == 1 && (a == 0 || b == 0 || c == 0 || d == 0)) {
                System.out.println("发生了重排序");
                System.out.println("e=1");
            }
            if (f == 1 && (a == 0 || b == 0 || c == 0 || d == 0 || e == 0)) {
                System.out.println("发生了重排序");
                System.out.println("f=1");
            }
            if (g == 1 && (a == 0 || b == 0 || c == 0 || d == 0 || e == 0 || f == 0)) {
                System.out.println("发生了重排序");
                System.out.println("g=1");
            }
            if (h == 1 && (a == 0 || b == 0 || c == 0 || d == 0 || e == 0 || f == 0 || g == 0)) {
                System.out.println("发生了重排序");
                System.out.println("h=1");
            }
        }
    }

}
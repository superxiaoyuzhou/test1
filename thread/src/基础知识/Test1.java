package 基础知识;

/**
 *  指令重排序测试 CmdReorderTest
 *  volatile修饰的变量能保证可见性,禁止指令重排序
 *  可见性是指一个线程修改了一个变量的值后，其他线程立即可以感知到这个值的修改。
 *  volatile修饰的变量
 *      可见性:在修改后会立即同步给主内存，cup在读取的时候会废弃缓存中的值,从主内存重新读取,
 *  保证读取时是最新的但,cpu读取后进行计算时,其它线程进行修改,cpu无法感知,所以单独volatile关键字无法保证线程安全，
 *
 *  如:i++ 不是一个原子操作
 *  1.cpu读取i的值
 *  2.cpu对i的值 + 1
 *  3.cpu赋新值给i,写回缓存主存
 *
 *  可以通过:CAS比较并交换来保证原子性,不同操作系统CAS的实现方式有差异(不管)
 *  CAS有ABA问题,可以通过加版本号解决
 *  ABA问题:
 *      a = 1;a = 2; a = 1;
 *  a的最终值没变,但过程中发生了改变
 * 除了volatile，synchronized和final也可以实现可见性。synchronized关键字是通过unlock之前必须把变量同步回主内存来实现的，
 * final则是在初始化后就不会更改，所以只要在初始化过程中没有把this指针传递出去也能保证对其他线程的可见性。
 */
public class Test1 {

    private static int a = 0, b = 0, x = 0, y = 0;
    /**
     * volatile关键字保证了内存的可见性,和通过内存屏障,禁止指令重排序(禁止volatile关键字变量操作之后的指令在volatile关键字变量之前执行)
     */
//    private volatile static int a = 0, b = 0, x = 0, y = 0;

    /**
     *
     * 这段代码的期望结果为(1,1)。
     * 然而，这段代码的执行结果也可能是(0,0),(1,0),(0,1)
     * 因为，在实际运行时，代码指令可能并不是严格按照代码语句顺序执行的。
     * 值得注意的是，a=1和x=a这两个语句的赋值操作的顺序被颠倒了，或者说，发生了指令“重排序”(reordering)。
     * （事实上，输出了这一结果，并不代表一定发生了指令重排序，内存可见性问题也会导致这样的输出)
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            a = 1;
            x = a;
        });
        Thread thread2 = new Thread(() -> {
            b = 1;
            y = b;
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("(" + x + "," + y + ")");

    }
}

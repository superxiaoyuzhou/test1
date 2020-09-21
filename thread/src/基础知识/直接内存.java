package 基础知识;

import java.nio.ByteBuffer;

/**
 * -XX:MaxDirectMemorySize 参数来设置最大可用直接内存，如果启动时未设置则默认为最大堆内存大小，即与 -Xmx 相同
 *
 * -Xmx10M
 */
public class 直接内存 {

    public static void main(String[] args) {

        //nio直接内存分配大小
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024*1024*12);    //12M
        //12>10 抛出 java.lang.OutOfMemoryError: Direct buffer memory 异常
    }
}

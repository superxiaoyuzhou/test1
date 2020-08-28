package 基础知识;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存溢出
 * -Xms20m -Xmx20m -Xmn20m
 */
public class OOM {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        while (true) {
            list.add("巨大对象");
            System.out.println(Runtime.getRuntime().maxMemory()/1024/1024 + "M:" + Runtime.getRuntime().totalMemory()/1024/1024 + "M:" + Runtime.getRuntime().freeMemory()/1024/1024 + "M");
        }
    }
}

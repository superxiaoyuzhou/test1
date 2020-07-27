package 线程;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Demo6 {

    public static void main(String[] args) {

        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("a","aaa");
        ConcurrentSkipListMap<Object, Object> map2 = new ConcurrentSkipListMap<>();
        map2.get("");
        map2.put("","");
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
        list.add("");
        list.remove("");
        list.get(0);
    }
}

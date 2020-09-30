package com.example.test.test;

import lombok.extern.slf4j.Slf4j;

/**
 * javap -v Test.class
 *
 * 反编译Java代码，查看Java字节码
 */
@Slf4j
public class Test {

    private String name1;

    private int int1;

    public static void main(String[] args) {
        int a = 100;
        int b = 200;
        int c = a * b + 300;
    }

    public Integer add(Integer x) {
        int c = x + int1;
        return c;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public int getInt1() {
        return int1;
    }

    public void setInt1(int int1) {
        this.int1 = int1;
    }
}

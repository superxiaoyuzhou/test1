package com.example.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Optional;

@Slf4j
public class Hello {
    class User{
        private String name;
        private int age;

        public User(String name) {
            this.name = name;
        }

        public User(String name, int age) {
            System.out.println("执行了!!!!");
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    @Test
    public void test1(){
        User user = null;
        user = new User("老王");
        user = Optional.ofNullable(user).orElseGet(() -> new User("张三", 23));
        log.info(user.toString());
        user = Optional.ofNullable(user).orElse(new User("张三", 23));
        log.info(user.toString());

        //结论,当Optional的值是空值时，无论orElse还是orElseGet都会执行；
        // 而当返回的Optional有值时，orElse会执行，而orElseGet不会执行。

    }


}

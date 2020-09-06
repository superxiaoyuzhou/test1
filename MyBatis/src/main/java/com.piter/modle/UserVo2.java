package com.piter.modle;

import java.util.List;

public class UserVo2 {
    private Integer id;

    private String userName;

    private int age;

    private String address;

    private UserVo test;

    private List<UserVo> testList;

    public UserVo2(Integer id, String userName) {
        this.userName = userName;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserVo getTest() {
        return test;
    }

    public void setTest(UserVo test) {
        this.test = test;
    }

    public List<UserVo> getTestList() {
        return testList;
    }

    public void setTestList(List<UserVo> testList) {
        this.testList = testList;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}

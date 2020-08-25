package com.github.appundefined.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeEntityDemo {
@TreeElement(name = "id")
private String id;
@TreeElement(name = "pid")
private String pid;
private Long test1;
private Integer test2;
private int test3;
private String name;
@TreeElement(name = "children")
private List<Object> treeEntityDemos = new ArrayList<>();
    public List<Object> getTreeEntityDemos() {
        return treeEntityDemos;
    }

    public void setTreeEntityDemos(List<Object> treeEntityDemos) {
        this.treeEntityDemos = treeEntityDemos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTest1() {
        return test1;
    }

    public void setTest1(Long test1) {
        this.test1 = test1;
    }

    public Integer getTest2() {
        return test2;
    }

    public void setTest2(Integer test2) {
        this.test2 = test2;
    }

    public int getTest3() {
        return test3;
    }

    public void setTest3(int test3) {
        this.test3 = test3;
    }
}

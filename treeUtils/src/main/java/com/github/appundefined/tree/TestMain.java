package com.github.appundefined.tree;

import java.util.ArrayList;
import java.util.List;

public class TestMain {
    public static void main(String[] args) throws IllegalAccessException {
        ArrayList<TreeEntityDemo> treeEntityDemos = new ArrayList<>();
        TreeEntityDemo treeEntityDemo = new TreeEntityDemo();
        treeEntityDemo.setId(1+"");
        treeEntityDemo.setTest1(0L);
        treeEntityDemo.setTest2(2);
        treeEntityDemo.setTest3(2);
        treeEntityDemos.add(treeEntityDemo);
        TreeEntityDemo treeEntityDemo2 = new TreeEntityDemo();
        treeEntityDemo2.setId(2+"");
        treeEntityDemo2.setPid(1+"");
        treeEntityDemos.add(treeEntityDemo2);
        TreeEntityDemo treeEntityDemo3 = new TreeEntityDemo();
        treeEntityDemo3.setId(3+"");
        treeEntityDemo3.setPid(1+"");
        treeEntityDemos.add(treeEntityDemo3);
        TreeEntityDemo treeEntityDemo4 = new TreeEntityDemo();
        treeEntityDemo4.setId(4+"");
        treeEntityDemo4.setPid(3+"");
        treeEntityDemos.add(treeEntityDemo4);
        List<TreeEntityDemo> treeEntityDemos1 = TreeUtils.ListToTree(treeEntityDemos);
        System.out.println(treeEntityDemos1);
        Object id = TreeUtils.getType(treeEntityDemo, "test3");
        System.out.println("1111"+id.toString()+"22222");
        Object id2 = TreeUtils.getType(treeEntityDemo, "test1");
        System.out.println("1111"+id2.toString()+"22222");
        Object id3 = TreeUtils.getType(treeEntityDemo, "test2");
        System.out.println("1111"+id3.toString()+"22222");
    }
}

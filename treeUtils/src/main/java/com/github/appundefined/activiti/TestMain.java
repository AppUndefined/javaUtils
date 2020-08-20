package com.github.appundefined.activiti;

import java.util.ArrayList;
import java.util.List;

public class TestMain {
    public static void main(String[] args) throws IllegalAccessException {
        ArrayList<TreeEntityDemo> treeEntityDemos = new ArrayList<>();
        TreeEntityDemo treeEntityDemo = new TreeEntityDemo();
        treeEntityDemo.setId(1+"");
        treeEntityDemo.setPid(0+"");
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
    }
}

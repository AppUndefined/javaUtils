package 修饰符测试2;

import 修饰符测试.ProtectedTest;

public class Demo {
    public static void main(String[] args) {
        ProtectedTest protectedTest = new ProtectedTest();
        //protected 修饰符修饰的变量不能再同一包外进行访问
//        protectedTest.name = "abc";
//        System.out.printf(protectedTest.name);
        ProtectedChildrenTest protectedChildrenTest = new ProtectedChildrenTest();
        //protected 修饰符修饰的变量可以再子类中访问但子类对象需要在父类同一包下才可访问
//        protectedChildrenTest.name = "bcd";
//        System.out.printf(protectedChildrenTest.name);
    }
}

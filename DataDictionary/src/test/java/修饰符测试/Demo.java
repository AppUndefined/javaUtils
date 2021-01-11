package 修饰符测试;

import 修饰符测试2.DefaultChildrenTest;
import 修饰符测试2.ProtectedChildrenTest;

public class Demo {
    public static void main(String[] args) {
//        protectedTest();
    }
    private static void defaultTest() {
        DefaultTest defaultTest = new DefaultTest();
        defaultTest.name = "abc";
        System.out.printf(defaultTest.name);
        DefaultChildrenTest defaultChildrenTest = new DefaultChildrenTest();
        //default修饰的变量在子类中无法直接访问
//        defaultChildrenTest.name = "bcd";
//        System.out.printf(defaultChildrenTest.name);
    }
    private static void protectedTest() {
        ProtectedTest protectedTest = new ProtectedTest();
        ProtectedTest protectedTest2 = new ProtectedTest("abc");
        protectedTest.name = "abc";
        System.out.printf(protectedTest.name);
        ProtectedChildrenTest protectedChildrenTest = new ProtectedChildrenTest();
        protectedChildrenTest.name = "bcd";
        System.out.printf(protectedChildrenTest.name);
    }
}

import com.github.appundefined.annotation.AnnotationUtils;

import java.util.Date;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) throws Exception {
//        testLRU();
        testAnnotation();
    }

    private static void testAnnotation() throws Exception {
        Object annotationValue = AnnotationUtils.getAnnotationValue(TreeEntityDemo.class, TreeElement.class, "pid", "name");
        HashMap  result = AnnotationUtils.getAnnotationValue(TreeEntityDemo.class, TreeElement.class);
        result.forEach((x,y)->{
            System.out.println(x+"::"+y);
        });

    }

    private static void testLRU() throws IllegalAccessException {
        Date start = new Date();
        TestUser testUser = new TestUser();
        testUser.setUsername("aaa");
        Object username = AnnotationUtils.getValue(testUser, "username");

        TestUser2 testUser2 = new TestUser2();
        testUser2.setUsername("aaa");
        Object username2 = AnnotationUtils.getValue(testUser2, "username");


        TestUser3 testUser3 = new TestUser3();
        testUser3.setUsername("aaa");
        Object username3 = AnnotationUtils.getValue(testUser3, "username");


        TestUser4 testUser4 = new TestUser4();
        testUser4.setUsername("aaa");
        Object username4 = AnnotationUtils.getValue(testUser4, "username");


        Date end = new Date();
        System.out.println("耗时"+((end.getTime()-start.getTime())));
    }
}

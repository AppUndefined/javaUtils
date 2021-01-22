package springAspect;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext  ctx =
                new AnnotationConfigApplicationContext(Config.class);
        Student student = (Student) ctx.getBean("student");
        student.getName();
        student.getAge();
        student.printThrowException();
    }
}

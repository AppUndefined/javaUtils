package springCustomEvent;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext  ctx =
                new AnnotationConfigApplicationContext(Config.class);
    }
}

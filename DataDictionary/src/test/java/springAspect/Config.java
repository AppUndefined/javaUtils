package springAspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy//启用AspectJ自动代理
public class Config {
    @Bean
    public Logging logging(){return new Logging();}
    @Bean
    public Student student(){
        Student student = new Student();
        student.setAge(11);
        student.setName("李芸");
        return student;}
}

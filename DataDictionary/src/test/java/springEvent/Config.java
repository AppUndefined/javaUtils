package springEvent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public CStartEventHandler cStartEventHandler(){
        return new CStartEventHandler();
    }
}

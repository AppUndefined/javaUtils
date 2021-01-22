package springCustomEvent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public CustomListener customListener(){return new CustomListener();}
    @Bean
    public CustomPublisher customPublisher(){return new CustomPublisher();}
}

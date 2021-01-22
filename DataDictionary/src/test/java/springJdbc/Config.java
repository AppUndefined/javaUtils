package springJdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class Config {
    @Bean
    public DriverManagerDataSource driverManagerDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://43.254.45.15:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("RuQu123+");
        return dataSource;
    }
    @Bean
    public StudentJDBCTemplate studentJDBCTemplate(){
        StudentJDBCTemplate studentJDBCTemplate = new StudentJDBCTemplate();
        studentJDBCTemplate.setDataSource(driverManagerDataSource());
        return studentJDBCTemplate;
    }
}

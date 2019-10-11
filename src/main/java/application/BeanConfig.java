package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "classpath:application.properties")
@ComponentScan(basePackages = {"application.stats", "v1"})
public class BeanConfig {

    @Autowired
    Environment env;

    @Bean
    public ApplicationContextProvider applicationContextProvider(){
        return new ApplicationContextProvider();
    }
}

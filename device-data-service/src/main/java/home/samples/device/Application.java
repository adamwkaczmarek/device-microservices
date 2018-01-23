package home.samples.device;

import home.samples.context.UserContextFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableEurekaClient
@Configuration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }


    @Bean
    public UserContextFilter userContextFilter(){
        return new UserContextFilter();
    }

}

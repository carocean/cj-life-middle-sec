package cj.life.middle.sec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"cj.life.ability", "cj.life.middle.sec"})
@EnableEurekaClient
@EnableFeignClients(basePackages = "cj.life.middle.sec.remote")
public class CjLifeMiddleSecStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CjLifeMiddleSecStarterApplication.class, args);
    }

}

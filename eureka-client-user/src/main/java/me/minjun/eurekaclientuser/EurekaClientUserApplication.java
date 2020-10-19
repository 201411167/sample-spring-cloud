package me.minjun.eurekaclientuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClientUserApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(EurekaClientUserApplication.class);
        app.addListeners((ApplicationStartedEvent event) -> {
            System.out.println("===== Eureka Client User Started =====");
        });
        app.run(args);
    }

}

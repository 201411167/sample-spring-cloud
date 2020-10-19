package me.minjun.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ZuulApplication.class);
        app.addListeners((ApplicationStartedEvent event) -> {
            System.out.println("===== Zuul Started =====");
        });
        app.run(args);
    }

}

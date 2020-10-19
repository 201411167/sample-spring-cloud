package me.minjun.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Mono;

/**
 *  스프링 부트에서는 @EnableWebFlux 어노테이션을 사용하지 않는 것이 기본이다.
 *  어노테이션을 사용하면 부트의 webflux 설정을 사용하지 않고, 웹플럭스에 대한 완전한 컨트롤을 가져오겠다는 의미
 *
 *  출처) https://dreamchaser3.tistory.com/15
 */

@EnableEurekaServer
@SpringBootApplication
@RestController
public class EurekaServerApplication {


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(EurekaServerApplication.class);
        app.addListeners((ApplicationStartedEvent event) -> System.out.println("===== Eureka Server Started ====="));
        app.run(args);
    }

}

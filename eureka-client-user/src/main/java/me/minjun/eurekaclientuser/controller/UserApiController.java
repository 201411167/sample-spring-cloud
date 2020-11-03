package me.minjun.eurekaclientuser.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.minjun.eurekaclientuser.domain.user.User;
import me.minjun.eurekaclientuser.domain.user.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/hello")
    public Mono<String> helloWorld(){
        WebClient webClient = WebClient.builder().build();
        Mono<String> stringMono = webClient.get()
                .uri("http://localhost:9999/api/oauth/hello")
                .retrieve()
                .bodyToMono(String.class);

        log.info("request data from EUREKA-CLIENT-OAUTH");

        return stringMono;
    }

    @GetMapping("/all")
    public Flux<User> findAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/{email}")
    public Mono<User> findUser(@PathVariable String email){
        return userRepository.findById(email);
    }

    @PostMapping("/save")
    public Mono<User> saveUser(@RequestBody Map<String, Object> req){
        String email = (String) req.get("email");
        String name = (String) req.get("name");
        return userRepository.save(User.builder().email(email).name(name).build());
    }

    @GetMapping("/oauth2")
    public Mono<User> saveOAuth2User(){
        WebClient webClient = WebClient.builder().build();

        Mono<Map> mapMono = webClient.get()
                .uri("http://localhost:9999/api/oauth/user")
                .retrieve()
                .bodyToMono(Map.class);

        return mapMono.flatMap(o->{
            String email = (String) o.get("email");
            String name = (String) o.get("name");

            return userRepository.save(User.builder().email(email).name(name).build());
        });
    }

    @PutMapping("/update")
    public Mono<User> updateUserName(@RequestBody Map<String, Object> req){
        String email = (String) req.get("email");
        String new_name = (String) req.get("name");
        return userRepository.findById(email).flatMap(user -> {
            user.setName(new_name);
            return userRepository.save(user);
        });
    }

    @DeleteMapping("/delete")
    public Mono<Void> deleteUser(@RequestBody Map<String, Object> req){
        String email = (String) req.get("email");
        return userRepository.deleteById(email);
    }
}

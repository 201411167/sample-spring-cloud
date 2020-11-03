package me.minjun.oauth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.minjun.oauth.service.OAuthService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OAuthController {

    private final OAuthService oAuthService;

    @GetMapping("/hello")
    public Mono<String> hello(){
        return Mono.just("hello world");
    }

    @GetMapping("/user")
    public Mono<Map<String, Object>> getOAuthUserInfo() {
        String email = oAuthService.getEmail();
        String name = oAuthService.getName();
        String picture = oAuthService.getPicture();

        Map<String, Object> oauthUser = new HashMap<>();
        oauthUser.put("email", email);
        oauthUser.put("name", name);
        oauthUser.put("picture", picture);

        log.info("oauth user info : " + oauthUser.toString());

        return Mono.just(oauthUser);
    }


}

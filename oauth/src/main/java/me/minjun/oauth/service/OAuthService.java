package me.minjun.oauth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultReactiveOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.ReactiveOAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Slf4j
public class OAuthService implements ReactiveOAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private String email;
    private String name;
    private String picture;

    @Override
    public Mono<OAuth2User> loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        final DefaultReactiveOAuth2UserService delegate = new DefaultReactiveOAuth2UserService();
        final String clientRegistration = userRequest.getClientRegistration().getRegistrationId();

        Mono<OAuth2User> oAuth2UserMono = delegate.loadUser(userRequest);

        oAuth2UserMono.subscribe(oAuth2User -> {
//            CurrentUser currentUser = CurrentUser.getInstance();
            Map<String, Object> attributes = oAuth2User.getAttributes();
            String name = (String) attributes.get("name");
            String email = (String) attributes.get("email");
            String picture = (String) attributes.get("picture");

            log.info("OAuth2 User : " + attributes.toString());

            this.email = email;
            this.name = name;
            this.picture = picture;

        });

        return oAuth2UserMono;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }
}

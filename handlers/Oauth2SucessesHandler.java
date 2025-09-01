package com.practices.demo.handlers;

import com.practices.demo.entities.UserEntity;
import com.practices.demo.service.JwtService;
import com.practices.demo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Slf4j
@Component
@RequiredArgsConstructor
public class Oauth2SucessesHandler extends SimpleUrlAuthenticationSuccessHandler {
private final UserService userService;
private final JwtService jwtService;

    @Override
public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    OAuth2AuthenticationToken token=(OAuth2AuthenticationToken) authentication;
  DefaultOAuth2User oAuth2User =(DefaultOAuth2User) token.getPrincipal();
    log.info(oAuth2User.getAttribute("email"));
   String email=oAuth2User.getAttribute("email");
        UserEntity user=userService.getUserByEmail(email);
        if (user==null){
            UserEntity  userEntity=user.builder()
                    .name(oAuth2User.getAttribute("name"))
                    .email(email)
                    .build();
             user =userService.save(userEntity);

        }
String acessToken=jwtService.generateSecritKey(user);
        String refreshToken= jwtService.getRefreshToken(user);
        Cookie cookie=new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
        String frontEndUrl="http://localhost:8989/home.html?token="+acessToken;
//one way
        //        getRedirectStrategy().sendRedirect(request,response,frontEndUrl);
//another way is as
        response.sendRedirect(frontEndUrl);
    }
}

package com.practices.demo.configs;

import com.practices.demo.entities.UserEntity;
import com.practices.demo.filter.AuthJwtFilter;
import com.practices.demo.handlers.Oauth2SucessesHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

///posts/auth/**
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class webSecurityConfig {
    private final AuthJwtFilter authJwtFilter;
      private final Oauth2SucessesHandler oauth2SucessesHandler;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.csrf(csrf -> csrf.disable()) // disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**","/home.html").permitAll()  // allow signup/login
                        .anyRequest().authenticated()
                        // everything else needs auth
                ).addFilterBefore(authJwtFilter, UsernamePasswordAuthenticationFilter.class)

                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .oauth2Login(oauth2Config->oauth2Config.failureUrl("/login?error=true")
                        .successHandler(oauth2SucessesHandler)
                );

        return httpSecurity.build();
//        httpSecurity.authorizeHttpRequests(auth->auth.requestMatchers("/auth").permitAll()
////                        .requestMatchers("/auth/**")
////                        .hasRole("").
//                        . anyRequest().
//                        authenticated())
//                .formLogin(Customizer.withDefaults());
//        return httpSecurity.build();
    }
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configs)throws Exception{
        return configs.getAuthenticationManager();
    }
//    @Bean
//    UserDetailsService inMemoryUserDetailService(){
//        UserDetails normalUser= User.withUsername("suhail")
//                .password(pe().encode("suhail")).roles("USER").build();
//        UserDetails Admin=User.withUsername("admin").password(pe().encode("admin")).roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(normalUser,Admin);
//    }

}

package com.practices.demo.configs;

import com.practices.demo.entities.UserEntity;
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
///posts/auth/**
@Configuration
@EnableWebSecurity
public class webSecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.csrf(csrf -> csrf.disable()) // disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()  // allow signup/login
                        .anyRequest().authenticated()             // everything else needs auth
                )
                .formLogin(form -> form.disable())   // ❌ disable Spring’s login page
                .httpBasic(basic -> basic.disable()); // ❌ disable HTTP Basic too

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
    @Bean
    PasswordEncoder pe(){
        return new BCryptPasswordEncoder();
    }
}

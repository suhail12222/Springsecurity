package com.practices.demo.configs;

import com.practices.demo.auditorAware.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing (auditorAwareRef="getAuditorAwareImpl")
public class AppConfig {
    @Bean
    ModelMapper getModelMapper(){
        return new ModelMapper();
    }
    @Bean
    AuditorAware<String> getAuditorAwareImpl(){
        return new AuditorAwareImpl();
    }

}

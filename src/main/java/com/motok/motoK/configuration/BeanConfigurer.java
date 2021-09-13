package com.motok.motoK.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanConfigurer {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}

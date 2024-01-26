package com.akash.cellular_automata;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {

    @Bean
    public CAHelper caHelper(){
        return new CAHelper();
    }
}

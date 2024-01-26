package com.akash.cluster;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {

    @Bean
    public ClusterHelperMethods helperMethods(){
        return new ClusterHelperMethods();
    }
}

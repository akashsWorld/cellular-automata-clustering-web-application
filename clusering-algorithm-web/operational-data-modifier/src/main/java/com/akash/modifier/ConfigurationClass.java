package com.akash.modifier;


import com.akash.modifier.utility.ModifyDataAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {
    @Bean
    public ModifyDataAttributes deleteArrayIndexes(){
        return new ModifyDataAttributes();
    }
}

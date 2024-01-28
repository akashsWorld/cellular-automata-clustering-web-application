package com.akash.parser;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DataParserMainClass {
    public static void main(String[] args) {
        SpringApplication.run(DataParserMainClass.class,args);
    }
}
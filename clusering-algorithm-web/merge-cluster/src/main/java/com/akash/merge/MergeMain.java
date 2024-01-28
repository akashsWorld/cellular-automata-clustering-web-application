package com.akash.merge;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.akash.client")
@EnableDiscoveryClient
public class MergeMain {
    public static void main(String[] args) {
        SpringApplication.run(MergeMain.class,args);
    }
}
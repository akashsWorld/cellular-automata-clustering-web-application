package com.akash.cluster;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.akash.client"})
public class ClusterMain {
    public static void main(String[] args) {
        SpringApplication.run(ClusterMain.class,args);
    }
}

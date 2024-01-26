package com.akash.merge;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.akash.client")
public class MergeMain {
    public static void main(String[] args) {
        SpringApplication.run(MergeMain.class,args);
    }
}
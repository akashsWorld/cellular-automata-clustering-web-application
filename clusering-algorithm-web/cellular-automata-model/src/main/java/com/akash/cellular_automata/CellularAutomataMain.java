package com.akash.cellular_automata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CellularAutomataMain {
    public static void main(String[] args) {
        SpringApplication.run(CellularAutomataMain.class,args);
    }
}
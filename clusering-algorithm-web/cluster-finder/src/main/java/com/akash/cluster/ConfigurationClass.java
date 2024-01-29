package com.akash.cluster;

import com.akash.client.cellular_automata.CAClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigurationClass {

    @Bean
    public ClusterHelperMethods helperMethods(){
        return new ClusterHelperMethods();
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders httpHeaders(){
        HttpHeaders httpHeadersObj = new HttpHeaders();
        httpHeadersObj.setContentType(MediaType.APPLICATION_JSON);
        return httpHeadersObj;
    }

    @Bean
    public MergeArrayHttpRequest mergeArrayHttpRequest(HttpHeaders httpHeaders,
                                                       RestTemplate restTemplate,
                                                       CAClient caClient,
                                                       Environment environment){

        return new MergeArrayHttpRequest(httpHeaders,restTemplate,caClient,environment);

    }

}

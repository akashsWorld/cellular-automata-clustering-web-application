package com.akash.client.data_parser;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@FeignClient(name ="data-server" ,url = "localhost:8501")
public interface DataParserClient {

    @RequestMapping(value = "parser/parseData/uniqueConfiguration",method = RequestMethod.GET)
    public ResponseEntity<ArrayList<String>> getUniqueConfiguration(ArrayList<ArrayList<String>> operationalData);

    @RequestMapping(value = "parser/parseData/objectWiseData",method = RequestMethod.GET)
    public ResponseEntity<ArrayList<String>> getObjectWiseData(ArrayList<ArrayList<String>> operationalData);
}

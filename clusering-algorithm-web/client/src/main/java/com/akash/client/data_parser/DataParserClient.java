package com.akash.client.data_parser;

import com.akash.client.GlobalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@FeignClient(name ="data-parser" )
public interface DataParserClient {

    @RequestMapping (value = "parser/parseData/uniqueConfiguration",method = {RequestMethod.POST}, consumes = "application/json",produces = "application/json")
    ResponseEntity<ArrayList<String>> getUniqueConfiguration(ArrayList<ArrayList<String>> operationalData);

    @RequestMapping(value="parser/parseData/objectWiseData",method = {RequestMethod.POST}, consumes = "application/json",produces = "application/json")
    ResponseEntity<ArrayList<String>> getObjectWiseData(ArrayList<ArrayList<String>> operationalData);
}

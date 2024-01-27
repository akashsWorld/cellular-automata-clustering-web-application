package com.akash.parser;


import com.akash.client.GlobalResponse;
import com.akash.parser.responses.ParseDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("parser/parseData")
@RequiredArgsConstructor
public class DataParserController {
    private final DataParserService dataParserService;

    @PostMapping("parse")
    public ResponseEntity<ParseDataResponse> parseData(@RequestBody ArrayList<ArrayList<String>> rawData,
                                                                       @RequestParam("divisions") Integer divisions){
        ParseDataResponse parseDataResponse = dataParserService.convertNumericalDataToCategoricalData(rawData,divisions);

        return ResponseEntity.status(HttpStatus.OK).body(parseDataResponse);
    }

    @RequestMapping (value = "uniqueConfiguration",method = {RequestMethod.POST}, consumes = "application/json",produces = "application/json")
    public ResponseEntity<ArrayList<String>> getUniqueConfiguration(@RequestBody ArrayList<ArrayList<String>> operationalData){

        ArrayList<String> uniqueConfiguration = dataParserService.getUniqueConfiguration(operationalData);

        return ResponseEntity.status(HttpStatus.OK).body(uniqueConfiguration);
    }

    @RequestMapping(value="objectWiseData",method = {RequestMethod.POST}, consumes = "application/json",produces = "application/json")
    public ResponseEntity<ArrayList<String>> getObjectWiseData(@RequestBody ArrayList<ArrayList<String>> operationalData){

        ArrayList<String> objectWiseData = dataParserService.getObjectWiseData(operationalData);

        return ResponseEntity.status(HttpStatus.OK).body(objectWiseData);
    }
}

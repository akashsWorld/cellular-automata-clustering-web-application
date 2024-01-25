package com.akash.parser;


import com.akash.parser.responses.ParseDataResponse;
import lombok.RequiredArgsConstructor;
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
                                                       @RequestParam Integer divisions){
        return ResponseEntity.ok(dataParserService.convertNumericalDataToCategoricalData(rawData,divisions));
    }

    @RequestMapping (value = "uniqueConfiguration",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<ArrayList<String>> getUniqueConfiguration(@RequestBody ArrayList<ArrayList<String>> operationalData){
        return ResponseEntity.ok(dataParserService.getUniqueConfiguration(operationalData));
    }

    @RequestMapping(value="objectWiseData",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<ArrayList<String>> getObjectWiseData(@RequestBody ArrayList<ArrayList<String>> operationalData){
        return ResponseEntity.ok(dataParserService.getObjectWiseData(operationalData));
    }
}

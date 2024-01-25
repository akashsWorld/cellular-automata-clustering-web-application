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

    @GetMapping("uniqueConfiguration")
    public ResponseEntity<ArrayList<String>> getUniqueConfiguration(ArrayList<ArrayList<String>> operationalData){
        return ResponseEntity.ok(dataParserService.getUniqueConfiguration(operationalData));
    }

    @GetMapping("objectWiseData")
    public ResponseEntity<ArrayList<String>> getObjectWiseData(ArrayList<ArrayList<String>> operationalData){
        return ResponseEntity.ok(dataParserService.getObjectWiseData(operationalData));
    }
}

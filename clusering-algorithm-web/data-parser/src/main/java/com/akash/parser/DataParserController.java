package com.akash.parser;


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
    public ResponseEntity<ArrayList<ArrayList<String>>> parseData(@RequestBody ArrayList<ArrayList<String>> rawData,
                                                                  @RequestParam Integer divisions){
        return ResponseEntity.ok(dataParserService.convertNumericalDataToCategoricalData(rawData,divisions));
    }
}

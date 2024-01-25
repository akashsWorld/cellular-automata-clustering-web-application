package com.akash.parser.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class ParseDataResponse {
    private ArrayList<ArrayList<String>> parsedData;
    private Integer numberOfDivisions;
}

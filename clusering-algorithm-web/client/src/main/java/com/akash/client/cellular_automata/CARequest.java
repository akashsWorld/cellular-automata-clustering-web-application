package com.akash.client.cellular_automata;


import com.akash.caclustering.utility.Boundary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class CARequest {
    private String boundaryName;
    private ArrayList<ArrayList<String>> operationalData;
}

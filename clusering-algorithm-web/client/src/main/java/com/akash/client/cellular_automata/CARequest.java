package com.akash.client.cellular_automata;


import com.akash.caclustering.utility.Boundary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@Setter
public class CARequest {
    private Boundary boundary;
    private ArrayList<ArrayList<String>> operationalData;
}

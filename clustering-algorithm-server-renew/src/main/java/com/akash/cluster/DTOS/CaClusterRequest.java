package com.akash.cluster.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaClusterRequest {
    private ArrayList<ArrayList<String>> operationalData;
    private ArrayList<ArrayList<Integer>> primaryCluster;
}

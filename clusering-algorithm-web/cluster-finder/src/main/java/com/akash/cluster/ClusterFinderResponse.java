package com.akash.cluster;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClusterFinderResponse {
    private Integer numberOfClusters;
    private  ArrayList<ArrayList<Integer>> clusters;
}

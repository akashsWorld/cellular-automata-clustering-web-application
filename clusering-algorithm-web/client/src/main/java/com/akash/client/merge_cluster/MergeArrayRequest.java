package com.akash.client.merge_cluster;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MergeArrayRequest {
    private ArrayList<ArrayList<Integer>> primaryCluster;
    private ArrayList<ArrayList<Integer>> auxiliaryCluster;
}

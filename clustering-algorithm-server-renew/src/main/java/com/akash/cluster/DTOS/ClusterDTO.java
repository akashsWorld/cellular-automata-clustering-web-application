package com.akash.cluster.DTOS;

import java.util.ArrayList;

public record ClusterDTO(Integer clusterNumber,
                         Long levels,
                         ArrayList<ArrayList<Integer>> clusters
                         ) { }

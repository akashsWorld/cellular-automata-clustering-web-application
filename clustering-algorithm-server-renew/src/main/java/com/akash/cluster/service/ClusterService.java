package com.akash.cluster.service;

import com.akash.caclustering.clusteringException.RuleInvalidException;
import com.akash.cluster.DTOS.CaClusterRequest;
import com.akash.cluster.DTOS.ClusterDTO;

import java.util.ArrayList;
import java.util.List;

public interface ClusterService {
    ClusterDTO getLevelZeroClusters(ArrayList<ArrayList<String>> operationalData);
    ClusterDTO getLevelOneClusters(ArrayList<ArrayList<String>> operationalData, Integer neighbourHood, List<Integer> groups, Boolean isRandom,String boundary);
    ClusterDTO getLevelTwoClusters(CaClusterRequest caClusterRequest,Integer requiredCluster,String boundaryName,Integer neighbourhood) throws RuleInvalidException;
}

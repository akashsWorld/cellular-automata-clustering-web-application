package com.akash.cluster;


import com.akash.caclustering.clusteringException.RuleInvalidException;
import com.akash.caclustering.mergeing.Merge;
import com.akash.caclustering.utility.Pair;
import com.akash.cluster.DTOS.CaClusterRequest;
import com.akash.cluster.DTOS.ClusterDTO;
import com.akash.cluster.service.ClusterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImplementation implements ClusterService {

    @Override
    public ClusterDTO getLevelZeroClusters(ArrayList<ArrayList<String>> operationalData) {
        return null;
    }

    @Override
    public ClusterDTO getLevelOneClusters(ArrayList<ArrayList<String>> operationalData, Integer neighbourHood, List<Integer> groups, Boolean isRandom, String boundary) {
        return null;
    }

    @Override
    public ClusterDTO getLevelTwoClusters(CaClusterRequest caClusterRequest, Integer requiredCluster, String boundaryName, Integer neighbourhood) throws RuleInvalidException {
        Merge merge = new Merge();
        Pair<ArrayList<ArrayList<Integer>>, Long> result  = merge.mergeClustersFinal(caClusterRequest.getOperationalData(),caClusterRequest.getPrimaryCluster(),requiredCluster,neighbourhood);
        return new ClusterDTO(result.getFirst().size(),result.getSecond(),result.getFirst());
    }
}

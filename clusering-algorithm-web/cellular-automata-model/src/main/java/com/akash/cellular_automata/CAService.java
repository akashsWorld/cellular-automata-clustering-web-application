package com.akash.cellular_automata;


import com.akash.caclustering.clustering.ClusterFinder;
import com.akash.caclustering.clustering.Clusters;
import com.akash.caclustering.clusteringException.RuleInvalidException;
import com.akash.caclustering.utility.Pair;
import com.akash.client.cellular_automata.CARequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CAService {

    private final CAHelper caHelper;

    public Pair<ArrayList<String>,Integer> findAuxiliaryClusters(CARequest caRequest, Integer neighbourHood)
            throws RuleInvalidException {
//        TODO: add data filter.

        Clusters  clusters = new ClusterFinder(neighbourHood, caHelper.getBoundary(caRequest.getBoundaryName()));

        return clusters.getClusters(caRequest.getOperationalData());
    }

    public Pair<ArrayList<String>, Integer> findPrimaryCluster(CARequest caRequest, Integer neighbourHood, List<Integer> groups, Boolean isRandom) throws RuleInvalidException {
//        TODO: add data filter.

        Clusters clusters = new ClusterFinder(neighbourHood, caHelper.getBoundary(caRequest.getBoundaryName()));
        return clusters.getPrimaryCluster(caRequest.getOperationalData(),new ArrayList<>(groups),isRandom);
    }
}

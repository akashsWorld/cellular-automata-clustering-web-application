package com.akash.merge;

import com.akash.caclustering.mergeing.Merge;
import com.akash.client.merge_cluster.MergeArrayRequest;
import com.akash.client.merge_cluster.MergeClusterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MergeService {
    public ArrayList<ArrayList<Double>> findMergeArray(
            MergeArrayRequest mergeArrayRequest
    ){
        return Merge.createMergeArray(
                mergeArrayRequest.getPrimaryCluster(),
                mergeArrayRequest.getAuxiliaryCluster());
    }

    public ArrayList<ArrayList<Integer>> mergeClusters(
            MergeClusterRequest mergeClusterRequest,
            Integer requiredNumberCluster
    ){
        if(requiredNumberCluster==null){
            return Merge.initializeMerge(
                    mergeClusterRequest.getMergeArray(),
                    mergeClusterRequest.getPrimaryCluster()).getFirst();
        }

        return Merge.initializeMerge(
                mergeClusterRequest.getMergeArray(),
                mergeClusterRequest.getPrimaryCluster(),requiredNumberCluster).getFirst();
    }




}

package com.akash.merge;

import com.akash.client.merge_cluster.MergeArrayRequest;
import com.akash.client.merge_cluster.MergeClusterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "mergeCluster/merge")
@CrossOrigin
@RequiredArgsConstructor
public class MergeController {

    private final MergeService mergeService;

    @RequestMapping(value = "geMergeArray",method = {RequestMethod.POST},consumes = "application/json",produces = "application/json")
    public ArrayList<ArrayList<Double>> findMergeArray(
            @RequestBody MergeArrayRequest mergeArrayRequest
    ){
        return mergeService.findMergeArray(mergeArrayRequest);
    }
    @RequestMapping(value = "mergeClusters",method = {RequestMethod.POST},consumes = "application/json",produces = "application/json")
    public ArrayList<ArrayList<Integer>> mergeClusters (
            @RequestBody MergeClusterRequest mergeClusterRequest,
            @RequestParam(name = "requiredClusters",required = false,defaultValue = "2") Integer requiredCluster
    ){
        return mergeService.mergeClusters(mergeClusterRequest,requiredCluster);
    }
}

package com.akash.cluster.controller;


import com.akash.caclustering.clusteringException.RuleInvalidException;
import com.akash.cluster.DTOS.CaClusterRequest;
import com.akash.cluster.DTOS.ClusterDTO;
import com.akash.cluster.service.ClusterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/cluster")
@RequiredArgsConstructor
public class ClusterController {
    private final ClusterService clusterService;

    @PostMapping(value = "levelZero")
    public ResponseEntity<ClusterDTO> getLevelZeroClusters(
            @RequestBody ArrayList<ArrayList<String>> operationalData
    ){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clusterService.getLevelZeroClusters(operationalData));
    }

    @PostMapping(value = "levelOne")
    public ResponseEntity<ClusterDTO> getLevelZeroClusters(
            @RequestBody ArrayList<ArrayList<String>> operationalData,
            @RequestParam(name = "groups",required = false) List<Integer> groups,
            @RequestParam(name = "neighbourHood",required = false,defaultValue = "3") Integer neighbourHood,
            @RequestParam(name = "isRandom",required = false,defaultValue = "false") Boolean isRandom,
            @RequestParam(name = "boundary") String boundary
    ){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clusterService.getLevelOneClusters(
                operationalData,
                neighbourHood,
                groups,
                isRandom,
                boundary));
    }

    @PostMapping(value = "levelTwo")
    public ResponseEntity<ClusterDTO> getLevelZeroClusters(
            @RequestBody CaClusterRequest mergeClusterRequest,
            @RequestParam(name = "boundary") String boundaryName,
            @RequestParam(name = "neighbourHood") Integer neighbourHood,
            @RequestParam(name= "requiredCluster") Integer requiredCluster
    ) throws RuleInvalidException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clusterService.getLevelTwoClusters(
                mergeClusterRequest,
                requiredCluster,
                boundaryName,
                neighbourHood));
    }

}

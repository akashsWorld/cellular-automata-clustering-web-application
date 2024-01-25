package com.akash.cluster;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("cluster/FindCluster")
@RequiredArgsConstructor
public class ClusterFinderController {

    private final ClusterFinderService clusterFinderService;

    @PostMapping("levelZero")
    public ResponseEntity<ArrayList<ArrayList<Integer>>> findClusterAtLevelZero(@RequestBody ArrayList<ArrayList<String>> operationalData){
        return ResponseEntity.ok(clusterFinderService.findClusterAtLevelZero(operationalData));
    }
}

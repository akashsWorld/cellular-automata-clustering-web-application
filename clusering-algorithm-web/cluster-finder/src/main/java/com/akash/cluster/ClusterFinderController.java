package com.akash.cluster;

import com.akash.caclustering.clusteringException.RuleInvalidException;
import com.akash.client.cellular_automata.CAMergeClustersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cluster/FindCluster")
@RequiredArgsConstructor
public class ClusterFinderController {

    private final ClusterFinderService clusterFinderService;

    @GetMapping("levelZero")
    public ResponseEntity<ClusterFinderResponse> findClustersAtLevelZero(@RequestBody ArrayList<ArrayList<String>> operationalData){
        return ResponseEntity.ok(clusterFinderService.findClusterAtLevelZero(operationalData));
    }

    @GetMapping("levelOne")
    public ResponseEntity<ClusterFinderResponse> findClustersAtLevelOne(
            @RequestBody ArrayList<ArrayList<String>> operationalData,
            @RequestParam(name = "groups",required = false) List<Integer> groups,
            @RequestParam(name = "neighbourHood",required = false,defaultValue = "3") Integer neighbourHood,
            @RequestParam(name = "isRandom",required = false,defaultValue = "false") Boolean isRandom,
            @RequestParam(name = "boundary") String boundary
    ) throws RuleInvalidException {


//        TODO: not tested and also not handle the exception.

        return ResponseEntity.status(HttpStatus.OK)
                .body(clusterFinderService.findClusterAtLevelOne(
                        operationalData,
                        neighbourHood,
                        groups,
                        isRandom,
                        boundary
                ));
    }

    @GetMapping("levelTwo")
    public ResponseEntity<ClusterFinderResponse> findClustersAtLevelTwo(
            @RequestBody CAMergeClustersRequest mergeClusterRequest,
            @RequestParam(name = "boundary") String boundaryName,
            @RequestParam(name = "neighbourHood") Integer neighbourHood,
            @RequestParam(name= "requiredCluster") Integer requiredCluster
    ) throws RuleInvalidException {
        return ResponseEntity.status(HttpStatus.OK).body(clusterFinderService.findClustersAtLevelTwo(
                mergeClusterRequest,
                requiredCluster,
                boundaryName,
                neighbourHood
        ));
    }


}

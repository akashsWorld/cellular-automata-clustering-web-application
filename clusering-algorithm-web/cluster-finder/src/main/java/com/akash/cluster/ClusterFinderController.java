package com.akash.cluster;

import com.akash.caclustering.clusteringException.RuleInvalidException;
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

    @PostMapping("levelZero")
    public ResponseEntity<ArrayList<ArrayList<Integer>>> findClusterAtLevelZero(@RequestBody ArrayList<ArrayList<String>> operationalData){
        return ResponseEntity.ok(clusterFinderService.findClusterAtLevelZero(operationalData));
    }

    @PostMapping("levelTwo")
    public ResponseEntity<ArrayList<ArrayList<Integer>>> findPrimaryCluster(
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
}

package com.akash.cellular_automata;

import com.akash.caclustering.clusteringException.RuleInvalidException;
import com.akash.caclustering.utility.Pair;
import com.akash.client.cellular_automata.CARequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("cellularAutomata/find")
@RequiredArgsConstructor
public class CAController {

    private final CAService caService;

    @RequestMapping(value = "findAuxiliary",method = {RequestMethod.POST},consumes = "application/json",produces = "application/json")
    public ResponseEntity<Pair<ArrayList<String>,Integer>> findAuxiliaryClusters(
            @RequestBody CARequest caRequest,
            @RequestParam(value = "neighbourHood") Integer neighbourHood
            ) throws RuleInvalidException {
        return ResponseEntity.status(HttpStatus.OK).body(caService.findAuxiliaryClusters(caRequest,neighbourHood));
    }

    @RequestMapping(value = "cellularAutomata/find/findPrimary",method = {RequestMethod.POST},consumes = "application/json",produces = "application/json")
    ResponseEntity<Pair<ArrayList<String>,Integer>> findPrimaryClusters(
            @RequestBody CARequest caRequest,
            @RequestParam(name = "neighbourHood") Integer neighbourHood,
            @RequestParam(name ="groups") List<Integer> groups,
            @RequestParam(name = "isRandom") Boolean isRandom
    ) throws RuleInvalidException{
        return ResponseEntity.status(HttpStatus.OK).body(caService.findPrimaryCluster(caRequest,neighbourHood,groups,isRandom));
    }
}

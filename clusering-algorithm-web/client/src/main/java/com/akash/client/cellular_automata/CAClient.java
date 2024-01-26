package com.akash.client.cellular_automata;

import com.akash.caclustering.clusteringException.RuleInvalidException;
import com.akash.caclustering.utility.Pair;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "cellular-automata",url = "localhost:8504")
public interface CAClient {

    @RequestMapping(value = "cellularAutomata/find/findAuxiliary",method = {RequestMethod.POST},consumes = "application/json",produces = "application/json")
    ResponseEntity<Pair<ArrayList<String>,Integer>> findAuxiliaryClusters(
            @RequestBody CARequest caRequest,
            @RequestParam(value = "neighbourHood") Integer neighbourHood
    ) throws RuleInvalidException;

    @RequestMapping(value = "cellularAutomata/find/findPrimary",method = {RequestMethod.POST},consumes = "application/json",produces = "application/json")
    ResponseEntity<Pair<ArrayList<String>,Integer>> findPrimaryClusters(
            @RequestBody CARequest caRequest,
            @RequestParam(name = "neighbourHood") Integer neighbourHood,
            @RequestParam(name ="groups") List<Integer> groups,
            @RequestParam(name = "isRandom") Boolean isRandom
    ) throws RuleInvalidException;
}

package com.akash.cluster;

import com.akash.caclustering.clustering.Clusters;
import com.akash.caclustering.clusteringException.RuleInvalidException;
import com.akash.caclustering.utility.HelperUtilityMethods;
import com.akash.caclustering.utility.Pair;
import com.akash.client.cellular_automata.CAClient;
import com.akash.client.cellular_automata.CARequest;
import com.akash.client.exception.ResponseInvalidException;
import com.akash.client.merge_cluster.MergeArrayRequest;
import com.akash.client.merge_cluster.MergeClusterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;


public class MergeArrayHttpRequest {

    private final HttpHeaders httpHeaders;
    private final RestTemplate restTemplate;
    private final String MERGE_CLUSTER_URL;

    private final CAClient caClient;

    public MergeArrayHttpRequest(HttpHeaders httpHeaders,
                                 RestTemplate restTemplate,
                                 CAClient caClient,
                                 Environment environment) {
        this.httpHeaders = httpHeaders;
        this.restTemplate = restTemplate;
        this.caClient = caClient;
        this.MERGE_CLUSTER_URL = environment.getProperty("merge.cluster.url");
    }

    public ArrayList<ArrayList<Double>> getMergeArray(ArrayList<ArrayList<Integer>> primaryCluster,
                                                      ArrayList<ArrayList<Integer>> auxiliaryCluster){

        MergeArrayRequest mergeArrayRequest = new MergeArrayRequest(primaryCluster,auxiliaryCluster);

        HttpEntity<MergeArrayRequest> requestEntity = new HttpEntity<>(mergeArrayRequest,httpHeaders);

        ResponseEntity<ArrayList<ArrayList<Double>>> mergeArrayResponse = restTemplate.exchange(
                MERGE_CLUSTER_URL+"geMergeArray",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if(!mergeArrayResponse.getStatusCode().is2xxSuccessful() || mergeArrayResponse.getBody()==null)
            throw new ResponseInvalidException("Merge Array response invalid");

        return mergeArrayResponse.getBody();
    }

    public ArrayList<ArrayList<Integer>> getNewMergeList(ArrayList<ArrayList<Double>> mergeArray,
                                                         ArrayList<ArrayList<Integer>> primaryCluster){


        MergeClusterRequest mergeClusterRequest = new MergeClusterRequest(mergeArray,primaryCluster);

        HttpEntity<MergeClusterRequest> mergeClusterRequestEntity = new HttpEntity<>(mergeClusterRequest,httpHeaders);

        ResponseEntity<ArrayList<ArrayList<Integer>>> afterMergeResponse = restTemplate.exchange(
                MERGE_CLUSTER_URL+"mergeClusters",
                HttpMethod.POST,
                mergeClusterRequestEntity,
                new ParameterizedTypeReference<>() {
                });

        if(!afterMergeResponse.getStatusCode().is2xxSuccessful() || afterMergeResponse.getBody()==null)
            throw new ResponseInvalidException("Merge Array response invalid");

        return afterMergeResponse.getBody();
    }

    public ArrayList<ArrayList<Integer>> getAuxiliaryCluster(ArrayList<ArrayList<String>> operationalData,
                                                             Integer neighbourHood,
                                                             String boundaryName) throws RuleInvalidException {

        CARequest caRequest = new CARequest(boundaryName,
                HelperUtilityMethods.getSelectedOperationalData(operationalData));

        ResponseEntity<Pair<ArrayList<String>,Integer>> auxiliaryClusterResponse = caClient
                .findAuxiliaryClusters(caRequest,neighbourHood);


        if(auxiliaryClusterResponse.getBody()==null)
            throw new ResponseInvalidException("Auxiliary cluster response invalid");

        Pair<ArrayList<String>,Integer> auxiliaryClusterWithClusterNumber = auxiliaryClusterResponse.getBody();

        return Clusters.groupObjectsRespectToClusters(
                auxiliaryClusterWithClusterNumber.getFirst()
        );
    }
}

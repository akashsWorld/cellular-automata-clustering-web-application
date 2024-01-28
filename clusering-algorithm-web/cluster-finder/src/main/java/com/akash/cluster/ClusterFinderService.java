package com.akash.cluster;


import com.akash.caclustering.clustering.Clusters;
import com.akash.caclustering.clusteringException.RuleInvalidException;
import com.akash.caclustering.utility.HelperUtilityMethods;
import com.akash.caclustering.utility.Pair;
import com.akash.client.cellular_automata.CAClient;
import com.akash.client.cellular_automata.CAMergeClustersRequest;
import com.akash.client.cellular_automata.CARequest;
import com.akash.client.data_parser.DataParserClient;
import com.akash.client.exception.BoundaryNotFoundException;
import com.akash.client.exception.DataInvalidException;
import com.akash.client.exception.ResponseInvalidException;
import com.akash.client.merge_cluster.MergeArrayRequest;
import com.akash.client.merge_cluster.MergeClusterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClusterFinderService {

    private final DataParserClient dataParserClient;

    private final CAClient caClient;

    private final ClusterHelperMethods helperMethods;

    private final RestTemplate restTemplate;

    private final Environment environment;

    private final String MERGE_CLUSTER_URL;

    public ClusterFinderService(DataParserClient dataParserClient,
                                CAClient caClient,
                                ClusterHelperMethods helperMethods,
                                RestTemplate restTemplate,
                                Environment environment) {
        this.dataParserClient = dataParserClient;
        this.caClient = caClient;
        this.helperMethods = helperMethods;
        this.restTemplate = restTemplate;
        this.environment = environment;
        this.MERGE_CLUSTER_URL=environment.getProperty("merge.cluster.url");
    }


    public ClusterFinderResponse findClusterAtLevelZero(ArrayList<ArrayList<String>> operationalData){

        if(operationalData == null || operationalData.isEmpty() ){
            throw new DataInvalidException("Data Not valid for the operation");
        }

        ResponseEntity<ArrayList<String>> uniqueConfigurationResponse = dataParserClient.getUniqueConfiguration(operationalData);
        ResponseEntity<ArrayList<String>> objectWiseDataResponse = dataParserClient.getObjectWiseData(operationalData);


        if(!uniqueConfigurationResponse.getStatusCode().is2xxSuccessful() || !objectWiseDataResponse.getStatusCode().is2xxSuccessful() ){
            throw new ResponseInvalidException("Response not valid getting from server.");
        }

        if(uniqueConfigurationResponse.getBody()==null || objectWiseDataResponse.getBody()==null){
            throw new NullPointerException("Response data is not valid.");
        }

        ArrayList<String> uniqueConfiguration = uniqueConfigurationResponse.getBody();
        ArrayList<String> objectWiseData = objectWiseDataResponse.getBody();

        ArrayList<ArrayList<Integer>> clusters = HelperUtilityMethods.convertObjectWiseDataToClusterWise(objectWiseData,uniqueConfiguration);

        return new ClusterFinderResponse(clusters.size(),clusters);
    }

    public ClusterFinderResponse findClusterAtLevelOne(
            ArrayList<ArrayList<String>> operationalData,
            Integer neighbourHood,
            List<Integer> groups,
            Boolean isRandom,
            String boundaryName
    ) throws RuleInvalidException {

        if(boundaryName==null)
            throw new BoundaryNotFoundException("Invalid Boundary.");

        CARequest caRequest = new CARequest(boundaryName,operationalData);

        if(groups==null){
            groups = helperMethods.findGroups(operationalData.size());
        }


        ResponseEntity<Pair<ArrayList<String>,Integer>> primaryClustersArrayIndexWiseWithClusterNumber =
                caClient.findPrimaryClusters(caRequest,neighbourHood,groups,isRandom);

        if(primaryClustersArrayIndexWiseWithClusterNumber.getBody()==null){
            throw new ResponseInvalidException("The given response cluster not valid.");
        }

        ArrayList<ArrayList<Integer>> clusters = Clusters.
                groupObjectsRespectToClusters(primaryClustersArrayIndexWiseWithClusterNumber.getBody().getFirst());


        return new ClusterFinderResponse(clusters.size(),clusters);
    }

    public ClusterFinderResponse findClustersAtLevelTwo(
            CAMergeClustersRequest caMergeClustersRequest,
            ArrayList<Integer> selectedIndexes,
            String boundaryName,
            Integer neighbourHood
    ) throws RuleInvalidException {



        ArrayList<ArrayList<String>> selectedAttributes = helperMethods.selectAttributes(
                caMergeClustersRequest.getOperationalData(),
                selectedIndexes
                );

        CARequest caRequest = new CARequest(boundaryName,selectedAttributes);

        ResponseEntity<Pair<ArrayList<String>,Integer>> auxiliaryClusterResponse = caClient
                .findAuxiliaryClusters(caRequest,neighbourHood);


        if(auxiliaryClusterResponse.getBody()==null)
            throw new ResponseInvalidException("Auxiliary cluster response invalid");

        Pair<ArrayList<String>,Integer> auxiliaryClusterWithClusterNumber = auxiliaryClusterResponse.getBody();

//        Calculate the Merge array to check merge ability.

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        MergeArrayRequest mergeArrayRequest = new MergeArrayRequest(
                caMergeClustersRequest.getPrimaryCluster(),
                Clusters.groupObjectsRespectToClusters(auxiliaryClusterWithClusterNumber.getFirst())
                );

        HttpEntity<MergeArrayRequest> requestEntity = new HttpEntity<>(mergeArrayRequest,httpHeaders);



        ResponseEntity<ArrayList<ArrayList<Double>>> mergeArrayResponse = restTemplate.exchange(
                MERGE_CLUSTER_URL+"geMergeArray",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if(!mergeArrayResponse.getStatusCode().is2xxSuccessful() || mergeArrayResponse.getBody()==null)
            throw new ResponseInvalidException("Merge Array response invalid");


        ArrayList<ArrayList<Double>> mergeArray = mergeArrayResponse.getBody();

        MergeClusterRequest mergeClusterRequest = new MergeClusterRequest(
                mergeArray,
                caMergeClustersRequest.getPrimaryCluster()
        );

        HttpEntity<MergeClusterRequest> mergeClusterRequestEntity = new HttpEntity<>(mergeClusterRequest,httpHeaders);

        ResponseEntity<ArrayList<ArrayList<Integer>>> afterMergeResponse = restTemplate.exchange(
                MERGE_CLUSTER_URL+"mergeClusters",
                HttpMethod.POST,
                mergeClusterRequestEntity,
                new ParameterizedTypeReference<>() {
                });

        if(!afterMergeResponse.getStatusCode().is2xxSuccessful() || afterMergeResponse.getBody()==null)
            throw new ResponseInvalidException("Merge Array response invalid");

        ArrayList<ArrayList<Integer>> clusters = afterMergeResponse.getBody();
        return new ClusterFinderResponse(clusters.size(),clusters);
    }
}

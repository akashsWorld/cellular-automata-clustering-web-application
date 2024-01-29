package com.akash.cluster;


import com.akash.caclustering.boundaries.Boundaries;
import com.akash.caclustering.clustering.ClusterFinder;
import com.akash.caclustering.clustering.Clusters;
import com.akash.caclustering.clusteringException.RuleInvalidException;
import com.akash.caclustering.mergeing.Merge;
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
@RequiredArgsConstructor
public class ClusterFinderService {

    private final DataParserClient dataParserClient;

    private final CAClient caClient;

    private final ClusterHelperMethods helperMethods;

    private final MergeArrayHttpRequest mergeArrayHttpRequest;




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
            Integer requiredCluster,
            String boundaryName,
            Integer neighbourHood
    ) throws RuleInvalidException {

        ArrayList<ArrayList<String>> operationalData = caMergeClustersRequest.getOperationalData();
        ArrayList<ArrayList<Integer>> primaryCluster = caMergeClustersRequest.getPrimaryCluster();


        if(requiredCluster==primaryCluster.size()){
            return new ClusterFinderResponse(primaryCluster.size(),primaryCluster);
        }

        int pre, curr=Integer.MAX_VALUE;

//                TODO: Make a request to get auxiliary cluster.


        ArrayList<ArrayList<Integer>> tempAuxiliaryCluster = mergeArrayHttpRequest
                .getAuxiliaryCluster(operationalData,neighbourHood,boundaryName) ;

//                TODO: make a request to get merge array
        ArrayList<ArrayList<Double>> tempMergeArray = mergeArrayHttpRequest.
                getMergeArray(primaryCluster,tempAuxiliaryCluster);

//                TODO: make a request to get the clusters.
        ArrayList<ArrayList<Integer>>  tempCluster = mergeArrayHttpRequest.
                getNewMergeList(tempMergeArray,new ArrayList<>(primaryCluster));

        pre=tempCluster.size();

        ArrayList<ArrayList<Integer>> preTempClusters;

        do {

            if (curr < requiredCluster) {
                tempCluster = new ArrayList<>(primaryCluster);
            }

            tempAuxiliaryCluster = mergeArrayHttpRequest
                    .getAuxiliaryCluster(operationalData,neighbourHood,boundaryName);

            tempMergeArray = mergeArrayHttpRequest.getMergeArray(tempCluster,tempAuxiliaryCluster);
            tempCluster = mergeArrayHttpRequest.getNewMergeList(tempMergeArray,new ArrayList<>(tempCluster));
            curr = tempCluster.size();


            preTempClusters = new ArrayList<>(tempCluster);

//                    Reset the parameters to re-clustering
            if (curr > pre) {
                tempCluster = new ArrayList<>(primaryCluster);
            }

            pre = curr;

        } while (requiredCluster != curr);

        return new ClusterFinderResponse(preTempClusters.size(),preTempClusters);
    }

//    public void someMethod(){
//
//
//
//
//
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<MergeArrayRequest> requestEntity = new HttpEntity<>(mergeArrayRequest,httpHeaders);
//
//        ResponseEntity<ArrayList<ArrayList<Double>>> mergeArrayResponse = restTemplate.exchange(
//                MERGE_CLUSTER_URL+"geMergeArray",
//                HttpMethod.POST,
//                requestEntity,
//                new ParameterizedTypeReference<>() {
//                });
//
//        if(!mergeArrayResponse.getStatusCode().is2xxSuccessful() || mergeArrayResponse.getBody()==null)
//            throw new ResponseInvalidException("Merge Array response invalid");
//
//
//
//        HttpEntity<MergeClusterRequest> mergeClusterRequestEntity = new HttpEntity<>(mergeClusterRequest,httpHeaders);
//
//        ResponseEntity<ArrayList<ArrayList<Integer>>> afterMergeResponse = restTemplate.exchange(
//                MERGE_CLUSTER_URL+"mergeClusters",
//                HttpMethod.POST,
//                mergeClusterRequestEntity,
//                new ParameterizedTypeReference<>() {
//                });
//
//        if(!afterMergeResponse.getStatusCode().is2xxSuccessful() || afterMergeResponse.getBody()==null)
//            throw new ResponseInvalidException("Merge Array response invalid");
//
//
//
//
//    }
}

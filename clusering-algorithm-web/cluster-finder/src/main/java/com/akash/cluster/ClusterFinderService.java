package com.akash.cluster;


import com.akash.caclustering.boundaries.Boundaries;
import com.akash.caclustering.clustering.Clusters;
import com.akash.caclustering.clusteringException.RuleInvalidException;
import com.akash.caclustering.utility.Boundary;
import com.akash.caclustering.utility.HelperUtilityMethods;
import com.akash.caclustering.utility.Pair;
import com.akash.client.cellular_automata.CAClient;
import com.akash.client.cellular_automata.CARequest;
import com.akash.client.data_parser.DataParserClient;
import com.akash.client.exception.BoundaryNotFoundException;
import com.akash.client.exception.DataInvalidException;
import com.akash.client.exception.ResponseInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClusterFinderService {

    private final DataParserClient dataParserClient;

    private final CAClient caClient;

    private final ClusterHelperMethods helperMethods;

    public ArrayList<ArrayList<Integer>> findClusterAtLevelZero(ArrayList<ArrayList<String>> operationalData){

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

        return HelperUtilityMethods.convertObjectWiseDataToClusterWise(objectWiseData,uniqueConfiguration);
    }

    public ArrayList<ArrayList<Integer>> findClusterAtLevelOne(
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

        return Clusters.groupObjectsRespectToClusters(primaryClustersArrayIndexWiseWithClusterNumber.getBody().getFirst());
    }
}

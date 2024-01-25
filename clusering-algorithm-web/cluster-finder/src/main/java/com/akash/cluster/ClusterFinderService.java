package com.akash.cluster;


import com.akash.caclustering.utility.HelperUtilityMethods;
import com.akash.client.data_parser.DataParserClient;
import com.akash.client.exception.DataInvalidException;
import com.akash.client.exception.ResponseInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ClusterFinderService {

    private final DataParserClient dataParserClient;

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
}

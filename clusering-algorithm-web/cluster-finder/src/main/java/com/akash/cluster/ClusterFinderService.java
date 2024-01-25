package com.akash.cluster;


import com.akash.client.data_parser.DataParserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ClusterFinderService {

    private final DataParserClient dataParserClient;

    public ArrayList<Integer> findClusterAtLevelZero(ArrayList<ArrayList<String>> operationalData){


        ArrayList<String> arrayList = dataParserClient.getObjectWiseData(operationalData).getBody();
        if(arrayList==null)
            throw new NullPointerException("Data is invalid");

        System.out.println(arrayList.size());
        for (String str  : arrayList){
            System.out.println(str);
        }

//        This method is take help to an API.

        return null;
    }
}

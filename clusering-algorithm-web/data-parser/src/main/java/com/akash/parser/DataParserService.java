package com.akash.parser;

import com.akash.caclustering.dataParser.ParseToCategoricalData;
import com.akash.caclustering.dataParser.ParseToCategoricalDataImpl;
import com.akash.caclustering.utility.HelperUtilityMethods;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DataParserService {

    public ArrayList<ArrayList<String>> convertNumericalDataToCategoricalData(ArrayList<ArrayList<String>> data,Integer divisions){

        if(data==null)
            throw new NullPointerException("The request body is null");

        ArrayList<ArrayList<Double>> numericalData = HelperUtilityMethods.stringDoubleDataHandler(data);

        ParseToCategoricalData parse = new ParseToCategoricalDataImpl(numericalData,divisions);

        return parse.getOperationalData();
    }
}

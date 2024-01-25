package com.akash.parser;

import com.akash.caclustering.dataParser.ParseToCategoricalData;
import com.akash.caclustering.dataParser.ParseToCategoricalDataImpl;
import com.akash.caclustering.utility.HelperUtilityMethods;
import com.akash.client.exception.DataInvalidException;
import com.akash.parser.responses.ParseDataResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DataParserService {

    public ParseDataResponse convertNumericalDataToCategoricalData(ArrayList<ArrayList<String>> data, Integer divisions){

        if(data==null)
            throw new NullPointerException("The request body is null");

        ArrayList<ArrayList<Double>> numericalData = HelperUtilityMethods.stringDoubleDataHandler(data);

        ParseToCategoricalData parse = new ParseToCategoricalDataImpl(numericalData,divisions);

        return new ParseDataResponse(
                parse.getOperationalData().getFirst(),
                parse.getOperationalData().getSecond());
    }

    public ArrayList<String> getUniqueConfiguration(ArrayList<ArrayList<String>> operationalData) {
        if(operationalData==null)
            throw new NullPointerException("Given data is invalid");
        if(operationalData.isEmpty())
            throw new DataInvalidException("Given data is invalid");
        return ParseToCategoricalData.getUniqueConfigurations(operationalData);
    }

    public ArrayList<String> getObjectWiseData(ArrayList<ArrayList<String>> operationalData) {
        if(operationalData==null)
            throw new NullPointerException("Given data is invalid");
        if(operationalData.isEmpty())
            throw new DataInvalidException("Given data is invalid");
        return ParseToCategoricalData.getDataFromAttributeToObjectWise(operationalData);
    }
}

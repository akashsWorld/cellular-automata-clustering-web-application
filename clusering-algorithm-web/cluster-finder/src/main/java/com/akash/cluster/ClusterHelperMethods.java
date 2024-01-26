package com.akash.cluster;


import java.util.ArrayList;

public class ClusterHelperMethods {
    public ArrayList<Integer> findGroups(Integer numberOfAttributes){
        return null;
    }
    public ArrayList<ArrayList<String>> selectAttributes(ArrayList<ArrayList<String>> operationalData,
                                                         ArrayList<Integer> attributeIndex){

        attributeIndex.forEach(cc->{
            if(cc>=operationalData.size()){
                throw new IndexOutOfBoundsException("Given index not valid for the operation");
            }
        });

        ArrayList<ArrayList<String>> selectedAttributes = new ArrayList<>();

        for(Integer integer:attributeIndex){
            selectedAttributes.add(operationalData.get(integer));
        }

        return selectedAttributes;
    }
}

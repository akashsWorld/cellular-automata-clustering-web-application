package com.akash.modifier;


import com.akash.modifier.utility.ModifyDataAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ModifierService {

    private final ModifyDataAttributes deleteArrayIndexes;
    public ArrayList<ArrayList<String>> deleteIndexes(ArrayList<ArrayList<String>> data,
                                                      ArrayList<Integer> indexes) throws IndexOutOfBoundsException{
        return deleteArrayIndexes.deleteArrayIndexes(data,indexes);
    }

    public ArrayList<ArrayList<String>> deleteOneIndex(ArrayList<ArrayList<String>> data, Integer index){
        return deleteArrayIndexes.deleteOneIndex(data,index);
    }

    public ArrayList<ArrayList<String>> addAttribute(ArrayList<ArrayList<String>> data, ArrayList<String> addOn){
        return deleteArrayIndexes.dataAddOnHandle(data,addOn);
    }
    public ArrayList<ArrayList<String>> addMultipleAttribute(ArrayList<ArrayList<String>> data, ArrayList<ArrayList<String>> addOn){
        return deleteArrayIndexes.multipleDataAddOnHandle(data,addOn);
    }
}
